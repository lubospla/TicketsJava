package com.example.tickets.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("tickets")
public class Ticket {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String title;
    private TicketStatus status;
    private String description;
    private LocalDateTime dateTime;


}
