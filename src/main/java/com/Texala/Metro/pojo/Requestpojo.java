package com.Texala.Metro.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Requestpojo {

	SmartCard card;
	Station source;
	LocalDateTime dateTime;
	
}
