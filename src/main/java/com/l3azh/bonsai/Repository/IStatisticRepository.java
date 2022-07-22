package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.TotalAmountAllBillDto;
import com.l3azh.bonsai.Dto.TreeQuantityOrderDto;

import java.util.List;

public interface IStatisticRepository {
    List<TreeQuantityOrderDto> listTreeQuantityOrderByTheInputDay(int dayNumber);
    List<TotalAmountAllBillDto> listTotalBillInLastInputDay(int dayNumber);
}
