package com.test.fasoo.dto.AuthUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRequest {

    @NotNull
    private String requestId;

    @NotNull
    private String authTypeId;

    @NotNull
    private String userId;


    @Size(min = 1)
    @NotNull
    private List<String> resourceIdList;

    @NotNull
    private LocalDate beginDate;

    @NotNull
    private LocalDate expireDate;
}
