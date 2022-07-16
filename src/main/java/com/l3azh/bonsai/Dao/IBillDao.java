package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateBillRequestDto;
import com.l3azh.bonsai.Dto.Response.BillResponseDto;
import com.l3azh.bonsai.Dto.Response.CreateBillResponseDto;
import com.l3azh.bonsai.Dto.Response.ListBillResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithEmailException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithUUIDException;

public interface IBillDao {
    BaseResponseDto<CreateBillResponseDto> createBill(String email, CreateBillRequestDto requestDto)
            throws AccountWithEmailNotFoundException;
    BaseResponseDto<ListBillResponseDto> getAllBill() throws NoneBillFoundException;
    BaseResponseDto<BillResponseDto> getInfoBill(String uuidBill) throws NoneBillFoundWithUUIDException;
    BaseResponseDto<ListBillResponseDto> getBillOfAccount(String email) throws NoneBillFoundWithEmailException, AccountWithEmailNotFoundException;
}
