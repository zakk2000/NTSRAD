package com.celizion.app.dao;

import java.util.List;

import com.celizion.app.model.search.Wifi;

/**
 * <pre>
 * com.celizion.app.dao
 * SearchDAO.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 31.
 */
public interface SearchWifiDAO {

	List<Wifi> selectWifi(String searchKeyword);

}
