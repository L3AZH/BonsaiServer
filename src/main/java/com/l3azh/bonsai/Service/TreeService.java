package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.ITreeDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeResponseDto;
import com.l3azh.bonsai.Entity.TreeEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;
import com.l3azh.bonsai.Repository.ITreeRepository;
import com.l3azh.bonsai.Util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreeService implements ITreeDao {

    private final ITreeRepository treeRepository;

    @Override
    @Transactional
    public BaseResponseDto<CreateTreeResponseDto> createNewTree(CreateTreeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException {
        Optional<List<TreeEntity>> listTreeResultObject = treeRepository.getListTreeByName(requestDto.getName());
        if (listTreeResultObject.isPresent()) {
            throw new TreeTypeWithNameAlreadyExistException("Tree with this name already exist !");
        }
        TreeEntity newTree = TreeEntity
                .builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .picture(AppUtils.convertStringBase64ToByteArray(requestDto.getPicture()))
                .build();
        treeRepository.save(newTree);
        return BaseResponseDto.<CreateTreeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(CreateTreeResponseDto.builder().message("Create new tree successful !").build())
                .build();
    }
}
