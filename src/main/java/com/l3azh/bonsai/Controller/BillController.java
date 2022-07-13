package com.l3azh.bonsai.Controller;

import com.l3azh.bonsai.Dao.IBillDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateBillRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateBillResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ) throws AccountWithEmailNotFoundException {
        BaseResponseDto<CreateBillResponseDto> responseDto = billDao.createBill(email, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
