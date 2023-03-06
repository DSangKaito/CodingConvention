package com.example.datvexe.payloads.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AverageOfEvaluationResponse {
    private Long idBusCompany;
    private Float averageOfEvaluation;
}
