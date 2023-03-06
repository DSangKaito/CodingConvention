package com.example.datvexe.models;

import com.example.datvexe.common.Provider;
import com.example.datvexe.common.Role;
import com.example.datvexe.common.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "taikhoan")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "trangthaihoatdong")
    @Enumerated(EnumType.STRING)
    private Status trangThaiHoatDong;

    @OneToOne(mappedBy ="taiKhoan")
    @JsonBackReference
    private Admin admin;

    @OneToOne(mappedBy ="taiKhoan")
    @JsonBackReference
    private User user;

    @OneToOne(mappedBy ="taiKhoan")
    @JsonBackReference
    private BusCompany nhaXe;


}
