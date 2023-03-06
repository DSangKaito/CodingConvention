package com.example.datvexe.controllers;

import com.example.datvexe.handler.CustomException;
import com.example.datvexe.models.BusStation;
import com.example.datvexe.payloads.requests.BenXeRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import com.example.datvexe.services.BenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/benxe")
public class BenXeController {

    @Autowired
    BenXeService benXeService;

    @GetMapping("/benxeid/{id}")
    public DataResponse getBenXeById(@PathVariable("id") String id) {
        if (id == null) throw new CustomException("400", "Missing field");
        Long benXeId = Long.valueOf(id);
        BusStation benXe = benXeService.findBenXeById(benXeId);
        if (benXe == null) throw new CustomException("404", "Khong tim thay ben xe!!!!");
        return new DataResponse("200", benXe);
    }

    @GetMapping("/all-admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DataResponse getAllForAdmin() {
        List<BusStation> benXeList = benXeService.findAllBenXeForAdmin();
        if (benXeList.size() == 0) throw new CustomException("404", "Khong co ben xe!!!");
        return new DataResponse("200", benXeList);
    }

    @GetMapping("/all-user")
    public DataResponse getAllForUser() {
        List<BusStation> benXeList = benXeService.findAllBenXeForUser();
        if (benXeList.size() == 0) throw new CustomException("404", "Khong co ben xe!!!");
        return new DataResponse("200", benXeList);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DataResponse addNewBenXe(@RequestBody BenXeRequest benXeRequest) {
        if (benXeRequest == null) throw  new CustomException("400", "Missing field");
        BusStation newBenXe = benXeService.addNewBenXe(benXeRequest);
        if (newBenXe==null) throw new CustomException("400","Ten ben xe da ton tai!!!");
        return new DataResponse("200", newBenXe);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DataResponse updateBenXe(@PathVariable("id") String id, @RequestBody BenXeRequest benXeRequest) {
        if (benXeRequest == null) throw  new CustomException("400", "Missing field");
        Long benXeId = Long.valueOf(id);
        BusStation benXe = benXeService.updateBenXe(benXeRequest,benXeId);
        if (benXe == null) throw new CustomException("404", "Khong tim thay ben xe!!!");
        return new DataResponse("200", benXe);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DataResponse deleteBenXe(@PathVariable("id") String id) {
        Long benXeId = Long.valueOf(id);
        benXeId = benXeService.deleteBenXe(benXeId);
        if(benXeId == null) throw  new CustomException("404", "Khong tim thay ben xe");
        return new DataResponse("200", "Xoa thanh cong ben xe id: " + benXeId);
    }
}