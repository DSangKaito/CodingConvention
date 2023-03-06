package com.example.datvexe.controllers;

import com.example.datvexe.handler.CustomException;
import com.example.datvexe.models.Admin;
import com.example.datvexe.payloads.requests.AdminRequest;
import com.example.datvexe.payloads.responses.DataResponse;
import com.example.datvexe.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResponse getAll(){
        List<Admin> listAdmin = adminService.getAll();
        if (listAdmin==null) throw new CustomException("404","Khong co Admin nao ca!!!");
        return new DataResponse("200",listAdmin);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DataResponse getById(@PathVariable("id") String id){
        if (id==null) throw new CustomException("400", "Missing field!!!");
        Long adminId = Long.valueOf(id);
        Admin admin = adminService.getAdminById(adminId);
        if (admin==null) throw new CustomException("404", "Khong ton tai admin nhu yeu cau!!!");
        return new DataResponse("200",admin);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DataResponse updateAdmin(@PathVariable("id") String id,@RequestBody AdminRequest adminRequest){
        if (adminRequest == null || id == null) throw new CustomException("400", "Missing field!!!");
        Long adminId = Long.valueOf(id);
        DataResponse dataResponse = adminService.updateAdmin(adminRequest,adminId);
        if (dataResponse.getStatus().equals("1")) throw new CustomException("404", "Khong ton tai admin!!!");
        if (dataResponse.getStatus().equals("2")) throw new CustomException("404", "Khong ton tai tai khoan!!!");
        if (dataResponse.getStatus().equals("3")) throw new CustomException("404", "So dien thoai da ton tai!!!");
        if (dataResponse.getStatus().equals("4")) throw new CustomException("404", "Ten nha xe da ton tai!!!");
        if (dataResponse.getStatus().equals("5")) throw new CustomException("404", "Email da ton tai!!!");
        if (dataResponse.getStatus().equals("6")) throw new CustomException("404", "CMND da ton tai!!!");
        return dataResponse;
    }


}
