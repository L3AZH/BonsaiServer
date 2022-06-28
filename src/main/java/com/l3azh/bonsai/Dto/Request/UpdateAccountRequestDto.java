package com.l3azh.bonsai.Dto.Request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UpdateAccountRequestDto {

    @NotEmpty(message = "Password is empty !")
    @NotBlank(message = "Password is blank")
    private String password;

    @NotEmpty(message = "First name is empty !")
    @NotBlank(message = "First is blank")
    private String firstName;

    @NotEmpty(message = "Last name is empty !")
    @NotBlank(message = "Last name is blank")
    private String lastName;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid phonenumber !")
    @NotEmpty(message = "Phonenumber is empty !")
    @NotBlank(message = "Phonenumber is blank")
    private String phonenumber;

    private String avatar;
}
