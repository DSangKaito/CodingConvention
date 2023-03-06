package com.example.datvexe.services;

import com.example.datvexe.models.Admin;
import com.example.datvexe.models.BusCompany;
import com.example.datvexe.models.Account;
import com.example.datvexe.models.User;
import com.example.datvexe.payloads.requests.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public interface SignUpService {

    int addTaiKhoanUser(SignUpRequest signUpRequest);
    int addTaiKhoanNhaXe(SignUpRequest signUpRequest);
    int addTaiKhoanAdmin(SignUpRequest signUpRequest);
    int checkInfo(SignUpRequest signUpRequest);
    Admin convertSignUpToAdmin(SignUpRequest signUpRequest, Account taiKhoan);
    BusCompany convertSignUpToNhaXe(SignUpRequest signUpRequest, Account taiKhoan);
    User convertSignUpToUser(SignUpRequest signUpRequest, Account taiKhoan);
    Account convertSignUpToTaiKhoan(SignUpRequest signUpRequest);

}
