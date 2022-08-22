package com.db.trading;

import org.junit.jupiter.api.Test;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import com.db.trading.schedular.TradeUpdateSchedular;
import com.db.trading.service.TradingServiceImpl;

@SpringJUnitConfig(TradingTestDbApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeUpdateSchedularTest {
	
	
	@SpyBean 
    private TradeUpdateSchedular TradeUpdateSchedular;
	
	
    @Test
    public void whenWaitTwoMinutes_thenScheduledIsCalledAtLeastTwoTimes() {
        await()
          .atMost(Duration.TWO_MINUTES)
          .untilAsserted(() -> verify(TradeUpdateSchedular, atLeast(2)).updateSchedular());
    }

}
