package com.db.trading.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.trading.entity.TradeData;
import com.db.trading.schedular.TradeUpdateSchedular;
import com.db.trading.service.TradingServiceImpl;

@RestController
@RequestMapping("/tradeApp")
public class TradeController {
	
	private static final Logger log = LoggerFactory.getLogger(TradeUpdateSchedular.class);
	
	@Autowired
	private TradingServiceImpl tradingService;
	
 	@PostMapping("/trades")
 	public TradeData saveTrade(@RequestBody TradeData tradeData){
 	
 		
 		return tradingService.addTrade(tradeData);
 	}
 	
 	
 	
 	@GetMapping("/trades")
 	public List<TradeData> findAllTrades() {
 		
 		return tradingService.getAllTrades();
 	}

 	/*@GetMapping("/trades/{tradeId}")
 	public TradeData findAllTrades(@PathVariable("tradeId") String tradeId) {
 		
 		return tradingService.findByTradeId(tradeId);
 	}*/
}
