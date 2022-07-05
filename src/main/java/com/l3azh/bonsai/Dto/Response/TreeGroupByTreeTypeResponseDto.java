package com.l3azh.bonsai.Dto.Response;

import com.l3azh.bonsai.Dto.EntityDto.TreeDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeGroupByTreeTypeResponseDto {
    private TreeTypeDto treeTypeDto;
    private List<TreeDto> listTree;
}
