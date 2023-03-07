package com.example.datvexe.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "danhgia")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sosao")
    private int start;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "giodang")
    private LocalTime gioDang;

    @Column(name = "ngaydang")
    private LocalDate ngayDang;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "nhaxe_id", referencedColumnName = "id")
    @JsonManagedReference
    private BusCompany nhaXe;

}