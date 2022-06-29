package com.l3azh.bonsai.Dto.EntityDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeDto {
    private String uuidTree;
    private String name;
    private String description;
    private Double price;
    private String picture;
    private TreeTypeDto treeType;
}
