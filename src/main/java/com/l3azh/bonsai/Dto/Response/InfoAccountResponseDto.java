package com.l3azh.bonsai.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InfoAccountResponseDto {

    private String email;
    private String firstName;
    private String lastName;
    private String phonenumber;
    private String role;
    private String avatar;
}
