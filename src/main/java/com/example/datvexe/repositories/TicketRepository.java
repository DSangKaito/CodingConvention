package com.example.datvexe.repositories;

import com.example.datvexe.common.Status;
import com.example.datvexe.models.BusLine;
import com.example.datvexe.models.User;
import com.example.datvexe.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> getVeXeByUser(User user);

    Ticket findVeXeByTuyenXe_IdAndSoGhe(Long tuyenXeId, int soGhe);

    List<Ticket> findVeXeByTuyenXe(BusLine tuyenXe);
    Ticket findVeXeById(Long veXeId);

    List<Ticket> findTiketByStatusOrStatus(Status status1, Status status2);
}
