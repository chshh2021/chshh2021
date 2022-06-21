package com.lry.flight.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String from;
    private String to;
    private String date;
    private String airline;
    private String direct;
    private String passType;

}
