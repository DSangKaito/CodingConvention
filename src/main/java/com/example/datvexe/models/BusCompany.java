package com.example.datvexe.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "nhaxe")
public class BusCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tennhaxe")
    private String nameBusCompany;

    @Column(name = "sdt")
    private String phoneNumber;

    @Column(name = "motangan")
    private String description;

    @Column(name = "diachi")
    private String address;


    @OneToOne
    @JoinColumn(name = "taikhoan_id",referencedColumnName = "id")
    @JsonManagedReference
    private Account account;

    @OneToMany(mappedBy ="nhaXe")
    @JsonIgnore
    private List<Bus> buses;

    @OneToMany(mappedBy ="nhaXe")
    @JsonIgnore
    private List<Evaluation> evaluations;
}