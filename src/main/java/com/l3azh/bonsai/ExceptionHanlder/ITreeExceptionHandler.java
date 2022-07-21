package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.*;
import org.springframework.http.ResponseEntity;

public interface ITreeExceptionHandler {
    ResponseEntity<ErrorResponseDto> handleTreeWithNameAlreadyExistException(TreeWithNameAlreadyExistException e);

    ResponseEntity<ErrorResponseDto> handleNoneTreeFoundWithUUIDException(NoneTreeFoundWithUUIDException e);

    ResponseEntity<ErrorResponseDto> handleNoneTreeFoundException(NoneTreeFoundException e);

    ResponseEntity<ErrorResponseDto> handleNoneTreeFoundWithTreeType(NoneTreeFoundWithTreeTypeException e);

    ResponseEntity<ErrorResponseDto> handleNoneTreeFoundWithNameException(NoneTreeFoundWithNameException e);
}
