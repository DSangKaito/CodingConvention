package com.example.datvexe.models;

import com.example.datvexe.common.HinhThucThanhToan;
import com.example.datvexe.common.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name ="vexe")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "soghe")
    private int numberSeat;

    @Column(name = "ngaydat")
    private LocalDate dateOder;

    @Column(name = "ngaynhan")
    private LocalDate dateReceive;

    @ManyToOne
    @JoinColumn(name = "tuyenxe_id",referencedColumnName = "id")
    @JsonManagedReference
    private BusLine busLine;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @Column(name = "hinhthucthanhtoan")
    @Enumerated(EnumType.STRING)
    private HinhThucThanhToan hinhThucThanhToan;

    @Column(name = "xacthuc")
    @Enumerated(EnumType.STRING)
    private Status trangThai;
}
