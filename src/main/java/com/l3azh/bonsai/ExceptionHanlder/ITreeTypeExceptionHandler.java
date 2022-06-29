package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundWithUUIDException;
import org.springframework.http.ResponseEntity;

public interface ITreeTypeExceptionHandler {
    ResponseEntity<ErrorResponseDto> handleNoneTreeTypeFoundWithUUIDException(NoneTreeTypeFoundWithUUIDException e);
    ResponseEntity<ErrorResponseDto> handleNoneTreeTypeFoundException(NoneTreeTypeFoundException e);
}
