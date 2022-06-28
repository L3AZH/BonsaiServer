package com.l3azh.bonsai.ExceptionHanlder;

import com.l3azh.bonsai.Dto.Base.ErrorResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountAlreadyExistException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class BonsaiGlobalHandlerException implements
        IAccountExceptionHandler{

    /**
     * Valid field request exception handler
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        String errorMessageResponse = "None";
        if (ex.getBindingResult().getAllErrors().size() > 0) {
            ObjectError error = ex.getBindingResult().getAllErrors().get(0);
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessageResponse = fieldName + " - " + errorMessage;
        }
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .flag(false)
                        .errorMessage(errorMessageResponse)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle General Exception for Server
     */

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .flag(false)
                .errorMessage("Email or Password not correct !")
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleServerException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .flag(false)
                .errorMessage("Internal Server Error: " + e.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Account Exception
     */

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountAlreadyExistException(AccountAlreadyExistException e) {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .flag(false)
                .errorMessage(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountWithEmailNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountWithEmailNotFound(AccountWithEmailNotFoundException e) {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .flag(false)
                .errorMessage(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
