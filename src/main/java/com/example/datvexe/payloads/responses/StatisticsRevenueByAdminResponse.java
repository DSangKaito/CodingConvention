package com.example.datvexe.payloads.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StatisticsRevenueByAdminResponse {
    private Long busCompanyId;
    private String nameOfBusCompany;
    private float scaleRevenue;
    private int revenue;
}
