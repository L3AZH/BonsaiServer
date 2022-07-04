package com.l3azh.bonsai.Dto.Request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class LoginRequestDto {
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+" +
            "(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+" +
            "(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email !")
    @NotEmpty(message = "Email is empty !")
    @NotBlank(message = "Email is blank")
    private String email;

    @NotEmpty(message = "Password is empty !")
    @NotBlank(message = "Password is blank")
    private String password;
}
