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
@Table(name = "xe")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "biensoxe")
    private String bienSoXe;

    @ManyToOne
    @JoinColumn(name = "nhaxe_id",referencedColumnName = "id")
    @JsonManagedReference
    private BusCompany busCompany;

    @ManyToOne
    @JoinColumn(name = "loaixe_id",referencedColumnName = "id")
    @JsonManagedReference
    private TypeOfBus typeOfBus;

    @OneToMany(mappedBy="bus")
    @JsonIgnore
    private List<BusLine> busLines;

}
