package com.example.datvexe.repositories;
import com.example.datvexe.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiKhoanRepository extends JpaRepository<Account, Long> {

    Account findTaiKhoanById(Long id);
    List<Account> findAll();
    Account findTaiKhoanByUsername(String username);
    Account save(Account value);
    Account findTaiKhoanByAdmin_Id(Long id);
    Account findTaiKhoanByAdmin_Cmnd(String cmnd);
    Account findTaiKhoanByAdmin_Email(String email);
    Account findTaiKhoanByAdmin_Sdt(String sdt);
    Account findTaiKhoanByUser_Cmnd(String cmnd);
    Account findTaiKhoanByUser_Email(String email);
    Account findTaiKhoanByUser_Sdt(String sdt);
    Account findTaiKhoanByNhaXe_Sdt(String sdt);
    Account findTaiKhoanByNhaXe_TenNhaXe(String tenNhaXe);

    Account findTaiKhoanByNhaXe_Id(Long id);

    Account findTaiKhoanByUser_Id(Long userId);
}
