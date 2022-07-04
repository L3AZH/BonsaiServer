package com.l3azh.bonsai.Service;

import com.l3azh.bonsai.Config.UserDetailImpl;
import com.l3azh.bonsai.Entity.AccountEntity;
import com.l3azh.bonsai.Repository.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailImplService implements UserDetailsService {

    private final IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found any account with this email"));
        return UserDetailImpl.create(
                accountEntity,
                accountEntity.getRole().equalsIgnoreCase("USER") ?
                        UserDetailImpl.BonsaiAccRole.USER : UserDetailImpl.BonsaiAccRole.ADMIN);
    }
}
