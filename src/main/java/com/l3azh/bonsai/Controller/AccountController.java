package com.l3azh.bonsai.Controller;

import com.l3azh.bonsai.Dao.IAccountDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.UpdateAccountRequestDto;
import com.l3azh.bonsai.Dto.Response.InfoAccountResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateAccountResponseDto;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountDao accountDao;

    @GetMapping("/info")
    public ResponseEntity<BaseResponseDto<InfoAccountResponseDto>> getInfoAccount(
            @RequestParam String email) throws AccountWithEmailNotFoundException {
        BaseResponseDto<InfoAccountResponseDto> responseDto = accountDao.getAccountInfo(email);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/update-info")
    public ResponseEntity<BaseResponseDto<UpdateAccountResponseDto>> updateInfo(
            @RequestParam String email, @Valid @RequestBody UpdateAccountRequestDto requestDto
    ) throws AccountWithEmailNotFoundException {
        BaseResponseDto<UpdateAccountResponseDto> responseDto = accountDao.updateAccountInfo(email, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
