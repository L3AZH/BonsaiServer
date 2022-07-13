package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.ITreeTypeDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeTypeDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeTypeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeTypeResponseDto;
import com.l3azh.bonsai.Entity.TreeTypeEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneTreeTypeFoundWithUUIDException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.TreeTypeWithNameAlreadyExistException;
import com.l3azh.bonsai.Repository.ITreeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.stream.Collectors;

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
            throws NoneTreeTypeFoundWithUUIDException, TreeTypeWithNameAlreadyExistException {
        Optional<TreeTypeEntity> treeTypeResultObject =
                treeTypeRepository.findById(UUID.fromString(uuidTreeType));
        if (treeTypeResultObject.isEmpty()) {
            throw new NoneTreeTypeFoundWithUUIDException("Can not found any Tree Type with this uuid: " + uuidTreeType);
        }
        TreeTypeEntity treeTypeUpdate = treeTypeResultObject.get();
        Optional<List<TreeTypeEntity>> listTreeTypeResultObject =
                treeTypeRepository.getListTreeTypeByName(requestDto.getName());
        if (listTreeTypeResultObject.isPresent()) {
            if (listTreeTypeResultObject.get().size() > 1) {
                throw new TreeTypeWithNameAlreadyExistException("TreeType with this name already exist !");
            } else if (listTreeTypeResultObject.get().size() == 1) {
                if (!uuidTreeType.equals(listTreeTypeResultObject.get().get(0).getUuidTreeType().toString())) {
                    throw new TreeTypeWithNameAlreadyExistException("TreeType with this name already exist !");
                }
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

    @Override
    public BaseResponseDto<List<TreeTypeDto>> getAllTreeType() throws NoneTreeTypeFoundException {
        List<TreeTypeEntity> listTreeTypeEntity = treeTypeRepository.findAll();
        if (listTreeTypeEntity.isEmpty()) {
            throw new NoneTreeTypeFoundException("Can not found any TreeType in database !");
        }
        List<TreeTypeDto> listTreeTypeResult = listTreeTypeEntity.stream().map(treeTypeEntity -> {
            return TreeTypeDto.builder()
                    .uuidTreeType(treeTypeEntity.getUuidTreeType().toString())
                    .name(treeTypeEntity.getName())
                    .description(treeTypeEntity.getDescription())
                    .build();
        }).collect(Collectors.toList());
        return BaseResponseDto.<List<TreeTypeDto>>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(listTreeTypeResult)
                .build();
    }
}
