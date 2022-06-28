package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountAlreadyExistException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

public interface IAccountExceptionHandler {
    ResponseEntity<ErrorResponseDto> handleAccountAlreadyExistException(AccountAlreadyExistException e);
    ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException e);
    ResponseEntity<ErrorResponseDto> handleAccountWithEmailNotFound(AccountWithEmailNotFoundException e);
}
