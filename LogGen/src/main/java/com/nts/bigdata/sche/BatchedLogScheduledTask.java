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
public class BatchedLogScheduledTask extends LogScheduledTask {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@Scheduled(fixedRateString = "${app.config.batched.scheduled}")
	public void makeLog() {
		
		for(int i = 0; i < 2; i++) {
        	
        	writeLog();
        	logger.info("1,35.868602,128.693423,4,0.000000,0.000000,81,2017-02-24 12:50:45,(1/3)");
        
        }
	
    }

}
