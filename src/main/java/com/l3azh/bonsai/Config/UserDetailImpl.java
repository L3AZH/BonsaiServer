package com.l3azh.bonsai.Config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.l3azh.bonsai.Entity.AccountEntity;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Builder
public class UserDetailImpl implements UserDetails {

    public enum BonsaiAccRole{
        USER("USER"), ADMIN("ADMIN");

        private final String value;

        BonsaiAccRole(String theValue){
            this.value = theValue;
        }

        public String getValue(){
            return value;
        }
    }

    private String email;
    @JsonIgnore
    private String password;
    private String role;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailImpl create(AccountEntity accountEntity, BonsaiAccRole theRole) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(theRole.getValue()));
        return UserDetailImpl.builder()
                .email(accountEntity.getEmail())
                .password(accountEntity.getPassword())
                .authorities(authorityList)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        UserDetailImpl user = (UserDetailImpl) o;
        return Objects.equals(email, user.getUsername());
    }
}
