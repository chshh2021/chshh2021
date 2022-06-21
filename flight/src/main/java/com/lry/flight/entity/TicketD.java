package com.lry.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketD {
    private String org;
    private String dst;
    private String date;
    private String airline;
    private String planeModel;
    private String passType;
    private String fullFareBasis; //true
    //FDResult
    //findPrice(String org, String dst, String date, String airline, String planeModel, String passType,boolean fullFareBasis)
}