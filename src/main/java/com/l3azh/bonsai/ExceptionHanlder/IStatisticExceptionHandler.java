package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.StatisticResultEmptyException;
import org.springframework.http.ResponseEntity;

public interface IStatisticExceptionHandler {

    ResponseEntity<ErrorResponseDto> handleStatisticResultEmptyException(StatisticResultEmptyException e);
}
