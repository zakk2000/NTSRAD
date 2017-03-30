package com.celizion.app.service.search.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celizion.app.model.search.Search;
import com.celizion.app.service.search.SearchService;

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
public class SearchServiceImpl implements SearchService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Wifi> getSearchContents(Search search) {
		
		logger.debug("PARAM CHECK >>>>>>>>>>>>>> {}", search.getSearchStr());
		
		return zipcodeMapper.searchZipcode(map);
	
	}

}
