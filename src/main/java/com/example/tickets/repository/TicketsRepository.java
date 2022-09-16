package com.example.tickets.repository;

import com.example.tickets.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends MongoRepository<Ticket, String> {
}
