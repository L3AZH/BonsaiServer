package com.l3azh.bonsai.Dto.Request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CreateTreeRequestDto {
    @NotEmpty(message = "Password is empty !")
    @NotBlank(message = "Password is blank")
    private String name;
    private String description;

    @NotNull(message = "Price  is empty !")
    private Double price;

    @NotEmpty(message = "Picture is empty !")
    @NotBlank(message = "Picture is blank")
    private String picture;
    private String uuidTreeType;
}
