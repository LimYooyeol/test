package com.test.fasoo.aspect;

import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthCheck;
import com.test.fasoo.dto.AdminTypeAuth.AdminTypeAuthSearch;
import com.test.fasoo.dto.AuthTypeIdExtractable;
import com.test.fasoo.exception.CustomException;
import com.test.fasoo.service.AdminTypeAuthService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.test.fasoo.exception.CustomErrorCode.FORBIDDEN_REQUEST;
import static com.test.fasoo.exception.CustomErrorCode.UNDEFINED_AUTH_TYPE;

@Component
@Aspect
@RequiredArgsConstructor
public class AuthAspect {

    private final AdminTypeAuthService adminTypeAuthService;

    @Value("role-prefix")
    private String rolePrefix;

    @Before("@annotation(com.test.fasoo.aspect.annotation.AdminTypeAuthCheck)")
    public void adminTypeAuthAuthorization(JoinPoint joinPoint){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().size() == 0){
            // 관리자 역할이 아닌 경우(일반 사용자)
            throw new CustomException(FORBIDDEN_REQUEST);
        }else if(authentication.getAuthorities().size() > 1){
            throw new RuntimeException("More then one authority");
        }

        String adminTypeId = getAdminTypeId(((List<GrantedAuthority>)authentication.getAuthorities()).get(0));
        String authTypeName = getAuthTypeId(joinPoint).getAuthTypeId();

        AdminTypeAuthCheck adminTypeAuthCheck = adminTypeAuthService.checkAdminTypeAuth(new AdminTypeAuthSearch(authTypeName, adminTypeId));

        if(!adminTypeAuthCheck.getIsValidAuthTypeId()){
            // 존재하지 않은 권한 유형인 경우
            throw new CustomException(UNDEFINED_AUTH_TYPE);
        }
        if(!adminTypeAuthCheck.getHasAuthority()){
            // 담당하는 권한 유형이 아닌 관리자인 경우
            throw new CustomException(FORBIDDEN_REQUEST);
        }

    }

    private String getAdminTypeId(GrantedAuthority authority){
        return authority.getAuthority().replaceFirst(rolePrefix, "");
    }

    private AuthTypeIdExtractable getAuthTypeId(final JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();

        AuthTypeIdExtractable authTypeIdExtractable = null;
        for(Object argument : arguments){
            if(argument instanceof AuthTypeIdExtractable){
                if(authTypeIdExtractable == null){
                    authTypeIdExtractable = (AuthTypeIdExtractable) argument;
                }
                else{
                    throw new RuntimeException("JoinPoint multiple auth type id candidates errors");
                }
            }
        }

        if(authTypeIdExtractable == null){
            throw new RuntimeException("JoinPoint no auth type id Error");
        }

        return authTypeIdExtractable;
    }

}
