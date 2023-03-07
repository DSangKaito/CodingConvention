package com.example.datvexe.payloads.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StatisticsOderByAdminResponse {
    private Long busCompanyId;
    private String nameBusCompany;
    private int numberOfTicket;
    private int numberOfPackages;
    private float scaleTicket;
    private float scalePackages;
}
