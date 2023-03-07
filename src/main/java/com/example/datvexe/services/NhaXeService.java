package com.example.datvexe.services;

import com.example.datvexe.models.BusCompany;
import com.example.datvexe.payloads.requests.NhaXeRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import com.example.datvexe.payloads.responses.NhaXeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NhaXeService {
    List<BusCompany> getAll();
    List<NhaXeResponse> getAllForUser();
    BusCompany getNhaXeById(Long id);

    NhaXeResponse getNhaXeByIdForUser(Long id);
    DataResponse updateNhaXe(NhaXeRequest nhaXeRequest, Long nhaXeId);
}
