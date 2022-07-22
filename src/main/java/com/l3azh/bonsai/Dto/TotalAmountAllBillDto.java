package com.l3azh.bonsai.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TotalAmountAllBillDto {
    private Date totalBillTime;
    private Double totalBill;
}
