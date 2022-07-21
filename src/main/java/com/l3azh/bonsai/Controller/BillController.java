package com.l3azh.bonsai.Controller;

import com.l3azh.bonsai.Dao.IBillDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.MailDto;
import com.l3azh.bonsai.Dto.Request.CreateBillRequestDto;
import com.l3azh.bonsai.Dto.Response.BillResponseDto;
import com.l3azh.bonsai.Dto.Response.CreateBillResponseDto;
import com.l3azh.bonsai.Dto.Response.ListBillResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithEmailException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.NoneBillFoundWithUUIDException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/bill")
public class BillController {

    private final IBillDao billDao;

    @PostMapping(value = "/create-bill")
    public ResponseEntity<BaseResponseDto<CreateBillResponseDto>> createBill(
            @RequestParam String email,
            @Valid @RequestBody CreateBillRequestDto requestDto
    ) throws AccountWithEmailNotFoundException, MessagingException {
        BaseResponseDto<CreateBillResponseDto> responseDto = billDao.createBill(email, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-bill-info")
    public ResponseEntity<BaseResponseDto<BillResponseDto>> getBillInfo(
            @RequestParam String uuidBill
    ) throws NoneBillFoundWithUUIDException {
        BaseResponseDto<BillResponseDto> responseDto = billDao.getInfoBill(uuidBill);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-all-bill")
    public ResponseEntity<BaseResponseDto<ListBillResponseDto>> getAllBill() throws NoneBillFoundException {
        BaseResponseDto<ListBillResponseDto> responseDto = billDao.getAllBill();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/get-bill-account")
    public ResponseEntity<BaseResponseDto<ListBillResponseDto>> getBillOfAccount(
            @RequestParam String email
    ) throws NoneBillFoundWithEmailException, AccountWithEmailNotFoundException {
        BaseResponseDto<ListBillResponseDto> responseDto = billDao.getBillOfAccount(email);
        return ResponseEntity.ok(responseDto);
    }
}
