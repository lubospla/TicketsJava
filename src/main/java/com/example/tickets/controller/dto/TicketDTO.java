package com.example.tickets.controller.dto;

import com.example.tickets.model.TicketStatus;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class TicketDTO {

    @NotBlank
    private String title;
    @NotBlank
    private TicketStatus status;
    private String description;
}
