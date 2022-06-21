package com.lry.flight.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private Integer id;
    private Integer num;
    private String name;
    private String time;

}
