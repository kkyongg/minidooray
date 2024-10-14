package com.itaekit.gateway.auth;

import com.itaekit.gateway.dto.account.request.LoginAccountRequestDto;
import com.itaekit.gateway.dto.account.response.LoginAccountResponseDto;
import com.itaekit.gateway.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final AccountService accountService;

    @Autowired
    public CustomerUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginAccountRequestDto requestDto = new LoginAccountRequestDto(username);
        LoginAccountResponseDto loginAccountResponseDto = accountService.findUserByUserId(requestDto);
        return new CustomerUserDetails(loginAccountResponseDto);
    }
}
