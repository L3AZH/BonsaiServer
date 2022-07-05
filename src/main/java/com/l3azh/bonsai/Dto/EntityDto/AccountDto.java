package com.l3azh.bonsai.Dto.EntityDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private String avatar;
}
