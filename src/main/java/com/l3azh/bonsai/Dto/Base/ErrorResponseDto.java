package com.l3azh.bonsai.Dto.Base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private int code;
    private boolean flag;
    private String errorMessage;
    private String timeStamp;
}
