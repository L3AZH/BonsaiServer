package com.l3azh.bonsai.Dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTreeRequestDto {
    private String name;
    private String description;
    private Double price;
    private String picture;
    private String uuidTreeType;
}
