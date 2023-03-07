package com.example.datvexe.services;

import com.example.datvexe.payloads.requests.StatisticsByAdminRequest;
import com.example.datvexe.payloads.responses.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StatisticsService {
    float calculateAverageNumberOfEvaluation(Long busCompanyId);

    StatisticsEvaluationResponse doStatisticsEvaluationResponse(Long nhaXeId);

    List<AverageOfEvaluationResponse> getAllAverageOfEvaluation();

    List<StatisticsOderByAdminResponse> doStatisticsOderByAdmin(StatisticsByAdminRequest request);

    List<StatisticsRevenueByAdminResponse> getStatisticsRevenueByAdmin(StatisticsByAdminRequest statisticsByAdminRequest);


}
