package com.example.datvexe.payloads.requests;

import com.example.datvexe.common.Role;
import com.example.datvexe.common.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Data
public class NhaXeRequest {
    private String tenNhaXe;
    private String sdt;
    private String moTaNgan;
    private String diaChi;
    @Enumerated(EnumType.STRING)
    private Status trangThaiHoatDong;
    @Enumerated(EnumType.STRING)
    private Role role;
}
