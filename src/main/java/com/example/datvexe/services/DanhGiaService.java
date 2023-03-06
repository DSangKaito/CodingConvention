package com.example.datvexe.services;

import com.example.datvexe.models.Evaluation;
import com.example.datvexe.payloads.requests.DanhGiaRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DanhGiaService {
    Evaluation addDanhGia(DanhGiaRequest danhGiaRequest);
    Evaluation getDanhGiaById(Long id);
    List<Evaluation> getDanhGiaByNhaXeId(Long nhaXeId);

    Long deleteDanhGia(Long id);
}
