package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.TotalAmountAllBillDto;
import com.l3azh.bonsai.Dto.TreeQuantityOrderDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.StatisticResultEmptyException;

import java.util.List;

public interface IStatisticDao {

    BaseResponseDto<List<TreeQuantityOrderDto>> getTreeQuantityOrderByFromLastDayInput
            (int numberDayFromTheCurrent) throws StatisticResultEmptyException;
    BaseResponseDto<List<TotalAmountAllBillDto>> getTotalBillForTheLastInputDay
            (int numberDayFromTheCurrent) throws StatisticResultEmptyException;
}
