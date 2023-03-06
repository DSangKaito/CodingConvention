package com.example.datvexe.services;

import com.example.datvexe.models.TypeOfBus;
import com.example.datvexe.payloads.requests.LoaiXeRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LoaiXeService {
    List<TypeOfBus> getAllLoaiXe();

    TypeOfBus getLoaiXeById(Long id);

    TypeOfBus addLoaiXe(LoaiXeRequest loaiXeRequest);
}
