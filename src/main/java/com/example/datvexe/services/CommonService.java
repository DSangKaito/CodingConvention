package com.example.datvexe.services;

import com.example.datvexe.models.Account;
import com.example.datvexe.payloads.requests.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public interface CommonService {
    int checkInForUpdateAccount(SignUpRequest signUpRequest, Account taiKhoan);

    String changePasswordToPasswordEncode(String password);
}
