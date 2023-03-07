package com.example.datvexe.services.impl;

import com.example.datvexe.common.Role;
import com.example.datvexe.common.Status;
import com.example.datvexe.models.Admin;
import com.example.datvexe.models.BusCompany;
import com.example.datvexe.models.Account;
import com.example.datvexe.models.User;
import com.example.datvexe.payloads.requests.SignUpRequest;
import com.example.datvexe.repositories.AdminRepository;
import com.example.datvexe.repositories.BusCompanyRepository;
import com.example.datvexe.repositories.TaiKhoanRepository;
import com.example.datvexe.repositories.UserRepository;
import com.example.datvexe.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusCompanyRepository nhaXeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account convertSignUpToTaiKhoan(SignUpRequest signUpRequest){
        Account taiKhoan = new Account();
        taiKhoan.setUsername(signUpRequest.getUsername());
        taiKhoan.setPassword(signUpRequest.getPassword());
        taiKhoan.setRole(signUpRequest.getRole());
        taiKhoan.setTrangThaiHoatDong(Status.ACTIVE);
        if (signUpRequest.getRole() == Role.NHAXE) taiKhoan.setTrangThaiHoatDong(Status.INACTIVE);
        return taiKhoan;
    }

    public User convertSignUpToUser(SignUpRequest signUpRequest, Account taiKhoan) {
        User user = new User();
        user.setHoTen(signUpRequest.getHoTen());
        user.setCmnd(signUpRequest.getCmnd());
        user.setSdt(signUpRequest.getSdt());
        user.setEmail(signUpRequest.getEmail());
        user.setDiaChi(signUpRequest.getDiaChi());
        user.setTaiKhoan(taiKhoan);
        return user;
    }

    public BusCompany convertSignUpToNhaXe(SignUpRequest signUpRequest, Account taiKhoan){
        BusCompany nhaXe = new BusCompany();
        nhaXe.setTenNhaXe(signUpRequest.getTenNhaXe());
        nhaXe.setSdt(signUpRequest.getSdt());
        nhaXe.setMoTaNgan(signUpRequest.getMoTaNgan());
        nhaXe.setDiaChi(signUpRequest.getDiaChi());
        nhaXe.setTaiKhoan(taiKhoan);
        return nhaXe;
    }

    public Admin convertSignUpToAdmin(SignUpRequest signUpRequest, Account taiKhoan){
        Admin admin = new Admin();
        admin.setName(signUpRequest.getName());
        admin.setEmail(signUpRequest.getEmail());
        admin.setSdt(signUpRequest.getSdt());
        admin.setCmnd(signUpRequest.getCmnd());
        admin.setTaiKhoan(taiKhoan);
        return admin;
    }

    public int checkInfo(SignUpRequest signUpRequest){
        Account taiKhoan = taiKhoanRepository.findTaiKhoanByUsername(signUpRequest.getUsername());
        if(taiKhoan != null) return 1;

        //User
        User userold = userRepository.findUserByCmnd(signUpRequest.getCmnd());
        if(userold != null) return 2;
        userold = userRepository.findUserBySdt(signUpRequest.getSdt());
        if(userold != null) return 3;
        userold = userRepository.findUserByEmail(signUpRequest.getEmail());
        if(userold != null) return 4;

        //NhaXe
        BusCompany nhaXe = nhaXeRepository.findNhaXeBySdt(signUpRequest.getSdt());
        if(nhaXe != null) return 3;

        //Admin
        Admin admin = adminRepository.findAdminByCmnd(signUpRequest.getCmnd());
        if(admin != null) return 2;
        admin = adminRepository.findAdminBySdt(signUpRequest.getSdt());
        if(admin != null) return 3;
        admin = adminRepository.findAdminByEmail(signUpRequest.getEmail());
        if(admin != null) return 4;
        return 5;
    }

    public int addTaiKhoanUser(SignUpRequest signUpRequest){

        int check = checkInfo(signUpRequest);
        if (check!=5) return check;

        Account taiKhoanNew= convertSignUpToTaiKhoan(signUpRequest);
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        taiKhoanNew.setPassword(password);
        Account taiKhoanAdd = taiKhoanRepository.save(taiKhoanNew);
        Account taiKhoanOfUsername = new Account();
        if(taiKhoanAdd != null)  {
            taiKhoanOfUsername = taiKhoanRepository.findTaiKhoanByUsername(taiKhoanAdd.getUsername());
        }
        User userNew = convertSignUpToUser(signUpRequest, taiKhoanOfUsername);
        User userAdd = userRepository.save(userNew);
        if (userAdd != null) return 5;
        else return 6;
    }

    public int addTaiKhoanNhaXe(SignUpRequest signUpRequest){

        int check = checkInfo(signUpRequest);
        if (check!=5) return check;

        BusCompany nhaXe = nhaXeRepository.findNhaXeByTenNhaXe(signUpRequest.getTenNhaXe());
        if (nhaXe != null) return 6;
        Account taiKhoanNew= convertSignUpToTaiKhoan(signUpRequest);
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        taiKhoanNew.setPassword(password);
        Account taiKhoanAdd = taiKhoanRepository.save(taiKhoanNew);
        BusCompany nhaXeNew = convertSignUpToNhaXe(signUpRequest,taiKhoanAdd);
        nhaXeRepository.save(nhaXeNew);
        return 5;
    }
    public int addTaiKhoanAdmin(SignUpRequest signUpRequest) {

        int check = checkInfo(signUpRequest);
        if (check != 5) return check;

        Account taiKhoanNew = convertSignUpToTaiKhoan(signUpRequest);
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        taiKhoanNew.setPassword(password);
        Account taiKhoanAdd = taiKhoanRepository.save(taiKhoanNew);
        Account taiKhoanOfAdmin = new Account();
        if (taiKhoanAdd != null) {
            taiKhoanOfAdmin = taiKhoanRepository.findTaiKhoanByUsername(taiKhoanAdd.getUsername());
        }
        Admin adminnew = convertSignUpToAdmin(signUpRequest, taiKhoanOfAdmin);
        Admin adminAdd = adminRepository.save(adminnew);
        if (adminAdd != null) return 5;
        else return 6;
    }


}
