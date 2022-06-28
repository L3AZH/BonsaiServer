package com.l3azh.bonsai.Dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {
    private String email;
    private String password;
}
