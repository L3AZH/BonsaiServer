package com.l3azh.bonsai.Dto.Request;

import com.l3azh.bonsai.Dto.TreeOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillRequestDto {

    private List<TreeOrder> listTree;
}
