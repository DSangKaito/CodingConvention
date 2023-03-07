package com.example.datvexe.services;

import com.example.datvexe.models.Bus;
import com.example.datvexe.payloads.requests.XeRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface XeService {
    List<Bus> getAll();

    List<Bus> getAllByNhaXeId(Long nhaXeId);

    Bus getById(Long id);

    DataResponse addXe(XeRequest value);
}
