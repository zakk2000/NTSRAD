package com.celizion.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.celizion.app.dao.SearchWifiDAO;
import com.celizion.app.model.search.Wifi;

/**
 * <pre>
 * com.celizion.app.dao.impl
 * SearchWifiDAOImpl.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 31.
 */
@Repository
public class SearchWifiDAOImpl implements SearchWifiDAO {

	@Autowired
	JdbcTemplate searchEngineJdbcTemplate;
	
	@Override
	public List<Wifi> selectWifi(String searchKeyword) {
		
		StringBuilder query = new StringBuilder("SELECT BSSID")
									.append(", SSID")
									.append(", LOC_X")
									.append(", LOC_Y")
									.append(", BUILDINGID")
									.append(", MAXRSSI")
									.append(" FROM WIFI_BUILDING_AP_DB_20161014_T")
									.append("WHERE MATCH('")
									.append(searchKeyword)
									.append("')");
		
		return searchEngineJdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<Wifi>());
	
	}

}
