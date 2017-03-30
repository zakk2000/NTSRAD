package com.celizion.app.controller.rest.zipcode;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.celizion.app.model.search.Search;
import com.celizion.app.service.search.SearchService;

/**
 * <pre>
 * com.celizion.app.controller.rest.zipcode
 * SearchRestController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 30.
 */
@RestController
@RequestMapping(value = "/search")
public class SearchRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SearchService zipcodeService;
	
	@RequestMapping(value = "/{searchStr}", method = RequestMethod.GET)
	public List<Search> getAddr(Search search) {
		
		logger.debug("PARAMETER CHECK in CONTROLLER >>>>>>>>>>>>>>>>>>>>> {}", search.getSearchStr());
		return zipcodeService.getAddr(search);
	
	}

}
