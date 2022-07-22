package com.l3azh.bonsai.Controller;

import com.l3azh.bonsai.Dao.IStatisticDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.TotalAmountAllBillDto;
import com.l3azh.bonsai.Dto.TreeQuantityOrderDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.StatisticResultEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistic")
public class StatisticController {

    private final IStatisticDao statisticDao;

    @GetMapping(value = "/get-tree-quantity-order-by-day")
    public ResponseEntity<BaseResponseDto<List<TreeQuantityOrderDto>>> getTreeQuantityOrderByDay(int dayNumber)
            throws StatisticResultEmptyException {
        BaseResponseDto<List<TreeQuantityOrderDto>> responseDto =
                statisticDao.getTreeQuantityOrderByFromLastDayInput(dayNumber);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-total-bill-by-day")
    public ResponseEntity<BaseResponseDto<List<TotalAmountAllBillDto>>> getTotalBillByDay(int dayNumber)
            throws StatisticResultEmptyException {
        BaseResponseDto<List<TotalAmountAllBillDto>> responseDto =
                statisticDao.getTotalBillForTheLastInputDay(dayNumber);
        return ResponseEntity.ok(responseDto);
    }
}
