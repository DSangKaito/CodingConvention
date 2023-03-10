package com.example.datvexe.repositories;

import com.example.datvexe.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Evaluation findDanhGiaByUser_IdAndNhaXe_Id(Long userId, Long nhaXeId);
    Evaluation findDanhGiaById(Long id);
    List<Evaluation> findEvaluationsByBusCompany_Id(Long busCompanyId);

}
