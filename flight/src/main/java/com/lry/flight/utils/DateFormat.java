package com.lry.flight.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateFormat {

    private String ZONE_NUM = "+8";

    public Long getMilliSecond() {
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of(ZONE_NUM));
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of(ZONE_NUM)).toEpochMilli();
        return milliSecond;
    }

}
