package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;

public interface ITreeDao {
    BaseResponseDto<CreateTreeResponseDto> createNewTree(CreateTreeRequestDto requestDto) throws TreeTypeWithNameAlreadyExistException;
}
