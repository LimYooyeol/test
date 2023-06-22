package com.test.fasoo.dto.AuthUser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.fasoo.dto.AuthTypeIdExtractable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRequest implements AuthTypeIdExtractable {

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;
}
