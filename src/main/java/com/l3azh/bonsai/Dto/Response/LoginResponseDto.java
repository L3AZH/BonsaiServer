package com.l3azh.bonsai.Dto.Response;

import com.l3azh.bonsai.Dto.EntityDto.AccountDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private AccountDto accInfo;
}

