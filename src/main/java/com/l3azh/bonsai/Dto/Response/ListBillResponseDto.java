package com.l3azh.bonsai.Dto.Response;

import com.l3azh.bonsai.Dto.EntityDto.BillDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListBillResponseDto {
    private List<BillDto> listBill;
}
