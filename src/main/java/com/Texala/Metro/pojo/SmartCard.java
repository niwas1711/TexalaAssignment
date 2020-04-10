package com.Texala.Metro.pojo;

import lombok.Data;

@Data
public class SmartCard {

	long id;

    Traveller traveller;
    double balance;
}
