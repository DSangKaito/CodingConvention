package com.example.datvexe.payloads.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StatisticsEvaluationResponse {
    private int start1;
    private int start2;
    private int start3;
    private int start4;
    private int start5;
    private int numberOfEvaluations;
}
