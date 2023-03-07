package com.example.datvexe.services;

import com.example.datvexe.models.Ticket;
import com.example.datvexe.payloads.requests.VeXeRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeXeService {
    List<Ticket> getAllVeXeByUserId(Long userId);

    List<Ticket> getAllVeXeByTuyenXeId(Long tuyenXe);
    DataResponse addVeXe(VeXeRequest veXeRequest);
    Ticket updateVeXe(VeXeRequest veXeRequest, Long veXeId);

    Long deleteVeXe(Long veXeId);
}
