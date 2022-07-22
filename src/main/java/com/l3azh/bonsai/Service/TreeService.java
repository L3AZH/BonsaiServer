package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.ITreeDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeTypeDto;
import com.l3azh.bonsai.Dto.Request.CreateTreeRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateTreeRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateTreeResponseDto;
import com.l3azh.bonsai.Dto.Response.DeleteTreeResponseDto;
import com.l3azh.bonsai.Dto.Response.TreeGroupByTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeResponseDto;
import com.l3azh.bonsai.Entity.BillDetailEntity;
import com.l3azh.bonsai.Entity.TreeEntity;
import com.l3azh.bonsai.Entity.TreeTypeEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.*;
import com.l3azh.bonsai.Repository.IBillDetailRepository;
import com.l3azh.bonsai.Repository.ITreeRepository;
import com.l3azh.bonsai.Repository.ITreeTypeRepository;
import com.l3azh.bonsai.Util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreeService implements ITreeDao {

    private final ITreeRepository treeRepository;

    private final ITreeTypeRepository treeTypeRepository;
    private final IBillDetailRepository billDetailRepository;

    @Override
    @Transactional
    public BaseResponseDto<CreateTreeResponseDto> createNewTree(CreateTreeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException, NoneTreeTypeFoundWithUUIDException {
        Optional<TreeTypeEntity> treeTypeResultObject = treeTypeRepository.findById(UUID.fromString(requestDto.getUuidTreeType()));
        if (treeTypeResultObject.isEmpty()) {
            throw new NoneTreeTypeFoundWithUUIDException("Can not found any Tree Type with uuid: " + requestDto.getUuidTreeType());
        }
        TreeTypeEntity type = treeTypeResultObject.get();
        Optional<List<TreeEntity>> listTreeResultObject = treeRepository.getListTreeByName(requestDto.getName());
        if (listTreeResultObject.isPresent()) {
            if (listTreeResultObject.get().size() > 0) {
                throw new TreeTypeWithNameAlreadyExistException("Tree with this name already exist !");
            }
        }
        TreeEntity newTree = TreeEntity
                .builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .picture(AppUtils.convertStringBase64ToByteArray(requestDto.getPicture()))
                .theTreeType(type)
                .build();
        treeRepository.save(newTree);
        return BaseResponseDto.<CreateTreeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(CreateTreeResponseDto.builder().message("Create new tree successful !").build())
                .build();
    }

    @Override
    @Transactional
    public BaseResponseDto<UpdateTreeResponseDto> updateTree(String uuidTree, UpdateTreeRequestDto requestDto)
            throws TreeTypeWithNameAlreadyExistException, NoneTreeTypeFoundWithUUIDException, NoneTreeFoundWithUUIDException {
        Optional<TreeTypeEntity> treeTypeResultObject = treeTypeRepository.findById(UUID.fromString(requestDto.getUuidTreeType()));
        if (treeTypeResultObject.isEmpty()) {
            throw new NoneTreeTypeFoundWithUUIDException("Can not found any Tree Type with uuid: " + requestDto.getUuidTreeType());
        }
        TreeTypeEntity type = treeTypeResultObject.get();
        Optional<TreeEntity> treeResultObject = treeRepository.findById(UUID.fromString(uuidTree));
        if (treeResultObject.isEmpty()) {
            throw new NoneTreeFoundWithUUIDException("Can not found any Tree with uuid: " + uuidTree);
        }
        TreeEntity treeUpdate = treeResultObject.get();
        Optional<List<TreeEntity>> listTreeResultObject = treeRepository.getListTreeByName(requestDto.getName());
        if (listTreeResultObject.isPresent()) {
            if (listTreeResultObject.get().size() > 1) {
                throw new TreeTypeWithNameAlreadyExistException("Tree with this name already exist !");
            } else if (listTreeResultObject.get().size() == 1) {
                if (!uuidTree.equals(listTreeResultObject.get().get(0).getUuidTree().toString())) {
                    throw new TreeTypeWithNameAlreadyExistException("Tree with this name already exist !");
                }
            }
        }
        treeUpdate.setName(requestDto.getName());
        treeUpdate.setDescription(requestDto.getDescription());
        treeUpdate.setPrice(requestDto.getPrice());
        treeUpdate.setPicture(AppUtils.convertStringBase64ToByteArray(requestDto.getPicture()));
        treeUpdate.setTheTreeType(type);
        treeRepository.save(treeUpdate);
        return BaseResponseDto.<UpdateTreeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(UpdateTreeResponseDto.builder().message("Create new tree successful !").build())
                .build();
    }

    @Override
    public BaseResponseDto<List<TreeDto>> getAllTree() throws NoneTreeFoundException {
        List<TreeEntity> listTreeEntity = treeRepository.findAll();
        if (listTreeEntity.isEmpty()) {
            throw new NoneTreeFoundException("Can not found any tree in database !");
        }
        List<TreeDto> listTreeResult = listTreeEntity.stream().map(treeEntity -> {
            return TreeDto.builder()
                    .uuidTree(treeEntity.getUuidTree().toString())
                    .name(treeEntity.getName())
                    .description(treeEntity.getDescription())
                    .price(treeEntity.getPrice())
                    .picture(AppUtils.convertByteToBase64String(treeEntity.getPicture()))
                    .treeType(TreeTypeDto.builder()
                            .uuidTreeType(treeEntity.getTheTreeType().getUuidTreeType().toString())
                            .name(treeEntity.getTheTreeType().getName())
                            .description(treeEntity.getTheTreeType().getDescription()).build())
                    .build();
        }).collect(Collectors.toList());

        return BaseResponseDto.<List<TreeDto>>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(listTreeResult)
                .build();
    }

    @Override
    public BaseResponseDto<List<TreeDto>> getListTreeGroupByName(String nameTree) throws NoneTreeFoundWithNameException {
        List<TreeEntity> listTreeEntity = treeRepository.getListTreeContainNameSearch(nameTree);
        if (listTreeEntity.isEmpty()) {
            throw new NoneTreeFoundWithNameException("Can not found any tree with this name : " + nameTree);
        }
        List<TreeDto> listTreeResult = listTreeEntity.stream().map(treeEntity -> {
            return TreeDto.builder()
                    .uuidTree(treeEntity.getUuidTree().toString())
                    .name(treeEntity.getName())
                    .description(treeEntity.getDescription())
                    .price(treeEntity.getPrice())
                    .picture(AppUtils.convertByteToBase64String(treeEntity.getPicture()))
                    .treeType(TreeTypeDto.builder()
                            .uuidTreeType(treeEntity.getTheTreeType().getUuidTreeType().toString())
                            .name(treeEntity.getTheTreeType().getName())
                            .description(treeEntity.getTheTreeType().getDescription()).build())
                    .build();
        }).collect(Collectors.toList());

        return BaseResponseDto.<List<TreeDto>>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(listTreeResult)
                .build();
    }

    @Override
    public BaseResponseDto<TreeDto> getTree(String uuidTree) throws NoneTreeFoundWithUUIDException {
        Optional<TreeEntity> treeResultObject = treeRepository.findById(UUID.fromString(uuidTree));
        if (treeResultObject.isEmpty()) {
            throw new NoneTreeFoundWithUUIDException("Can not found any tree with uuid: " + uuidTree);
        }
        TreeEntity treeEntity = treeResultObject.get();
        return BaseResponseDto.<TreeDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(TreeDto.builder()
                        .uuidTree(treeEntity.getUuidTree().toString())
                        .name(treeEntity.getName())
                        .description(treeEntity.getDescription())
                        .price(treeEntity.getPrice())
                        .picture(AppUtils.convertByteToBase64String(treeEntity.getPicture()))
                        .treeType(TreeTypeDto.builder()
                                .uuidTreeType(treeEntity.getTheTreeType().getUuidTreeType().toString())
                                .name(treeEntity.getTheTreeType().getName())
                                .description(treeEntity.getTheTreeType().getDescription())
                                .build()
                        ).build()
                )
                .build();
    }

    @Override
    public BaseResponseDto<List<TreeGroupByTreeTypeResponseDto>> getAllTreeGroupByTreeType()
            throws NoneTreeTypeFoundException {
        List<TreeTypeEntity> listTreeTypeResultObject = treeTypeRepository.findAll();
        if (listTreeTypeResultObject.isEmpty()) {
            throw new NoneTreeTypeFoundException("Can not found any tree type !");
        }
        List<TreeGroupByTreeTypeResponseDto> resultList =
                listTreeTypeResultObject.stream().map(treeTypeEntity -> {

                    TreeTypeDto treeTypeDto = TreeTypeDto.builder()
                            .uuidTreeType(treeTypeEntity.getUuidTreeType().toString())
                            .name(treeTypeEntity.getName())
                            .description(treeTypeEntity.getDescription())
                            .build();

                    Optional<List<TreeEntity>> listTreeEntity = treeRepository.getListTreeByTreeType(treeTypeDto.getUuidTreeType());

                    if (listTreeEntity.isPresent() && listTreeEntity.get().size() > 0) {
                        List<TreeDto> listTreeDto = listTreeEntity.get().stream().map(treeEntity -> {
                            return TreeDto.builder()
                                    .uuidTree(treeEntity.getUuidTree().toString())
                                    .name(treeEntity.getName())
                                    .description(treeEntity.getDescription())
                                    .price(treeEntity.getPrice())
                                    .picture(AppUtils.convertByteToBase64String(treeEntity.getPicture()))
                                    .treeType(treeTypeDto)
                                    .build();
                        }).collect(Collectors.toList());

                        return TreeGroupByTreeTypeResponseDto.builder()
                                .treeTypeDto(treeTypeDto)
                                .listTree(listTreeDto).build();
                    } else {
                        return TreeGroupByTreeTypeResponseDto.builder()
                                .treeTypeDto(treeTypeDto)
                                .listTree(new ArrayList<>()).build();
                    }
                }).collect(Collectors.toList());

        return BaseResponseDto.<List<TreeGroupByTreeTypeResponseDto>>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(resultList).build();
    }

    @Override
    public BaseResponseDto<TreeGroupByTreeTypeResponseDto> getListTreeGroupByTreeType(String uuidTreeType)
            throws NoneTreeTypeFoundWithUUIDException, NoneTreeFoundWithTreeTypeException {
        Optional<TreeTypeEntity> treeTypeResultObject = treeTypeRepository.findById(UUID.fromString(uuidTreeType));
        if (treeTypeResultObject.isEmpty()) {
            throw new NoneTreeTypeFoundWithUUIDException("Can not found any tree type with this uuid: " + uuidTreeType);
        }
        Optional<List<TreeEntity>> listTreeResultObject = treeRepository.getListTreeByTreeType(uuidTreeType);
        if (listTreeResultObject.isEmpty()) {
            throw new NoneTreeFoundWithTreeTypeException("Can not found any tree with this tree type ");
        } else {
            if (listTreeResultObject.get().isEmpty()) {
                throw new NoneTreeFoundWithTreeTypeException("Can not found any tree with this tree type ");
            }
        }
        TreeTypeEntity treeTypeEntity = treeTypeResultObject.get();
        List<TreeEntity> listTreeEntity = listTreeResultObject.get();
        TreeTypeDto treeTypeDto = TreeTypeDto.builder()
                .uuidTreeType(treeTypeEntity.getUuidTreeType().toString())
                .name(treeTypeEntity.getName())
                .description(treeTypeEntity.getDescription())
                .build();
        List<TreeDto> listTreeDto = listTreeEntity.stream().map(treeEntity -> TreeDto.builder()
                .uuidTree(treeEntity.getUuidTree().toString())
                .name(treeEntity.getName())
                .description(treeEntity.getDescription())
                .price(treeEntity.getPrice())
                .picture(AppUtils.convertByteToBase64String(treeEntity.getPicture()))
                .treeType(treeTypeDto)
                .build()).collect(Collectors.toList());

        return BaseResponseDto.<TreeGroupByTreeTypeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(TreeGroupByTreeTypeResponseDto.builder()
                        .treeTypeDto(treeTypeDto)
                        .listTree(listTreeDto).build())
                .build();
    }

    @Override
    @Transactional
    public BaseResponseDto<DeleteTreeResponseDto> deleteTree(String uuidTree) throws NoneTreeFoundWithUUIDException, TreeDeleteExistInBillDetailException {
        Optional<TreeEntity> treeResultObject = treeRepository.findById(UUID.fromString(uuidTree));
        if(treeResultObject.isEmpty()){
            throw new NoneTreeFoundWithUUIDException("Can not found any Tree with uuid: "+ uuidTree);
        }
        List<BillDetailEntity> listBillDetailEntity = billDetailRepository.getListBillDetailByTree(uuidTree);
        if(listBillDetailEntity.size() > 0){
            throw new TreeDeleteExistInBillDetailException("Tree was exist in Bill Detail database !");
        }
        TreeEntity treeEntity = treeResultObject.get();
        treeRepository.delete(treeEntity);
        return BaseResponseDto.<DeleteTreeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(DeleteTreeResponseDto.builder().message("Delete Tree Success !").build())
                .build();
    }
}
