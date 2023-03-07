package com.example.datvexe.models;

import com.example.datvexe.common.HinhThucThanhToan;
import com.example.datvexe.common.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name ="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numberSeat")
    private int numberSeat;

    @Column(name = "dateOrder")
    private LocalDate dateOrder;

    @Column(name = "dateReceive")
    private LocalDate dateReceive;

    @ManyToOne
    @JoinColumn(name = "busLine",referencedColumnName = "id")
    @JsonManagedReference
    private BusLine busLine;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @Column(name = "payMethod")
    @Enumerated(EnumType.STRING)
    private Payments payments;

    @Column(name = "verify")
    @Enumerated(EnumType.STRING)
    private Status status;
}
