package com.l3azh.bonsai.Dto.Request;

import com.l3azh.bonsai.Dto.TreeOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CreateBillRequestDto {

    private List<TreeOrder> listTree;
}
