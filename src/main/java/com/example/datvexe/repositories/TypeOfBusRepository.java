package com.example.datvexe.repositories;

import com.example.datvexe.models.TypeOfBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfBusRepository extends JpaRepository<TypeOfBus, Long> {
    List<TypeOfBus> findAll();

    TypeOfBus findOneById(Long id);

    TypeOfBus save(TypeOfBus loaiXe);

    TypeOfBus findLoaiXeByTenLoaiXe(String tenLoaiXe);
}
