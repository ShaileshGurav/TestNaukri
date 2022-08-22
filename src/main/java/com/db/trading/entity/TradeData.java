package com.db.trading.entity;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table
public class TradeData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	
    private String tradeId;

    private int version;

    private String counterParty;

    private String bookId;

    private LocalDate maturityDt;

    private LocalDate createdDt;

    private String expired;
    
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDt() {
		return maturityDt;
	}

	public void setMaturityDt(LocalDate maturityDt) {
		this.maturityDt = maturityDt;
	}

	public LocalDate getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}
    
    

}
