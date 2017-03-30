package com.celizion.app.service.search;

import java.util.List;

import com.celizion.app.model.search.Search;

/**
 * <pre>
 * com.celizion.app.service.search
 * SearchService.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 30.
 */
public interface SearchService {

	List<Search> getSearchContents(Search search);

}
