package com.example.datvexe.models;

import com.example.datvexe.common.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "benxe")
public class BusStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenbenxe")
    private String tenBenXe;

    @Column(name = "diachichitiet")
    private String diaChiChiTiet;

    @Column(name = "tinhthanh")
    private String tinhThanh;

    @Column(name = "trangthai")
    @Enumerated(EnumType.STRING)
    private Status trangThai;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy ="benXeDi")
    @JsonBackReference
    private List<TuyenXe> tuyenXeDi;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy ="benXeDen")
    @JsonBackReference
    private List<TuyenXe> tuyenXeDen;
}