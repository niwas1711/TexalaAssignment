package com.Texala.Metro.Service;

import com.Texala.Metro.interfaces.Fare;


public class WeekdayFare implements Fare {
    @Override
    public String getName() {
        return WeekdayFare.class.toGenericString();
    }

    @Override
    public double getFarePerStation() {
        return 7;
    }
}