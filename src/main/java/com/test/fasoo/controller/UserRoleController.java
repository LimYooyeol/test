package com.test.fasoo.controller;

import com.test.fasoo.dto.UserRole.UserRoleRequest;
import com.test.fasoo.dto.UserRole.UserRoleResponse;
import com.test.fasoo.exception.ErrorResponse;
import com.test.fasoo.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping("/user-role")
    public UserRoleResponse userRoleAdd(@RequestBody @Valid UserRoleRequest userRoleRequest){
        UserRoleResponse userRoleResponse = userRoleService.addUserRole(userRoleRequest);

        return userRoleResponse;
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){

        ErrorResponse errorResponse = new ErrorResponse();

        if(e.getErrorCode() == 1452){   // FK constraints 위반
            errorResponse.setCode("-002");
            errorResponse.setMessage("Not a valid admin type");
        }else if(e.getErrorCode() == 1062){     // UNIQUE constraints 위반
            errorResponse.setCode("-003");
            errorResponse.setMessage("The user already has an admin role");
        }else{
            errorResponse.setMessage("Invalid parameters");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
