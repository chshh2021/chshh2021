package com.lry.flight.mesage;

import java.io.Serializable;

public class FlightMessage implements Serializable {

    public static final String QUEUE = "QUEUE_FLIGHT_01";

    public static final String EXCHANGE = "EXCHANGE_FLIGHT_01";

    public static final String ROUTING_KEY = "ROUTING_KEY_FLIGHT_01";

    /**
     * 编号
     */
    private Integer id;

    public FlightMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FlightMessage{" +
                "id=" + id +
                '}';
    }

}
