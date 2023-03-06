package com.example.datvexe.controllers;

import com.example.datvexe.common.Role;
import com.example.datvexe.config.CustomTaiKhoanDetails;
import com.example.datvexe.config.JwtTokenProvider;
import com.example.datvexe.models.Account;
import com.example.datvexe.payloads.requests.LoginRequest;
import com.example.datvexe.payloads.responses.LoginResponse;
import com.example.datvexe.repositories.AdminRepository;
import com.example.datvexe.repositories.BusCompanyRepository;
import com.example.datvexe.repositories.TaiKhoanRepository;
import com.example.datvexe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusCompanyRepository nhaXeRepository;

    @Autowired
    AdminRepository adminRepository;

    @PostMapping("/login")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long id = 0L;
        Account taiKhoan = taiKhoanRepository.findTaiKhoanByUsername(loginRequest.getUsername());
        if (taiKhoan.getRole() == Role.USER)
            id = taiKhoan.getUser().getId();
        else  if (taiKhoan.getRole() == Role.NHAXE)
            id = taiKhoan.getNhaXe().getId();
        else  if (taiKhoan.getRole() == Role.ADMIN)
            id = taiKhoan.getAdmin().getId();

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomTaiKhoanDetails) authentication.getPrincipal());

        return new LoginResponse(jwt,taiKhoan.getRole(),id,taiKhoan.getUsername());
    }
}
