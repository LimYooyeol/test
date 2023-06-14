package com.test.fasoo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.fasoo.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                .hasRole("ADMIN");  // 임시 설정 -> Custom  Authority AuthorizationManager 를 이용하는 방법으로 전환 필요 할 수 있음 (매핑을 DB에서 읽어야 한다면)

        return http.build();
    }

}
