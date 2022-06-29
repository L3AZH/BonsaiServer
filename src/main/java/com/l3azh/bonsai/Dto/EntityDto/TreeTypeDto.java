package com.l3azh.bonsai.Dto.EntityDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeTypeDto {
    private String uuidTreeType;
    private String name;
    private String description;
}
