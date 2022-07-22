package com.l3azh.bonsai.Dao;

import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeResponseDto;
import com.l3azh.bonsai.Dto.Response.DeleteTreeResponseDto;
import com.l3azh.bonsai.Dto.Response.TreeGroupByTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.*;

import java.util.List;

public interface ITreeDao {
    BaseResponseDto<CreateTreeResponseDto> createNewTree(CreateTreeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException, NoneTreeTypeFoundWithUUIDException;

    BaseResponseDto<List<TreeDto>> getAllTree() throws NoneTreeFoundException;

    BaseResponseDto<TreeDto> getTree(String uuidTree) throws NoneTreeFoundWithUUIDException;

    BaseResponseDto<List<TreeGroupByTreeTypeResponseDto>> getAllTreeGroupByTreeType()
            throws NoneTreeTypeFoundException;

    BaseResponseDto<TreeGroupByTreeTypeResponseDto> getListTreeGroupByTreeType(String uuidTreeType)
            throws NoneTreeTypeFoundException, NoneTreeTypeFoundWithUUIDException, NoneTreeFoundWithTreeTypeException;

    BaseResponseDto<List<TreeDto>> getListTreeGroupByName(String nameTree)
            throws NoneTreeFoundWithNameException;

    BaseResponseDto<UpdateTreeResponseDto> updateTree(String uuidTree, UpdateTreeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException, NoneTreeTypeFoundWithUUIDException, NoneTreeFoundWithUUIDException;

    BaseResponseDto<DeleteTreeResponseDto> deleteTree(String uuidTree)
            throws NoneTreeFoundWithUUIDException, TreeDeleteExistInBillDetailException;
}
