package com.l3azh.bonsai.Dto.Request;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateTreeRequestDto {
    @NotEmpty(message = "Name is empty !")
    @NotBlank(message = "Name is blank")
    private String name;
    private String description;

    @NotNull(message = "Price  is empty !")
    @Min(value = 0, message = "Price must be positive number")
    private Double price;

    @NotEmpty(message = "Picture is empty !")
    @NotBlank(message = "Picture is blank")
    private String picture;
    private String uuidTreeType;
}
