package com.db.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradingTestDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingTestDbApplication.class, args);
	}

}
