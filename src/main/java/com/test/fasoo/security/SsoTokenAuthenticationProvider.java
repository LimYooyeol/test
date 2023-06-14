package com.test.fasoo.security;


import io.jsonwebtoken.Jwts;
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
public class SsoTokenAuthenticationProvider implements AuthenticationProvider {

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

            if(subject.equals("USER1")){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
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
