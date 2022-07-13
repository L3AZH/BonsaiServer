package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateBillRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateBillResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;

public interface IBillDao {
    BaseResponseDto<CreateBillResponseDto> createBill(String email, CreateBillRequestDto requestDto)
            throws AccountWithEmailNotFoundException;
}
