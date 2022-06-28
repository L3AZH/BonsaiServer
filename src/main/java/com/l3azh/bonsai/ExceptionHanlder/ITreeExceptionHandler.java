package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeWithNameAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface ITreeExceptionHandler {
    ResponseEntity<ErrorResponseDto> handleTreeWithNameAlreadyExistException(TreeWithNameAlreadyExistException e);
}
