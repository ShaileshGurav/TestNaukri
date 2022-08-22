package com.db.trading;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.db.trading.controller.TradeController;
import com.db.trading.entity.TradeData;
import com.db.trading.exception.TradeExceptions;
import com.db.trading.service.TradingServiceImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest

class TradingTestDbApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	@Autowired 
	private TradeController tradeController;
	

	
	List<TradeData> tradeList = new ArrayList<TradeData>();
	
	TradeData tradeData;
	
	/*
	 * @Before(value = "") public void setUp() throws Exception { tradeData = new
	 * TradeData(); tradeData.setTradeId("T1"); tradeData.setVersion(1);
	 * tradeData.setBookId("B1"); tradeData.setCounterParty("CP-1");
	 * tradeData.setExpired("Y"); tradeData.setMaturityDt(LocalDate.now());
	 * tradeData.setCreatedDt(LocalDate.now());
	 * 
	 * tradeData = new TradeData(); tradeData.setTradeId("T2");
	 * tradeData.setVersion(1); tradeData.setBookId("B1");
	 * tradeData.setCounterParty("CP-1"); tradeData.setExpired("Y");
	 * tradeData.setMaturityDt(LocalDate.now());
	 * tradeData.setCreatedDt(LocalDate.now()); }
	 */
	
	@Test
	void testNewTrade_sucessful() {
		TradeData testData = DataCreation("T1", 1 ,"B1" ,"Cp-1","N" ,LocalDate.now(),LocalDate.now());
		TradeData tradeData =tradeController.saveTrade(testData);
		tradeList.add(tradeData);
		
		Assertions.assertEquals(tradeData.getTradeId(),tradeData.getTradeId());
		Assertions.assertEquals(tradeData.getVersion(),tradeData.getVersion());
	}
	
	@Test
	void testInvalidTrade_wrongMaturityDate_fail() throws TradeExceptions {
		TradeData testData=null;
		try {
			 testData = DataCreation("T1", 1 ,"B1" ,"Cp-1","N" ,LocalDate.now(),getLocalDate(2022,8,21));
			tradeController.saveTrade(testData);
			
		} catch (TradeExceptions e) {
			Assertions.assertEquals(" Maturity date is invalid: "+testData.getTradeId(), e.getMessage());
			Assertions.assertEquals(tradeList.size(), tradeController.findAllTrades().size());
		}
		
	}
	
	@Test
	void testInvalidTrade_wrongVersionNo_fail() throws TradeExceptions {
		TradeData testData=null;
		try {
			 testData = DataCreation("T1", 0 ,"B1" ,"Cp-1","N" ,LocalDate.now(),LocalDate.now());
		   tradeController.saveTrade(testData);
			
		} catch (TradeExceptions e) {
			Assertions.assertEquals("Incorrect Version Id for given Trade:"+testData.getTradeId(), e.getMessage());
		}
		
	}
	
	@Test
	void testInvalidTrade_CorrectVersion_sucessful() throws TradeExceptions {
		TradeData testData=null;
		
			 testData = DataCreation("T1", 2 ,"B1" ,"Cp-1","N" ,LocalDate.now(),LocalDate.now());
		   tradeController.saveTrade(testData);
		   TradeData tradeData =tradeController.saveTrade(testData);
			tradeList.add(tradeData);
			
			Assertions.assertEquals(tradeData.getTradeId(),tradeData.getTradeId());
			Assertions.assertEquals(tradeData.getVersion(),tradeData.getVersion());
			
		
		
	}
	
	private TradeData DataCreation(String tradeId, int version , String bookId ,String counterParty,String expired,LocalDate createDt,LocalDate maturityDt) {
		tradeData = new TradeData();
		tradeData.setTradeId(tradeId);
		tradeData.setVersion(version);
		tradeData.setBookId(bookId);
		tradeData.setCounterParty(counterParty);
		tradeData.setExpired(expired);
		tradeData.setMaturityDt(maturityDt!=null ? maturityDt : LocalDate.now());
		tradeData.setCreatedDt(LocalDate.now());
		 return tradeData;
	}
	
	public static LocalDate getLocalDate(int year,int month, int day){
		LocalDate localDate = LocalDate.of(year,month,day);
		return localDate;
	}

	

}
