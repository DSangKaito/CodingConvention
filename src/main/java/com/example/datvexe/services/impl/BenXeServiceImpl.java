package com.example.datvexe.services.impl;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.BusStation;
import com.example.datvexe.payloads.requests.BenXeRequest;
import com.example.datvexe.repositories.BenXeRepository;
import com.example.datvexe.services.BenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenXeServiceImpl implements BenXeService {
    @Autowired
    BenXeRepository benXeRepository;

    public BusStation convertBenXeRequestToBenXe(BenXeRequest benXeRequest, BusStation benXe) {
        benXe.setTinhThanh(benXeRequest.getTinhThanh());
        benXe.setTenBenXe(benXeRequest.getTenBenXe());
        benXe.setDiaChiChiTiet(benXeRequest.getDiaChiChiTiet());
        benXe.setTrangThai(benXeRequest.getTrangThai());
        return benXe;
    }

    public List<BusStation> findAllBenXeForUser() {
        return benXeRepository.findAllByTrangThai(Status.ACTIVE);
    }

    public List<BusStation> findAllBenXeForAdmin() {
        return benXeRepository.findAll();
    }

    public BusStation findBenXeById(Long id) {
        return benXeRepository.findOneById(id);
    }

    public BusStation addNewBenXe(BenXeRequest benXeRequest) {
        BusStation benXeCheck =benXeRepository.findBenXeByTenBenXeLike(benXeRequest.getTenBenXe());
        if (benXeCheck != null) return null;
        BusStation newBenXe = new BusStation();
        newBenXe = convertBenXeRequestToBenXe(benXeRequest, newBenXe);
        return benXeRepository.save(newBenXe);
    }

    public BusStation updateBenXe(BenXeRequest benXe, Long id) {
        BusStation newBenXe = benXeRepository.findOneById(id);
        if (newBenXe == null) return null;
        newBenXe = convertBenXeRequestToBenXe(benXe, newBenXe);
        return benXeRepository.save(newBenXe);
    }

    public Long deleteBenXe(Long id) {
        BusStation newBenXe = benXeRepository.findOneById(id);
        if (newBenXe == null) return null;
        benXeRepository.delete(newBenXe);
        return newBenXe.getId();
    }
}
