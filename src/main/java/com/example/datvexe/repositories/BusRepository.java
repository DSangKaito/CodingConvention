package com.example.datvexe.repositories;

import com.example.datvexe.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findOneById(Long id);
    List<Bus> findAll();
    Bus save(Bus value);

    List<Bus> findXeByNhaXe_Id(Long id);
    Bus findXeByBienSoXe(String bienSoXe);
}
