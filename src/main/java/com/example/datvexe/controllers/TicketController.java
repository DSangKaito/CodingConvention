package com.example.ticketbooking .controllers;

import com.example.ticketBooking.handler.CustomException;
import com.example.ticketBooking.models.HangHoa;
import com.example.ticketBooking.models.Ticket ;
import com.example.ticketBooking.payloads.requests.TicketRequest;
import com.example.ticketBooking.payloads.responses.DataResponse;
import com.example.ticketBooking.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/ticket ")
public class Ticket Controller {

    @Autowired
    Ticket Service BusLinesService;

    @GetMapping("/user-id/{id}")
    // get all ticket by user id, type id is Long
    public DataResponse getAllTicketByUserId(@PathVariable("id") Long id){
        if(id == null) throw new CustomException("400","Not found user!!!!");
        List<Ticket> ticket  = ticket Service.getAllTicketByUserId(id);
        if (ticket  == null) throw new CustomException("404", "Not found user!!!");
        // if not found ticket in List, throw message: Not found user.
        if (ticket .size() == 0) return new DataResponse("200", "You should booking!!!");
        return new DataResponse("200",ticket);
    }

    @GetMapping("/busLine-id/{id}")
    // get all ticket by BusLine id
    @PreAuthorize("hasAnyRole('BUS_COMPANY','ADMIN')")
    public DataResponse getAllTicketByBusLineId(@PathVariable("id") String id){
        if(id == null) throw new CustomException("400","Missing field!!!!");
        Long busLineId = Long.valueOf(id);
        List<Ticket> ticket  = Ticket Service.getAllTicketByBusLineId(busLineId);
        if (ticket  == null) throw new CustomException("404", "Not found BusLine!!!");
        if (ticket.size() == 0) return new DataResponse("200", "You should booking!!!");
        return new DataResponse("200",ticket );
    }

    @PostMapping("/add")
    // verify user type when login success.
    @PreAuthorize("hasAnyRole('USER','BUS_COMPANY')")
    // add ticket when user booking
    public DataResponse addTicket (@RequestBody TicketRequest ticketRequest){
        if (ticketRequest == null) throw new CustomException("400","Missing field!!!");
        DataResponse dataResponse = Ticket Service.addTicket (ticketRequest);
        if (dataResponse.getStatus()=="1") throw new CustomException("400", "Not found BusLine!!!");
        if (dataResponse.getStatus()=="2") throw new CustomException("400", "Not found user!!!");
        if (dataResponse.getStatus()=="3") throw new CustomException("400", "Ghe da duoc dat!!!");
        if (dataResponse.getStatus()=="4") throw new CustomException("400", "Dang ky khong thanh cong!!!");
        if (dataResponse.getStatus()=="5") return new DataResponse("200", dataResponse.getObject());
        return new DataResponse("200", dataResponse.getObject());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('BUS_COMPANY')")
    // update ticket information
    public DataResponse updateTicket (@PathVariable("id") String id,@RequestBody TicketRequest ticketRequest){
        if(Ticket Request == null || id==null)  throw  new CustomException("400","Missing field!!!");
        Long Ticket id = Long.valueOf(id);
        Ticket  ticket = Ticket Service.updateTicket(ticket Request,ticket id);
        if (ticket  == null) throw new CustomException("404","Updating information faild!!!");
        return new DataResponse("200",ticket );
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('USER','BUS_COMPANY')")
    // remove ticket
    public DataResponse deleteTicket (@PathVariable("id") String id){
        if (id == null) throw new CustomException("400","Missing id!!!");
        Long Ticket id = Long.valueOf(id);
        Ticket id = Ticket Service.deleteTicket (Ticket id);
        if(Ticket id == null) throw  new CustomException("404", "Not found ticket");
        return new DataResponse("200", "Xoa thanh cong ve xe id: " + Ticket id);
    }
}
