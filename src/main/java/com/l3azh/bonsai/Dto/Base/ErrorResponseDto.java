package com.l3azh.bonsai.Dto.Base;

import com.l3azh.bonsai.Util.AppUtils;
import lombok.Builder;
import lombok.Data;

import javax.persistence.PrePersist;
import java.util.Objects;

@Data
@Builder
public class ErrorResponseDto {
    private int code;
    private boolean flag;
    private String errorMessage;
    @Builder.Default
    private String timeStamp = AppUtils.getCurrentDateString();
}
