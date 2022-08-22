package com.db.trading.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.db.trading.entity.TradeData;

@Repository
public interface TradeRespository extends JpaRepository<TradeData, Long> {

	@Query("select t1 from TradeData t1 where t1.version = (select max(t2.version) from TradeData t2 where t2.tradeId = :tradeId) and t1.tradeId=:tradeId")
	public List<TradeData> findByVersion(@Param("tradeId") String tradeId );
	
	//@Query("update TradeData t1 SET t1.expired= 'Y' WHERE t1.maturityDt < ?1")
	//public void updateExpiryFlag(String maturityDt);
}
