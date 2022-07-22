package com.l3azh.bonsai.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeQuantityOrderDto {
    private String uuidTree;
    private String nameTree;
    private Integer quantity;
}
