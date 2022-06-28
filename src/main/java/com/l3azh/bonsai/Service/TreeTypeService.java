package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.ITreeTypeDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeTypeResponseDto;
import com.l3azh.bonsai.Entity.TreeTypeEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;
import com.l3azh.bonsai.Repository.ITreeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TreeTypeService implements ITreeTypeDao {

    private final ITreeTypeRepository treeTypeRepository;

    @Override
    @Transactional
    public BaseResponseDto<CreateTreeTypeResponseDto> createNewTreeType(CreateTreeTypeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException {
        Optional<List<TreeTypeEntity>> listTreeTypeResultObject =
                treeTypeRepository.getListTreeTypeByName(requestDto.getName());
        if (listTreeTypeResultObject.isPresent()) {
            if (listTreeTypeResultObject.get().size() > 0) {
                throw new TreeTypeWithNameAlreadyExistException("TreeType with this name already exist !");
            }
        }
        TreeTypeEntity treeTypeEntity = TreeTypeEntity
                .builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription()).build();
        treeTypeRepository.save(treeTypeEntity);
        return BaseResponseDto.<CreateTreeTypeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(CreateTreeTypeResponseDto.builder().message("Create new tree type success !").build())
                .build();
    }

    @Override
    public BaseResponseDto<UpdateTreeTypeResponseDto> updateTreeType(
            String uuidTreeType, UpdateTreeTypeRequestDto requestDto)
            throws NoneTreeTypeFoundException, TreeTypeWithNameAlreadyExistException {
        Optional<TreeTypeEntity> treeTypeResultObject =
                treeTypeRepository.findById(UUID.fromString(uuidTreeType));
        if (treeTypeResultObject.isEmpty()) {
            throw new NoneTreeTypeFoundException("Can not found any Tree Type with this uuid: " + uuidTreeType);
        }
        TreeTypeEntity treeTypeUpdate = treeTypeResultObject.get();
        Optional<List<TreeTypeEntity>> listTreeTypeResultObject =
                treeTypeRepository.getListTreeTypeByName(requestDto.getName());
        if (listTreeTypeResultObject.isPresent()) {
            if (listTreeTypeResultObject.get().size() > 0) {
                throw new TreeTypeWithNameAlreadyExistException("TreeType with this name already exist !");
            }
        }
        treeTypeUpdate.setName(requestDto.getName());
        treeTypeUpdate.setDescription(requestDto.getDescription());
        treeTypeRepository.save(treeTypeUpdate);
        return BaseResponseDto.<UpdateTreeTypeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(UpdateTreeTypeResponseDto.builder().message("Update tree type successful !").build())
                .build();
    }
}
