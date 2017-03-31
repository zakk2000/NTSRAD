package com.celizion.app.service.search.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celizion.app.dao.SearchWifiDAO;
import com.celizion.app.model.search.Wifi;
import com.celizion.app.service.search.SearchWifiService;

/**
 * <pre>
 * com.celizion.app.service.search.impl
 * ZipcodeServiceImpl.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 28.
 */
@Service
@Transactional(readOnly = true, transactionManager = "searchEngineTransactionManager")
public class SearchWifiServiceImpl implements SearchWifiService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SearchWifiDAO searchWifiDAO;
	
	@Override
	public List<Wifi> searchWifi(String searchKeyword) {
		
		logger.debug("PARAM CHECK >>>>>>>>>>>>>> {}", searchKeyword);
		
		return searchWifiDAO.selectWifi(searchKeyword);
	
	}

}
