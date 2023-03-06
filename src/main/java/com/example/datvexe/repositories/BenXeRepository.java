package com.example.datvexe.repositories;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.BusStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenXeRepository extends JpaRepository<BusStation, Long> {
    List<BusStation> findAll();
    List<BusStation> findAllByTrangThai(Status trangThai);
    BusStation findOneById(Long id);

    BusStation save(BusStation value);

    BusStation findBenXeByTenBenXeLike(String tenBenXe);
}
