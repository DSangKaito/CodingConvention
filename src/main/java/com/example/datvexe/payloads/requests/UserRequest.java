package com.example.datvexe.payloads.requests;

import com.example.datvexe.common.Role;
import com.example.datvexe.common.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Getter
@Setter
public class UserRequest {
    private String hoTen;
    private String cmnd;
    private String sdt;
    private String email;
    private String diaChi;
    @Enumerated(EnumType.STRING)
    private Status trangThaiHoatDong;
    @Enumerated(EnumType.STRING)
    private Role role;
}
