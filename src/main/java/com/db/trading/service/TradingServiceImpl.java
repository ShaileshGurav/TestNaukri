package com.db.trading.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.trading.entity.TradeData;
import com.db.trading.exception.TradeExceptions;
import com.db.trading.repository.TradeRespository;

import antlr.Version;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class TradingServiceImpl {
	
	@Autowired 
	private TradeRespository tradeRespository;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	/*
	 * If TradeId exist then check the version 
	 * 			if version > old version then add new Trade record
	 * 			if version == old version then update same Trade record
	 * 			if version < old version throw an error
	 * Else if add TradeId in Table
	 */
	public TradeData addTrade(TradeData tradeData) {
		System.out.println(":::::::::"+tradeData.getTradeId());
		if(!validateMaturityDate(tradeData))
				throw new TradeExceptions(" Maturity date is invalid: "+tradeData.getTradeId());
		List<TradeData> exsitingTrade = tradeRespository.findByVersion(tradeData.getTradeId());
		if(exsitingTrade.size() ==1) {
			// record exist
			if(validateVersion(tradeData,exsitingTrade.get(0))==1) {
				// 1 . version > old version  .. new entry for same TradeId
				return tradeRespository.save(tradeData);
			}else if(validateVersion(tradeData,exsitingTrade.get(0))==0) {
				//version == old Version hence update existing for same TradeId
				exsitingTrade.get(0).setBookId(tradeData.getBookId());
				exsitingTrade.get(0).setCounterParty(tradeData.getCounterParty());
				exsitingTrade.get(0).setExpired(tradeData.getExpired());
				exsitingTrade.get(0).setMaturityDt(tradeData.getMaturityDt());
				return tradeRespository.save(exsitingTrade.get(0));
			} else if (validateVersion(tradeData,exsitingTrade.get(0))==2) {
				//version < old Version .error
				throw new TradeExceptions("Incorrect Version Id for given Trade:"+tradeData.getTradeId());
			}
		}
		//no record ..new entry always
		 return tradeRespository.save(tradeData);
	}
	
	public List<TradeData> getAllTrades() {
		List<TradeData> lst = new ArrayList<TradeData>();
		lst.addAll(tradeRespository.findAll());
		System.out.println(lst.size());
		return lst;
	}
	
	private int  validateVersion(TradeData tradeData, TradeData oldTradeData) {
		
		if(tradeData.getVersion() > oldTradeData.getVersion()){
            return 1;
        }else if (tradeData.getVersion() == oldTradeData.getVersion()) {
        	return 0;
        }else if(tradeData.getVersion() < oldTradeData.getVersion()){
            return 2;
        }
        return -1;
	
	}
	
	private boolean validateMaturityDate(TradeData tradeData) {
		
	  return tradeData.getMaturityDt().isBefore(LocalDate.now())  ? false:true;
	}

	
	// wanted to use update query so that multiple records can be updated in one go
	// but getting issue in '<'
	//  Hence using below approach ..to update record one by one
	public void updateExpiryFlagOfTrade(){
	     
	       // String maturityDt = dateFormat.format(LocalDate.now());
	        
	       //  tradeRespository.updateExpiryFlag(maturityDt);
		// update  TradeData set expired=Y where maturityDt < todayDate 
		//----------------------------------------------
		
		tradeRespository.findAll().stream().forEach(t -> {
            if (!validateMaturityDate(t)) {
                t.setExpired("Y");
              
                tradeRespository.save(t);
            }
        });
		
	     }

	
}
