package com.Texala.Metro.Service;

import java.time.LocalDateTime;

import com.Texala.Metro.interfaces.Fare;
import com.Texala.Metro.pojo.Station;

public class FareCalculator {
	 public double getFare(Station source, Station destination, LocalDateTime localDateTime) {
	        Fare fareStrategy = FareStrategyFactory.getFareStrategy(localDateTime);
	        int distance = source.distance(destination);

	        double fare = distance * fareStrategy.getFarePerStation();

	        return fare;
	    }
}
