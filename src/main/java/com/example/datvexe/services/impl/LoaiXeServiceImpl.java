package com.example.datvexe.services.impl;

import com.example.datvexe.models.TypeOfBus;
import com.example.datvexe.payloads.requests.LoaiXeRequest;
import com.example.datvexe.repositories.TypeOfBusRepository;
import com.example.datvexe.services.LoaiXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiXeServiceImpl implements LoaiXeService {

    @Autowired
    TypeOfBusRepository loaiXeRepository;

    public TypeOfBus convertLoaiXeRequestToLoaiXe(LoaiXeRequest loaiXeRequest, TypeOfBus loaiXe){
        loaiXe.setTenLoaiXe(loaiXeRequest.getTenLoaiXe());
        loaiXe.setSucChua(loaiXeRequest.getSucChua());
        return loaiXe;
    }

    public List<TypeOfBus> getAllLoaiXe(){
      List<TypeOfBus> listLoaiXe =   loaiXeRepository.findAll();
      if(listLoaiXe.size() == 0) return null;
      return listLoaiXe;
    }

    public TypeOfBus getLoaiXeById(Long id) {
        TypeOfBus loaiXe = loaiXeRepository.findOneById(id);
        if(loaiXe == null) return null;
        return loaiXe;
    }

    public TypeOfBus addLoaiXe(LoaiXeRequest loaiXeRequest){
        TypeOfBus loaiXe = loaiXeRepository.findLoaiXeByTenLoaiXe(loaiXeRequest.getTenLoaiXe());
        if(loaiXe != null) return null;
        TypeOfBus loaiXeNew = new TypeOfBus();
        loaiXeNew = convertLoaiXeRequestToLoaiXe(loaiXeRequest,loaiXeNew);
        return loaiXeRepository.save(loaiXeNew);
    }
}
