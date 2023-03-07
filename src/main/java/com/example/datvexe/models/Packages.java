package com.example.datvexe.models;
import com.example.datvexe.common.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "package")
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cannang")
    private Long weight;

    @Column(name = "gia")
    private int fee;

    @Column(name = "tennguoinhan")
    private String nameReceiver;

    @Column(name = "sdtnguoinhan")
    private String phoneReceiver;

    @Column(name = "email")
    private String email;

    @Column(name = "ngaydat")
    private LocalDate dateOder;

    @Column(name = "trangthai")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "tuyenxe_id",referencedColumnName = "id")
    @JsonIgnore
    private BusLine busLine;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
