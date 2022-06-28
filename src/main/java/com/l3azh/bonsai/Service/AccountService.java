package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Dao.IAccountDao;
import com.l3azh.bonsai.Dto.Base.BaseResponseDto;
import com.l3azh.bonsai.Dto.Request.CreateAccountRequestDto;
import com.l3azh.bonsai.Dto.Request.UpdateAccountRequestDto;
import com.l3azh.bonsai.Dto.Response.CreateAccountResponseDto;
import com.l3azh.bonsai.Dto.Response.InfoAccountResponseDto;
import com.l3azh.bonsai.Dto.Response.UpdateAccountResponseDto;
import com.l3azh.bonsai.Entity.AccountEntity;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountAlreadyExistException;
import com.l3azh.bonsai.ExceptionHanlder.Exceptions.AccountWithEmailNotFoundException;
import com.l3azh.bonsai.Repository.IAccountRepository;
import com.l3azh.bonsai.Util.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountDao {
    private final IAccountRepository accountRepository;

    private final ApplicationContext applicationContext;

    @Override
    @Transactional
    public BaseResponseDto<CreateAccountResponseDto> createNewAccount(CreateAccountRequestDto requestDto)
            throws AccountAlreadyExistException {
        /**
         * Check email exist in db
         */
        Optional<AccountEntity> accountExist = accountRepository.findById(requestDto.getEmail());
        if (accountExist.isPresent()) {
            throw new AccountAlreadyExistException("Account with this email already exist in database !");
        }
        /**
         * Check if user request an avatar
         */
        byte[] avatar = null;
        if (requestDto.getAvatar() != null) {
            if (requestDto.getAvatar().length() > 0) {
                avatar = Base64.getDecoder().decode(requestDto.getAvatar());
            }
        }
        /**
         * Create new account
         */
        String passwordEncode = applicationContext.getBean(
                "passwordEncoder",
                BCryptPasswordEncoder.class).encode(requestDto.getPassword());
        AccountEntity newAccount;
        if (avatar != null && avatar.length > 0) {
            newAccount = AccountEntity.builder()
                    .email(requestDto.getEmail())
                    .password(passwordEncode)
                    .firstName(requestDto.getFirstName())
                    .lastName(requestDto.getLastName())
                    .phoneNumber(requestDto.getPhonenumber())
                    .avatar(avatar).build();
        } else {
            newAccount = AccountEntity.builder()
                    .email(requestDto.getEmail())
                    .password(passwordEncode)
                    .firstName(requestDto.getFirstName())
                    .lastName(requestDto.getLastName())
                    .phoneNumber(requestDto.getPhonenumber()).build();
        }
        accountRepository.save(newAccount);
        return BaseResponseDto.<CreateAccountResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(CreateAccountResponseDto.builder().message("Create account successful !").build()).build();
    }

    @Override
    public BaseResponseDto<InfoAccountResponseDto> getAccountInfo(String email) throws AccountWithEmailNotFoundException {
        Optional<AccountEntity> result = accountRepository.findById(email);
        AccountEntity accountInfo;
        if (result.isEmpty()) {
            throw new AccountWithEmailNotFoundException("Can not found any account with this email: " + email);
        }
        accountInfo = result.get();
        return BaseResponseDto.<InfoAccountResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(new InfoAccountResponseDto(
                        accountInfo.getEmail(),
                        accountInfo.getFirstName(),
                        accountInfo.getLastName(),
                        accountInfo.getPhoneNumber(),
                        accountInfo.getAvatar() == null ?
                                "" : AppUtils.convertByteToBase64String(accountInfo.getAvatar())
                )).build();

    }

    @Override
    @Transactional
    public BaseResponseDto<UpdateAccountResponseDto> updateAccountInfo(String email, UpdateAccountRequestDto requestDto) throws AccountWithEmailNotFoundException {
        Optional<AccountEntity> result = accountRepository.findById(email);
        AccountEntity accountInfo;
        if (result.isEmpty()) {
            throw new AccountWithEmailNotFoundException("Can not found any account with this email: " + email);
        }
        accountInfo = result.get();
        accountInfo.setFirstName(requestDto.getFirstName());
        accountInfo.setLastName(requestDto.getLastName());
        accountInfo.setPhoneNumber(requestDto.getPhonenumber());
        accountInfo.setAvatar(AppUtils.convertStringBase64ToByteArray(requestDto.getAvatar()));
        accountRepository.save(accountInfo);

        return BaseResponseDto.<UpdateAccountResponseDto>builder()
                .code(HttpStatus.OK.value())
                .flag(true)
                .data(UpdateAccountResponseDto.builder().message("Update account successful !").build()).build();
    }
}
