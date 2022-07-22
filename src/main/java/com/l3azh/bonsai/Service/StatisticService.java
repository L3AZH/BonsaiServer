package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.IStatisticDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.TotalAmountAllBillDto;
import com.l3azh.bonsai.Dto.TreeQuantityOrderDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.StatisticResultEmptyException;
import com.l3azh.bonsai.Repository.IStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService implements IStatisticDao {

    private final IStatisticRepository statisticRepository;

    @Override
    public BaseResponseDto<List<TreeQuantityOrderDto>> getTreeQuantityOrderByFromLastDayInput(
            int numberDayFromTheCurrent) throws StatisticResultEmptyException {
        List<TreeQuantityOrderDto> listResult =
                statisticRepository.listTreeQuantityOrderByTheInputDay(numberDayFromTheCurrent);
        if (listResult.isEmpty()) {
            throw new StatisticResultEmptyException("Result is Empty !");
        }
        return BaseResponseDto.<List<TreeQuantityOrderDto>>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(listResult)
                .build();
    }

    @Override
    public BaseResponseDto<List<TotalAmountAllBillDto>> getTotalBillForTheLastInputDay(
            int numberDayFromTheCurrent) throws StatisticResultEmptyException {
        List<TotalAmountAllBillDto> listResult =
                statisticRepository.listTotalBillInLastInputDay(numberDayFromTheCurrent);
        if(listResult.isEmpty()){
            throw new StatisticResultEmptyException("Result is Empty");
        }
        return BaseResponseDto.<List<TotalAmountAllBillDto>>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(listResult)
                .build();
    }
}
