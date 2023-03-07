package com.example.datvexe.repositories;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.BusCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusCompanyRepository extends JpaRepository<BusCompany, Long> {
    BusCompany findNhaXeBySdt(String sdt);
    BusCompany findNhaXeByTenNhaXe(String tenNhaXe);
    BusCompany findBusCompanyById(Long id);
    List<BusCompany> findNhaXesByTaiKhoanTrangThaiHoatDongLike(Status trangThai);
    BusCompany findNhaXeByIdAndTaiKhoanTrangThaiHoatDongLike(Long id, Status trangThai);
}
