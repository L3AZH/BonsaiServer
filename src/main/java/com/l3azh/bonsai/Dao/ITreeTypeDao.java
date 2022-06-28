package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeTypeResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;

public interface ITreeTypeDao {
    BaseResponseDto<CreateTreeTypeResponseDto> createNewTreeType(CreateTreeTypeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException;

    BaseResponseDto<UpdateTreeTypeResponseDto> updateTreeType(
            String uuidTreeType, UpdateTreeTypeRequestDto requestDto) throws NoneTreeTypeFoundException, TreeTypeWithNameAlreadyExistException;
}
