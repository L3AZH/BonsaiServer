package com.l3azh.bonsai.Dto.EntityDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDetailDto {
    private TreeDto treeDto;
    private Double priceSold;
    private Integer quantity;
}
