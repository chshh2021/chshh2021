package com.lry.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketB {
    private String org;
    private String dst;
    private String date;
    private String airline;
    private String direct;
    private String nostop;
    //getSchedule(String org, String dst, Date date, String airline, boolean direct, boolean nostop)
}
