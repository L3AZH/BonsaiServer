package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeTypeDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeTypeResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundWithUUIDException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;

import java.util.List;

public interface ITreeTypeDao {
    BaseResponseDto<CreateTreeTypeResponseDto> createNewTreeType(CreateTreeTypeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException;

    BaseResponseDto<UpdateTreeTypeResponseDto> updateTreeType(
            String uuidTreeType, UpdateTreeTypeRequestDto requestDto) throws NoneTreeTypeFoundWithUUIDException, TreeTypeWithNameAlreadyExistException;

    BaseResponseDto<List<TreeTypeDto>> getAllTreeType() throws NoneTreeTypeFoundException;
}
