package com.l3azh.bonsai.Dto.EntityDto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BillDto {
    private String uuidBill;
    private Date createDate;
    private String email;
    private List<BillDetailDto> listBillDetail;
}
