package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import org.springframework.http.ResponseEntity;

public interface ITreeTypeExceptionHandler {
    ResponseEntity<ErrorResponseDto> handleNoneTreeTypeFoundException(NoneTreeTypeFoundException e);
}
