package com.example.datvexe.services;

import com.example.datvexe.models.BusStation;
import com.example.datvexe.payloads.requests.BenXeRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BenXeService {

    List<BusStation> findAllBenXeForUser();

    List<BusStation> findAllBenXeForAdmin();

    BusStation findBenXeById(Long id);

    BusStation addNewBenXe(BenXeRequest benXeRequest);

    BusStation updateBenXe(BenXeRequest benXeRequest, Long id);

    Long deleteBenXe(Long id);
}
