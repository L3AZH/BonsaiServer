package com.l3azh.bonsai.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeOrder{
    private String uuidTree;
    private Integer quantity;
    private Double priceSold;
}