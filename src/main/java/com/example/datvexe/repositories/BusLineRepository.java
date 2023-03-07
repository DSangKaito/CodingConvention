package com.example.datvexe.repositories;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.BusStation;
import com.example.datvexe.models.TuyenXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusLineRepository extends JpaRepository<TuyenXe, Long> {

    TuyenXe findOneById(Long id);

//    Long deleteTuyenXeById(Long id);

    List<TuyenXe> findAll();

    //    List<TuyenXe> findTuyenXeByBenXeDiBenXeDenNgayDi(BenXe benXeDi, BenXe benXeDen, Date thoiGianKhoiHanh);
    List<TuyenXe> findTuyenXeByBenXeDiLikeAndBenXeDenLikeAndNgayDiLike(BusStation benXeDi, BusStation benXeDen, LocalDate ngayDi);

    List<TuyenXe> findTuyenXeByBenXeDi_TinhThanhContainsAndBenXeDen_TinhThanhContains(String a,String b);

    List<TuyenXe> findTuyenXeByBenXeDi_TinhThanhContainsAndBenXeDen_TinhThanhContainsAndAndNgayDiLike(String a,String b, LocalDate date);

    List<TuyenXe> findTuyenXeByTrangThaiOrTrangThai(Status trangThai1, Status trangThai2);

}

