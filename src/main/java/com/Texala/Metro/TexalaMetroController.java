package com.Texala.Metro;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Texala.Metro.Exceptions.InsufficientCardBalance;
import com.Texala.Metro.Exceptions.MinimumCardBalanceException;
import com.Texala.Metro.Service.MetroService;
import com.Texala.Metro.pojo.Requestpojo;

 
 

@RestController("/MetroApi")
public class TexalaMetroController {

	@Autowired
	private MetroService mtrx;
	
	
	@PostMapping("/swipeIn")
	public ResponseEntity<String> swipeIn(@RequestBody Requestpojo MetroswipeInrequest ) throws MinimumCardBalanceException
	{
		mtrx.swipeIn(MetroswipeInrequest.getCard(), MetroswipeInrequest.getSource(), MetroswipeInrequest.getDateTime());
		return new ResponseEntity<>("Door Open", HttpStatus.OK);
		
	}
	
	@PostMapping("/swipeOut")
	public ResponseEntity<String> swipeOut(@RequestBody Requestpojo Metroswipeoutrequest ) throws InsufficientCardBalance
	{
		mtrx.swipeOut(Metroswipeoutrequest.getCard(), Metroswipeoutrequest.getSource(), Metroswipeoutrequest.getDateTime());
		return new ResponseEntity<>("Door Open", HttpStatus.OK);
		
	}
	
}
