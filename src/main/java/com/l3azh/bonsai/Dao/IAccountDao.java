package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateAccountRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateAccountRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateAccountResponseDto;
import com.l3azh.bonsai.Dto.Response.InfoAccountResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateAccountResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountAlreadyExistException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;

public interface IAccountDao {
    BaseResponseDto<CreateAccountResponseDto> createNewAccount(CreateAccountRequestDto requestDto) throws AccountAlreadyExistException;
    BaseResponseDto<InfoAccountResponseDto> getAccountInfo(String email) throws AccountWithEmailNotFoundException;
    BaseResponseDto<UpdateAccountResponseDto> updateAccountInfo(String email, UpdateAccountRequestDto requestDto) throws AccountWithEmailNotFoundException;
}
