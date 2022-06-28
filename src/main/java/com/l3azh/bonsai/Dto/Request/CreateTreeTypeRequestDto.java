package com.l3azh.bonsai.Dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTreeTypeRequestDto {
    private String name;
    private String description;
}
