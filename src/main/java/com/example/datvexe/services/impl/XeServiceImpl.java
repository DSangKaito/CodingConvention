package com.example.datvexe.services.impl;

import com.example.datvexe.models.Bus;
import com.example.datvexe.payloads.requests.XeRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import com.example.datvexe.repositories.TypeOfBusRepository;
import com.example.datvexe.repositories.BusCompanyRepository;
import com.example.datvexe.repositories.BusRepository;
import com.example.datvexe.services.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XeServiceImpl implements XeService {
    @Autowired
    BusRepository xeRepository;

    @Autowired
    BusCompanyRepository nhaXeRepository;

    @Autowired
    TypeOfBusRepository loaiXeRepository;

    private DataResponse convertXeRequestToXe(XeRequest xeRequest, Bus xe){
        xe.setBienSoXe(xeRequest.getBienSoXe());
        if (nhaXeRepository.findNhaXeByTenNhaXe(xeRequest.getTenNhaXe())==null) return new DataResponse("1","/");
        xe.setNhaXe(nhaXeRepository.findNhaXeByTenNhaXe(xeRequest.getTenNhaXe()));
        if (loaiXeRepository.findLoaiXeByTenLoaiXe(xeRequest.getTenLoaiXe())==null) return new DataResponse("2","/");
        xe.setLoaiXe(loaiXeRepository.findLoaiXeByTenLoaiXe(xeRequest.getTenLoaiXe()));
        return new DataResponse("200",xe);
    }

    @Override
    public List<Bus> getAll() {
        List<Bus> listXe = xeRepository.findAll();
        if(listXe == null) return null;
        return listXe;
    }

    @Override
    public List<Bus> getAllByNhaXeId(Long nhaXeId) {
        List<Bus> listXe = xeRepository.findXeByNhaXe_Id(nhaXeId);
        if (listXe == null) return null;
        return listXe;
    }

    @Override
    public Bus getById(Long id) {
        Bus xe = xeRepository.findOneById(id);
        if(xe == null) return null;
        return xe;
    }

    @Override
    public DataResponse addXe(XeRequest xeRequest) {
        Bus xe = xeRepository.findXeByBienSoXe(xeRequest.getBienSoXe());
        if (xe != null) return new DataResponse("0","/");
        Bus xeNew = new Bus();
        DataResponse dataResponse = convertXeRequestToXe(xeRequest,xeNew);
        if (dataResponse.getStatus()=="1") return new DataResponse("1","/");
        if (dataResponse.getStatus()=="2") return new DataResponse("2","/");
        if (dataResponse.getStatus()=="200"){
            xeNew = xeRepository.save(xeNew);
        }
        return new DataResponse("200", xeNew);
    }


}
