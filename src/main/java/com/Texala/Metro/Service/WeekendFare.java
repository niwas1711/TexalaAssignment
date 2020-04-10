package com.Texala.Metro.Service;

import com.Texala.Metro.interfaces.Fare;

public class WeekendFare implements Fare {

    @Override
    public String getName() {
        return WeekendFare.class.toGenericString();
    }

    @Override
    public double getFarePerStation() {
        return 5.5;
    }
}