package com.db.trading.exception;

public class TradeExceptions extends RuntimeException {

	private String msg;
	public TradeExceptions(String msg) {
		super(msg);
		this.msg=msg;
	}
}
