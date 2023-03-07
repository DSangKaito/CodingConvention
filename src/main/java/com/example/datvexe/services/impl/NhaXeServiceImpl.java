package com.example.datvexe.services.impl;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.BusCompany;
import com.example.datvexe.models.Account;
import com.example.datvexe.payloads.requests.NhaXeRequest;
import com.example.datvexe.payloads.requests.SignUpRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import com.example.datvexe.payloads.responses.NhaXeResponse;
import com.example.datvexe.repositories.BusCompanyRepository;
import com.example.datvexe.repositories.TaiKhoanRepository;
import com.example.datvexe.services.NhaXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NhaXeServiceImpl implements NhaXeService {


    @Autowired
    BusCompanyRepository nhaXeRepository;

    @Autowired
    CommonServiceImpl commonService;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    public Account convertNhaXeRequestToTaiKhoan(NhaXeRequest nhaXeRequest, Long nhaXeId){
        Account taiKhoan = taiKhoanRepository.findTaiKhoanByNhaXe_Id(nhaXeId);
        if (taiKhoan == null) return null;
        if (nhaXeRequest.getTrangThaiHoatDong()==null) nhaXeRequest.setTrangThaiHoatDong(Status.ACTIVE);
        taiKhoan.setTrangThaiHoatDong(nhaXeRequest.getTrangThaiHoatDong());
        return taiKhoan;
    }

    public BusCompany convertNhaXeRequestToNhaXe(NhaXeRequest nhaXeRequest, BusCompany nhaXe, Account taiKhoan){
        nhaXe.setTenNhaXe(nhaXeRequest.getTenNhaXe());
        nhaXe.setSdt(nhaXeRequest.getSdt());
        nhaXe.setMoTaNgan(nhaXeRequest.getMoTaNgan());
        nhaXe.setDiaChi(nhaXeRequest.getDiaChi());
        nhaXe.setTaiKhoan(taiKhoan);
        return nhaXe;
    }

    public SignUpRequest convertNhaXeRequestToSignUpRequest(NhaXeRequest nhaXeRequest){
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setSdt(nhaXeRequest.getSdt());
        signUpRequest.setMoTaNgan(nhaXeRequest.getMoTaNgan());
        signUpRequest.setDiaChi(nhaXeRequest.getDiaChi());
        signUpRequest.setTenNhaXe(nhaXeRequest.getTenNhaXe());
        signUpRequest.setTrangThaiHoatDong(nhaXeRequest.getTrangThaiHoatDong());
        signUpRequest.setRole(nhaXeRequest.getRole());
        return signUpRequest;
    }

    public NhaXeResponse convertNhaXeToNhaXeResponse(BusCompany nhaXe){
        NhaXeResponse nhaXeResponse = new NhaXeResponse();
        nhaXeResponse.setId(nhaXe.getId());
        nhaXeResponse.setSdt(nhaXe.getSdt());
        nhaXeResponse.setTenNhaXe(nhaXe.getTenNhaXe());
        nhaXeResponse.setMoTaNgan(nhaXe.getMoTaNgan());
        nhaXeResponse.setDiaChi(nhaXe.getDiaChi());
        nhaXeResponse.setTaiKhoanId(nhaXe.getTaiKhoan().getId());
        return nhaXeResponse;
    }

    @Override
    public List<BusCompany> getAll() {
        List<BusCompany> listNhaXe = nhaXeRepository.findAll();
        if (listNhaXe.size() == 0) return null;
        return listNhaXe;
    }

    @Override
    public List<NhaXeResponse> getAllForUser() {
        List<BusCompany> nhaXeList = nhaXeRepository.findNhaXesByTaiKhoanTrangThaiHoatDongLike(Status.ACTIVE);
        List<NhaXeResponse> nhaXeResponseList = new ArrayList<NhaXeResponse>();
        for (BusCompany nhaXe : nhaXeList) {
            nhaXeResponseList.add(convertNhaXeToNhaXeResponse(nhaXe));
        }
        return nhaXeResponseList;
    }

    @Override
    public BusCompany getNhaXeById(Long id) {
        BusCompany nhaXe = nhaXeRepository.findNhaXeById(id);
        if (nhaXe == null) return null;
        return nhaXe;
    }

    @Override
    public NhaXeResponse getNhaXeByIdForUser(Long id) {
        BusCompany nhaXe = nhaXeRepository.findNhaXeByIdAndTaiKhoanTrangThaiHoatDongLike(id, Status.ACTIVE);
        return convertNhaXeToNhaXeResponse(nhaXe);
    }

    @Override
    public DataResponse updateNhaXe(NhaXeRequest nhaXeRequest, Long nhaXeId) {
        BusCompany nhaXe = nhaXeRepository.findNhaXeById(nhaXeId);
        if (nhaXe == null) return new DataResponse("1","/");
        Account taiKhoanNew = convertNhaXeRequestToTaiKhoan(nhaXeRequest, nhaXeId);
        if (taiKhoanNew == null) return new DataResponse("2","/");
        BusCompany nhaXeNew = convertNhaXeRequestToNhaXe(nhaXeRequest, nhaXe, taiKhoanNew);
        int check = commonService.checkInForUpdateAccount(convertNhaXeRequestToSignUpRequest(nhaXeRequest),taiKhoanNew);
        if (check != 5) return new DataResponse(String.valueOf(check+2),"/");
        taiKhoanRepository.save(taiKhoanNew);
        BusCompany nhaXeUpdate= nhaXeRepository.save(nhaXeNew);
        return new DataResponse("200",nhaXeUpdate);
    }
}
