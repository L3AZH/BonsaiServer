package com.l3azh.bonsai.Dto.Request;

import com.l3azh.bonsai.Config.UserDetailImpl;
import com.l3azh.bonsai.ValidCustomAnotation.ValidRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CreateAccountRequestDto {

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+" +
            "(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+" +
            "(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email !")
    @NotEmpty(message = "Email is empty !")
    @NotBlank(message = "Email is blank")
    private String email;

    @NotEmpty(message = "Password is empty !")
    @NotBlank(message = "Password is blank")
    private String password;

    @NotEmpty(message = "First name is empty !")
    @NotBlank(message = "First is blank")
    private String firstName;

    @NotEmpty(message = "Last name is empty !")
    @NotBlank(message = "Last name is blank")
    private String lastName;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid Phone Number !")
    @NotEmpty(message = "Phone Number is empty !")
    @NotBlank(message = "Phone Number is blank")
    private String phonenumber;

    @ValidRole(
            bonsaiEnumClass = UserDetailImpl.BonsaiAccRole.class,
            message = "Invalid role !")
    @NotEmpty(message = "Role is empty !")
    @NotBlank(message = "Role is blank")
    private String role;

    private String avatar;
}
