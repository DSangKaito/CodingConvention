package com.example.datvexe.models;
import com.example.datvexe.common.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="tuyenxe")
public class BusLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ngaydi")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateStarting;

    @Column(name = "giodi")
    private LocalTime timeStarting;

    @Column(name = "thoigianhanhtrinh")
    private String timeOfTrip;

    @Column(name = "giave")
    private int fare;

    @Column(name = "trangthai")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "benxedi_id", referencedColumnName = "id")
    @JsonManagedReference
    private BusStation busStationStarting;
    @ManyToOne
    @JoinColumn(name = "benxeden_id", referencedColumnName = "id")
    @JsonManagedReference
    private BusStation busStationEnding;

    @OneToMany(mappedBy="busLine")
    @JsonIgnore
    private List<Ticket> ticket;

    @OneToMany(mappedBy="busLine")
    @JsonBackReference
    private List<Packages> packages;

    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @JsonManagedReference
    private Bus bus;

}
