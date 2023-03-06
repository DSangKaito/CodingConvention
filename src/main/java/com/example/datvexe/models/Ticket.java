package com.example.ticketbooking.models;

import com.example.ticketBooking.common.payMethod;
import com.example.ticketBooking.common.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "seatNumber")
    private int seatNumber;

    @Column(name = "bookingDate")
    private LocalDate bookingDate;

    @Column(name = "pickDate")
    private LocalDate pickDate;

    @ManyToOne
    @JoinColumn(name = "schedule_id",referencedColumnName = "id")
    @JsonManagedReference
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @Column(name = "payMethod")
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Column(name = "verify")
    @Enumerated(EnumType.STRING)
    private Status status;
}
