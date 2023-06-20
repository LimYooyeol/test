package com.test.fasoo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.fasoo.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrors;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EnableWebSecurity(debug = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SsoTokenAuthenticationProvider ssoTokenAuthenticationProvider;

    private final SsoTokenAuthenticationFailureHandler ssoTokenAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        ProviderManager providerManager = new ProviderManager(ssoTokenAuthenticationProvider);
        BearerTokenAuthenticationFilter bearerTokenAuthenticationFilter = new BearerTokenAuthenticationFilter(providerManager);
        bearerTokenAuthenticationFilter.setAuthenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setCode("UNSUPPORTED_HEADER");
                errorResponse.setMessage("올바르지 않은 헤더입니다.");

                ObjectMapper mapper = new ObjectMapper();

                response.getWriter().write(mapper.writeValueAsString(errorResponse));
            }
        });
        bearerTokenAuthenticationFilter.setBearerTokenResolver(new BearerTokenResolver() {

            private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
                    Pattern.CASE_INSENSITIVE);
            private String bearerTokenHeaderName = HttpHeaders.AUTHORIZATION;
            @Override
            public String resolve(HttpServletRequest request) {
                final String authorizationHeaderToken = resolveFromAuthorizationHeader(request);

                return authorizationHeaderToken;
            }

            private String resolveFromAuthorizationHeader(HttpServletRequest request) {
                String authorization = request.getHeader(this.bearerTokenHeaderName);
                if (!StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
                    BearerTokenError error = BearerTokenErrors.invalidRequest("Not a bearer token");
                    throw new OAuth2AuthenticationException(error);
                }
                Matcher matcher = authorizationPattern.matcher(authorization);
                if (!matcher.matches()) {
                    BearerTokenError error = BearerTokenErrors.invalidToken("Bearer token is malformed");
                    throw new OAuth2AuthenticationException(error);
                }
                return matcher.group("token");
            }
        });
        bearerTokenAuthenticationFilter.setAuthenticationFailureHandler(ssoTokenAuthenticationFailureHandler);


        http
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        ErrorResponse errorResponse = new ErrorResponse();
                        errorResponse.setCode("FORBIDDEN_REQUEST");
                        errorResponse.setMessage("허용되지 않은 요청입니다.");

                        ObjectMapper mapper = new ObjectMapper();

                        response.getWriter().write(mapper.writeValueAsString(errorResponse));
                    }
                });

        http
                .csrf()
                .disable()
                .addFilterAfter(bearerTokenAuthenticationFilter, CsrfFilter.class)
                .authorizeRequests()
                .anyRequest()
                .hasRole("SYSTEM_ADMIN");

        return http.build();
    }

}
