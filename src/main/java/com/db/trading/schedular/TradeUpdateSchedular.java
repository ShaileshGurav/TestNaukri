package com.db.trading.schedular;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.trading.service.TradingServiceImpl;

@Component
public class TradeUpdateSchedular {
	
	@Autowired
	private TradingServiceImpl tradeServiceImpl;
	
	private static final Logger log = LoggerFactory.getLogger(TradeUpdateSchedular.class);

	

	
   // planning to use the update query so that multiple record can be updated in one go  but getting an error
	// change frequncy as per need
	//*** Pls change it the frequncy ..for testing set to 1 min
	@Scheduled(cron = "${tradingupdate.frequency}")
	public void updateSchedular() {
		log.info(" Executed "+LocalDate.now());
		tradeServiceImpl.updateExpiryFlagOfTrade();
	}
	
	

}
