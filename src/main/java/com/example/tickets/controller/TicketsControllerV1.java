package com.example.tickets.controller;

import com.example.tickets.controller.dto.TicketDTO;
import com.example.tickets.model.Ticket;
import com.example.tickets.model.TicketStatus;
import com.example.tickets.repository.TicketsRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/tickets")
public class TicketsControllerV1 {

    private final TicketsRepository ticketsRepository;


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Ticket addTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = new ModelMapper().map(ticketDTO, Ticket.class);
        ticket.setDateTime(LocalDateTime.now());
        return ticketsRepository.save(ticket);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Ticket> getTickets() {
        return ticketsRepository.findAll();
    }

    @GetMapping(path = "/statuses", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getTicketStatuses() {
        JSONObject json = new JSONObject();
        json.put("statuses", TicketStatus.values());
        return json.toString();
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ticket> updateTicket(@Valid @RequestBody TicketDTO ticketDTO,
                                               @PathVariable String id) {
        return ticketsRepository.findById(id)
                .map(ticket -> {
                    ticket.setTitle(ticketDTO.getTitle());
                    ticket.setStatus(ticketDTO.getStatus());
                    ticket.setDescription(ticketDTO.getDescription());
                    ticket.setDateTime(LocalDateTime.now());
                    return ResponseEntity.ok(ticketsRepository.save(ticket));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTicket(@PathVariable String id) {
        ticketsRepository.deleteById(id);
    }

}
