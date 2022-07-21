package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.IBillDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.EntityDto.BillDetailDto;
import com.l3azh.bonsai.Dto.EntityDto.BillDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeDto;
import com.l3azh.bonsai.Dto.EntityDto.TreeTypeDto;
import com.l3azh.bonsai.Dto.MailDto;
import com.l3azh.bonsai.Dto.Request.CreateBillRequestDto;
import com.l3azh.bonsai.Dto.Response.*;
import com.l3azh.bonsai.Entity.AccountEntity;
import com.l3azh.bonsai.Entity.BillDetailEntity;
import com.l3azh.bonsai.Entity.BillEntity;
import com.l3azh.bonsai.Entity.TreeEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithEmailException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithUUIDException;
import com.l3azh.bonsai.Repository.IAccountRepository;
import com.l3azh.bonsai.Repository.IBillDetailRepository;
import com.l3azh.bonsai.Repository.IBillRepository;
import com.l3azh.bonsai.Repository.ITreeRepository;
import com.l3azh.bonsai.Util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillService implements IBillDao {

    private final IBillRepository billRepository;
    private final IAccountRepository accountRepository;
    private final IBillDetailRepository billDetailRepository;
    private final ITreeRepository treeRepository;
    private final EmailSenderService emailSenderService;

    @Override
    @Transactional
    public BaseResponseDto<CreateBillResponseDto> createBill(String email, CreateBillRequestDto requestDto)
            throws AccountWithEmailNotFoundException, MessagingException {
        Optional<AccountEntity> accountResultObject = accountRepository.findById(email);
        if (accountResultObject.isEmpty()) {
            throw new AccountWithEmailNotFoundException("Can not found any account with this email: " + email);
        }
        Map<String, Object> mailProperties = new HashMap<>();
        AccountEntity accountInfo = accountResultObject.get();
        BillEntity newBill = BillEntity.builder()
                .accountOfBill(accountInfo).build();
        billRepository.save(newBill);
        List<BillDetailEntity> billDetailEntityList = new ArrayList<>();
        requestDto.getListTree().forEach(treeOrder -> {
            TreeEntity treeEntity = treeRepository.findById(UUID.fromString(treeOrder.getUuidTree())).get();
            BillDetailEntity detailBill = BillDetailEntity.builder()
                    .billDetailKey(BillDetailEntity.BillDetailKey.builder()
                            .fkUUIDBill(newBill.getUuidBill())
                            .fkUUIDTree(UUID.fromString(treeOrder.getUuidTree())).build())
                    .treeOfBillDetail(treeEntity)
                    .billOfBillDetail(newBill)
                    .priceSold(treeOrder.getPriceSold())
                    .quantity(treeOrder.getQuantity())
                    .build();
            billDetailEntityList.add(detailBill);
            billDetailRepository.save(detailBill);
        });
        newBill.setListBillDetail(billDetailEntityList);
        double totalNewBill = 0.0;
        for( BillDetailEntity billDetail : newBill.getListBillDetail()){
            totalNewBill += billDetail.getPriceSold()*billDetail.getQuantity();
        }
        mailProperties.put("billInfo", newBill);
        mailProperties.put("billTotal",String.format("%.2f",totalNewBill));
        MailDto mailDto = MailDto.builder()
                .to("lamhatuananh6@gmail.com")
                .from("BonsaiShop")
                .subject("Invoice Order")
                .template("invoice.html")
                .properties(mailProperties).build();
        emailSenderService.sendHtmlMessage(mailDto);
        return BaseResponseDto.<CreateBillResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(CreateBillResponseDto.builder().message("Create Bill successful !").build()).build();

    }

    @Override
    public BaseResponseDto<ListBillResponseDto> getAllBill() throws NoneBillFoundException {
        List<BillEntity> listBillResultObject = billRepository.findAll();
        if (listBillResultObject.isEmpty()) {
            throw new NoneBillFoundException("Can not found any Bill !");
        }
        List<BillDto> listBill = listBillResultObject.stream().map(billEntity -> BillDto.builder()
                .uuidBill(billEntity.getUuidBill().toString())
                .createDate(billEntity.getCreateDate())
                .email(billEntity.getAccountOfBill().getEmail())
                .listBillDetail(billEntity.getListBillDetail().stream().map(billDetailEntity -> BillDetailDto.builder()
                        .treeDto(TreeDto.builder()
                                .uuidTree(billDetailEntity.getTreeOfBillDetail().getUuidTree().toString())
                                .name(billDetailEntity.getTreeOfBillDetail().getName())
                                .description(billDetailEntity.getTreeOfBillDetail().getDescription())
                                .price(billDetailEntity.getTreeOfBillDetail().getPrice())
                                .picture(AppUtils.convertByteToBase64String(billDetailEntity.getTreeOfBillDetail().getPicture()))
                                .treeType(TreeTypeDto.builder()
                                        .uuidTreeType(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getUuidTreeType().toString())
                                        .name(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getName())
                                        .description(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getDescription())
                                        .build()
                                ).build())
                        .priceSold(billDetailEntity.getPriceSold())
                        .quantity(billDetailEntity.getQuantity())
                        .build()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
        return BaseResponseDto.<ListBillResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(ListBillResponseDto.builder().listBill(listBill).build()).build();
    }

    @Override
    public BaseResponseDto<BillResponseDto> getInfoBill(String uuidBill) throws NoneBillFoundWithUUIDException {
        Optional<BillEntity> billResultObject = billRepository.findById(UUID.fromString(uuidBill));
        if (billResultObject.isEmpty()) {
            throw new NoneBillFoundWithUUIDException("Can not found any bill with this uuid: " + uuidBill);
        }
        BillEntity billEntity = billResultObject.get();
        BillDto billDto = BillDto.builder()
                .uuidBill(billEntity.getUuidBill().toString())
                .createDate(billEntity.getCreateDate())
                .email(billEntity.getAccountOfBill().getEmail())
                .listBillDetail(billEntity.getListBillDetail().stream().map(billDetailEntity -> BillDetailDto.builder()
                        .treeDto(TreeDto.builder()
                                .uuidTree(billDetailEntity.getTreeOfBillDetail().getUuidTree().toString())
                                .name(billDetailEntity.getTreeOfBillDetail().getName())
                                .description(billDetailEntity.getTreeOfBillDetail().getDescription())
                                .price(billDetailEntity.getTreeOfBillDetail().getPrice())
                                .picture(AppUtils.convertByteToBase64String(billDetailEntity.getTreeOfBillDetail().getPicture()))
                                .treeType(TreeTypeDto.builder()
                                        .uuidTreeType(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getUuidTreeType().toString())
                                        .name(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getName())
                                        .description(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getDescription()).build())
                                .build())
                        .quantity(billDetailEntity.getQuantity())
                        .priceSold(billDetailEntity.getPriceSold())
                        .build()).collect(Collectors.toList())
                ).build();
        return BaseResponseDto.<BillResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(BillResponseDto.builder().billInfo(billDto).build()).build();
    }

    @Override
    public BaseResponseDto<ListBillResponseDto> getBillOfAccount(String email) throws NoneBillFoundWithEmailException, AccountWithEmailNotFoundException {
        Optional<AccountEntity> accountResultObject = accountRepository.findById(email);
        if(accountResultObject.isEmpty()){
            throw new AccountWithEmailNotFoundException("Can not found any account with this email: "+ email);
        }
        List<BillEntity> listBillResultObject = billRepository.findByEmail(email);
        if (listBillResultObject.isEmpty()) {
            throw new NoneBillFoundWithEmailException("Can not found any Bill with this email: " + email);
        }
        List<BillDto> listBill = listBillResultObject.stream().map(billEntity -> BillDto.builder()
                .uuidBill(billEntity.getUuidBill().toString())
                .createDate(billEntity.getCreateDate())
                .email(billEntity.getAccountOfBill().getEmail())
                .listBillDetail(billEntity.getListBillDetail().stream().map(billDetailEntity -> BillDetailDto.builder()
                        .treeDto(TreeDto.builder()
                                .uuidTree(billDetailEntity.getTreeOfBillDetail().getUuidTree().toString())
                                .name(billDetailEntity.getTreeOfBillDetail().getName())
                                .description(billDetailEntity.getTreeOfBillDetail().getDescription())
                                .price(billDetailEntity.getTreeOfBillDetail().getPrice())
                                .picture(AppUtils.convertByteToBase64String(billDetailEntity.getTreeOfBillDetail().getPicture()))
                                .treeType(TreeTypeDto.builder()
                                        .uuidTreeType(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getUuidTreeType().toString())
                                        .name(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getName())
                                        .description(billDetailEntity.getTreeOfBillDetail().getTheTreeType().getDescription())
                                        .build()
                                ).build())
                        .priceSold(billDetailEntity.getPriceSold())
                        .quantity(billDetailEntity.getQuantity())
                        .build()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
        return BaseResponseDto.<ListBillResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(ListBillResponseDto.builder().listBill(listBill).build()).build();
    }
}
