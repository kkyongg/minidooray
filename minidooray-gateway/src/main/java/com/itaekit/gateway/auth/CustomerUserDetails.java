package com.itaekit.gateway.auth;

import com.itaekit.gateway.dto.account.response.LoginAccountResponseDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerUserDetails implements UserDetails {
    private LoginAccountResponseDto loginAccountResponseDto;

    public CustomerUserDetails(LoginAccountResponseDto loginAccountResponseDto) {
        this.loginAccountResponseDto = loginAccountResponseDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return loginAccountResponseDto.getPassword();
    }

    @Override
    public String getUsername() {
        return loginAccountResponseDto.getUserId();
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
}
