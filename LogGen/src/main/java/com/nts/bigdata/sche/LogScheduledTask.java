package com.nts.bigdata.sche;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.nts.bigdata.comm.CommonUtilities;

/**
 * <pre>
 * com.nts.bigdata.sche
 * LogScheduledTask.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 9.
 */
public abstract class LogScheduledTask {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void writeLog() {
		
		String apMacAddr = CommonUtilities.makeApMacAddr(17);
		
		StringBuilder apName = new StringBuilder();
		apName.append("5G_T ");
		String[] tempStrArr = StringUtils.delimitedListToStringArray(apMacAddr, ":");
		apName.append((tempStrArr[tempStrArr.length - 2] != null) ? tempStrArr[tempStrArr.length - 2] : "");
		apName.append((tempStrArr[tempStrArr.length - 1] != null) ? tempStrArr[tempStrArr.length - 1] : "");
		
		int apRssi = CommonUtilities.makeNegativeNum(40, 90);
		
		StringBuilder logMsg = new StringBuilder();
		logMsg.append(CommonUtilities.getCurrentDateString("yyyyMMddHHmmssSSS", Locale.KOREA));
		logMsg.append(",");
		logMsg.append(apMacAddr);
		logMsg.append(",");
		logMsg.append(apName);
		logMsg.append(",");
		logMsg.append(apRssi);
		logMsg.append(",");
		logMsg.append("1");
		
		logger.info(logMsg.toString());
	
	}
	
	abstract void makeLog();

}
