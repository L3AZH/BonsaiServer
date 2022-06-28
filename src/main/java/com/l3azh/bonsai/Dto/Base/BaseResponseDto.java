package com.l3azh.bonsai.Dto.Base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponseDto<T> {
    private int code;
    private boolean flag;
    private T data;
}