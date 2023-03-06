package com.example.datvexe.repositories;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.HangHoa;
import com.example.datvexe.models.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagesRepository extends JpaRepository<Package, Long> {
    Package findHangHoaById(Long id);

    List<Package> findHangHoaByUser_Id(Long userId);
    List<Package> findHangHoaByTuyenXe_Id(Long tuyenXeId);

    List<Packages> findPackagesByStatusOrStatus(Status status1, Status status2);

}
