package com.Texala.Metro.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.Texala.Metro.interfaces.Fare;

 

public class FareStrategyFactory {
	 static final Fare weekendStrategy = new WeekendFare();
	    static final Fare weekdayStrategy = new WeekdayFare();

	    public static Fare getFareStrategy(LocalDateTime localDateTime) {
	        if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY || localDateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
	            return weekendStrategy;
	        } else {
	            return weekdayStrategy;
	        }
	    }
}
