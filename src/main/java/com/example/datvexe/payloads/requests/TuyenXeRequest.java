package com.example.datvexe.payloads.requests;

import com.example.datvexe.common.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Setter
@Getter
public class TuyenXeRequest {
    private LocalDate ngayDi;
    private LocalTime gioDi;
    private String thoiGianHanhTrinh;
    private int giaVe;
    private Status trangThai;
    private String tenBenXeDi;
    private String tenBenXeDen;
    private String bienSoXe;
}
