package com.l3azh.bonsai.Controller;

import com.l3azh.bonsai.Dao.ITreeDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeResponseDto;
import com.l3azh.bonsai.Dto.Response.TreeGroupByTreeTypeResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tree")
public class TreeController {

    private final ITreeDao treeDao;

    @PostMapping(value = "/create-new-tree")
    public ResponseEntity<BaseResponseDto<CreateTreeResponseDto>> createNewTree(
            @Valid @RequestBody CreateTreeRequestDto requestDto
            ) throws TreeTypeWithNameAlreadyExistException {
        BaseResponseDto<CreateTreeResponseDto> responseDto = treeDao.createNewTree(requestDto);
        return ResponseEntity.ok(responseDto);
    }


    @GetMapping(value = "/get-all-tree")
    public ResponseEntity<BaseResponseDto<List<TreeDto>>> getAllTree() throws NoneTreeFoundException {
        BaseResponseDto<List<TreeDto>> responseDto = treeDao.getAllTree();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-all-tree-group-by-tree-type")
    public ResponseEntity<BaseResponseDto<List<TreeGroupByTreeTypeResponseDto>>> getListTreeGroupByTreeType()
            throws NoneTreeTypeFoundException {
        BaseResponseDto<List<TreeGroupByTreeTypeResponseDto>> responseDto =
                treeDao.getAllTreeGroupByTreeType();
        return ResponseEntity.ok(responseDto);
    }
}
