package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.IBillDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateBillRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateBillResponseDto;
import com.l3azh.bonsai.Dto.Response.TreeGroupByTreeTypeResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateTreeResponseDto;
import com.l3azh.bonsai.Entity.AccountEntity;
import com.l3azh.bonsai.Entity.BillDetailEntity;
import com.l3azh.bonsai.Entity.BillEntity;
import com.l3azh.bonsai.Entity.TreeEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import com.l3azh.bonsai.Repository.IAccountRepository;
import com.l3azh.bonsai.Repository.IBillDetailRepository;
import com.l3azh.bonsai.Repository.IBillRepository;
import com.l3azh.bonsai.Repository.ITreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillService implements IBillDao {

    private final IBillRepository billRepository;
    private final IAccountRepository accountRepository;
    private final IBillDetailRepository billDetailRepository;
    private final ITreeRepository treeRepository;

    @Override
    @Transactional
    public BaseResponseDto<CreateBillResponseDto> createBill(String email, CreateBillRequestDto requestDto)
            throws AccountWithEmailNotFoundException {
        Optional<AccountEntity> accountResultObject = accountRepository.findById(email);
        if (accountResultObject.isEmpty()) {
            throw new AccountWithEmailNotFoundException("Can not found any account with this email: " + email);
        }
        AccountEntity accountInfo = accountResultObject.get();
        BillEntity newBill = BillEntity.builder()
                .accountOfBill(accountInfo).build();
        billRepository.save(newBill);
        requestDto.getListTree().forEach(treeOrder -> {
            TreeEntity treeEntity = treeRepository.findById(UUID.fromString(treeOrder.getUuidTree())).get();
            BillDetailEntity detailBill =  BillDetailEntity.builder()
                    .billDetailKey(BillDetailEntity.BillDetailKey.builder()
                            .fkUUIDBill(newBill.getUuidBill())
                            .fkUUIDTree(UUID.fromString(treeOrder.getUuidTree())).build())
                    .treeOfBillDetail(treeEntity)
                    .billOfBillDetail(newBill)
                    .priceSold(treeOrder.getPriceSold())
                    .quantity(treeOrder.getQuantity())
                    .build();
            billDetailRepository.save(detailBill);
        });
        return BaseResponseDto.<CreateBillResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(CreateBillResponseDto.builder().message("Create Bill successful !").build()).build();

    }
}
