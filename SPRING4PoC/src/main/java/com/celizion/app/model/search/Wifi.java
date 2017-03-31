package com.celizion.app.model.search;

import org.pojomatic.annotations.AutoProperty;

import com.celizion.app.model.common.CommonModel;

/**
 * <pre>
 * com.celizion.app.search.model
 * Zipcode.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 28.
 */
@AutoProperty
public class Wifi extends CommonModel {

	private long id;
	private String zipcode;
	private String state;
	private String city;
	private String suburb;
	private String searchStr;
	
	public long getId() {
		return id;
	}
	public String getZipcode() {
		return zipcode;
	}
	public String getState() {
		return state;
	}
	public String getCity() {
		return city;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

}
