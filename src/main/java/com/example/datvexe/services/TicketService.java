package com.example.ticketbooking.services;

import com.example.ticketBooking.models.Schedule;
import com.example.ticketBooking.models.Ticket;
import com.example.ticketBooking.payloads.requests.TicketRequest;
import com.example.ticketBooking.payloads.responses.DataResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TicketService {
    List<Ticket> getAllTicketByUserId(Long userId);

    List<Ticket> getAllTicketByScheduleId(Long schedule);
    DataResponse addTicket(TicketRequest ticketRequest);
    Ticket updateTicket(TicketRequest ticketRequest,Long ticketId);

    Long deleteTicket(Long ticketId);
}
