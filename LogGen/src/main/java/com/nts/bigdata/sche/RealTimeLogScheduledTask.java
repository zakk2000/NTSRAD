package com.nts.bigdata.sche;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * com.nts.bigdata.sche
 * LogScheduledTasks.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 8.
 */
@Component
public class RealTimeLogScheduledTask extends LogScheduledTask {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@Scheduled(fixedRateString = "${app.config.realtime.scheduled}")
	public void makeLog() {
		
		for(int i = 0; i < 4; i++) {
        	
        	writeLog();
        	logger.info("1,37.565487,127.019299,4,0.000000,0.000000,29,2017-02-23 09:49:34,(1/1)");
        
        }
	
    }

}
