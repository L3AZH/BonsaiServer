package com.l3azh.bonsai.Controller;

import com.l3azh.bonsai.Dao.ITreeTypeDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeTypeDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.DeleteTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeTypeResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundWithUUIDException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWasUsedBySomeTreeInDBException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tree-type")
public class TreeTypeController {

    private final ITreeTypeDao treeTypeDao;

    @PostMapping(value = "/create-tree-type")
    public ResponseEntity<BaseResponseDto<CreateTreeTypeResponseDto>> createNewTreeType(
            @Valid @RequestBody CreateTreeTypeRequestDto requestDto
            ) throws TreeTypeWithNameAlreadyExistException {
        BaseResponseDto<CreateTreeTypeResponseDto> responseDto = treeTypeDao.createNewTreeType(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(value = "/update-tree-type")
    public ResponseEntity<BaseResponseDto<UpdateTreeTypeResponseDto>> updateTreeType(
            @RequestParam String uuidTreeType,@Valid @RequestBody UpdateTreeTypeRequestDto requestDto
            ) throws TreeTypeWithNameAlreadyExistException, NoneTreeTypeFoundWithUUIDException {
        BaseResponseDto<UpdateTreeTypeResponseDto> responseDto = treeTypeDao.updateTreeType(uuidTreeType, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-all-tree-type")
    public ResponseEntity<BaseResponseDto<List<TreeTypeDto>>> getAllTreeType() throws NoneTreeTypeFoundException {
        BaseResponseDto<List<TreeTypeDto>> listTreeType = treeTypeDao.getAllTreeType();
        return ResponseEntity.ok(listTreeType);
    }

    @DeleteMapping(value = "/delete-tree-type")
    public ResponseEntity<BaseResponseDto<DeleteTreeTypeResponseDto>> deleteTreeType(
            @RequestParam String uuidTreeType
    ) throws TreeTypeWasUsedBySomeTreeInDBException, NoneTreeTypeFoundWithUUIDException {
        BaseResponseDto<DeleteTreeTypeResponseDto> responseDto = treeTypeDao.deleteTreeType(uuidTreeType);
        return ResponseEntity.ok(responseDto);
    }
}
