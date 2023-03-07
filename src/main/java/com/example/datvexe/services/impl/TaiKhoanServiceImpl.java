package com.example.datvexe.services.impl;

import com.example.datvexe.common.Provider;
import com.example.datvexe.common.Status;
import com.example.datvexe.config.CustomTaiKhoanDetails;
import com.example.datvexe.models.Account;
import com.example.datvexe.payloads.requests.TaiKhoanRequest;
import com.example.datvexe.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanServiceImpl implements UserDetailsService {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    public void processOAuthPostLogin(String username) {
        Account existTaiKhoan = taiKhoanRepository.findTaiKhoanByUsername(username);

        if (existTaiKhoan == null) {
            Account newTaiKhoan = new Account();
            newTaiKhoan.setUsername(username);
            newTaiKhoan.setProvider(Provider.GOOGLE);
            newTaiKhoan.setTrangThaiHoatDong(Status.ACTIVE);

            taiKhoanRepository.save(newTaiKhoan);
        }

    }


    public Account getTaiKhoanById(Long id){
        Account taiKhoan = taiKhoanRepository.findTaiKhoanById(id);
        if (taiKhoan == null) return null;
        return taiKhoan;
    }

    public List<Account> getAll(){
        List<Account> listTaiKhoan = taiKhoanRepository.findAll();
        if (listTaiKhoan.size() == 0) return null;
        return listTaiKhoan;
    }

    public Account updateTaiKhoan(TaiKhoanRequest taiKhoanRequest, Long id){
        Account taiKhoanCheck = taiKhoanRepository.findTaiKhoanById(id);
        if(taiKhoanCheck == null) return null;
//        String passWordEncoded = commonService.changePasswordToPasswordEncode(taiKhoanRequest.getPassword());
//        String passWordEncoded = taiKhoanRequest.getPassword();
        taiKhoanCheck.setPassword(taiKhoanRequest.getPassword());
        taiKhoanCheck = taiKhoanRepository.save(taiKhoanCheck);
        return taiKhoanCheck;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = taiKhoanRepository.findTaiKhoanByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomTaiKhoanDetails(user);
    }

    public UserDetails loadUserById(Long id){
        Account user = taiKhoanRepository.findTaiKhoanById(id);
        if (user == null) {
            return null;
        }
        return new CustomTaiKhoanDetails(user);
    }
}
