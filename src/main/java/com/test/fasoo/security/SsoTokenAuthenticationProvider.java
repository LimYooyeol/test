package com.test.fasoo.security;


import com.test.fasoo.dto.UserRole.UserRoleResponse;
import com.test.fasoo.service.UserRoleService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SsoTokenAuthenticationProvider implements AuthenticationProvider {

    private final UserRoleService userRoleService;

    @Value("${secret-key}")
    private String secretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        try{
            String subject = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(authentication.getPrincipal().toString())
                    .getBody()
                    .getSubject();

            // 임시 역할 조회 START
            List<SimpleGrantedAuthority> authorities = new LinkedList<>();
            String rolePrefix = "ROLE_";

            UserRoleResponse userRole = userRoleService.findUserRoleByUserId(subject);
            if(userRole != null){
                authorities.add(new SimpleGrantedAuthority(rolePrefix + userRole.getAdminTypeId()));
            }

            // 임시 역할 조회 END

            return new UsernamePasswordAuthenticationToken(subject, null, authorities);

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
