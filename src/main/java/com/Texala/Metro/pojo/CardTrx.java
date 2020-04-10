package com.Texala.Metro.pojo;

import java.time.LocalDateTime;

import com.Texala.Metro.interfaces.Fare;

import lombok.Data;

@Data
public class CardTrx {

	long id;

    SmartCard card;

    Station source;
    Station destination;

    int distance;

    LocalDateTime startTime;
    LocalDateTime endTime;

    double balance;
    double fare;

    Fare fareStrategyUsed;
}
