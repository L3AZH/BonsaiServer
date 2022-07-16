package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithUUIDException;
import org.springframework.http.ResponseEntity;

public interface IBillExceptionHandler {
    ResponseEntity<ErrorResponseDto> handleNoneBillFoundException(NoneBillFoundException e);
    ResponseEntity<ErrorResponseDto> handleNoneBillFoundWithUUIDException(NoneBillFoundWithUUIDException e);
}
