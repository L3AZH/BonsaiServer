package com.l3azh.bonsai.Dto.Response;

import com.l3azh.bonsai.Dto.EntityDto.BillDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillResponseDto {
    private BillDto billInfo;
}
