package com.example.datvexe.services.impl;

import com.example.datvexe.models.Evaluation;
import com.example.datvexe.payloads.requests.DanhGiaRequest;
import com.example.datvexe.repositories.EvaluationRepository;
import com.example.datvexe.repositories.BusCompanyRepository;
import com.example.datvexe.repositories.UserRepository;
import com.example.datvexe.services.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhGiaServiceImpl implements DanhGiaService {

    @Autowired
    EvaluationRepository danhGiaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusCompanyRepository nhaXeRepository;

    public Evaluation convertDanhGiaRequestToDanhGia(DanhGiaRequest danhGiaRequest, Evaluation danhGia){
        danhGia.setSoSao(danhGiaRequest.getSoSao());
        danhGia.setNoiDung(danhGiaRequest.getNoiDung());
        danhGia.setGioDang(danhGiaRequest.getGioDang());
        danhGia.setNgayDang(danhGiaRequest.getNgayDang());
        danhGia.setUser(userRepository.findUserById(danhGiaRequest.getUserId()));
        danhGia.setNhaXe(nhaXeRepository.findNhaXeById(danhGiaRequest.getNhaXeId()));
        return danhGia;
    }

    public Evaluation addDanhGia(DanhGiaRequest danhGiaRequest){
        Evaluation danhGia =  danhGiaRepository.findDanhGiaByUser_IdAndNhaXe_Id(danhGiaRequest.getUserId(),danhGiaRequest.getNhaXeId());
        if (danhGia == null) {
            danhGia = new Evaluation();
        }
        Evaluation danhGiaNew = convertDanhGiaRequestToDanhGia(danhGiaRequest,danhGia);
        if (danhGiaNew == null) return null;
        danhGiaNew = danhGiaRepository.save(danhGiaNew);
        return danhGiaNew;
    }

    @Override
    public Evaluation getDanhGiaById(Long id) {
        Evaluation danhGia = danhGiaRepository.findDanhGiaById(id);
        if (danhGia == null) return null;
        return danhGia;
    }

    @Override
    public List<Evaluation> getDanhGiaByNhaXeId(Long nhaXeId) {
        List<Evaluation> danhGia = danhGiaRepository.findDanhGiasByNhaXe_Id(nhaXeId);
        if (danhGia.size()==0) return null;
        return danhGia;
    }

    @Override
    public Long deleteDanhGia(Long id) {
        Evaluation danhGia = danhGiaRepository.findDanhGiaById(id);
        if (danhGia == null) return null;
        danhGiaRepository.delete(danhGia);
        return danhGia.getId();
    }
}
