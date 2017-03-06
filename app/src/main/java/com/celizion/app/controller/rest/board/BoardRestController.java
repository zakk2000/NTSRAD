package com.celizion.app.controller.rest.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.celizion.app.model.board.Board;
import com.celizion.app.service.board.BoardService;

/**
 * <pre>
 * com.celizion.app.controller.rest.board
 * BoardRestController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
@RestController
@RequestMapping(value = "/board")
public class BoardRestController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = {"/{srchFromYMDHM}/{srchToYMDHM}/{searchType}/{searchStr}"
								, "/{srchFromYMDHM}/{srchToYMDHM}"
								, "/"}, method = RequestMethod.GET)
	public List<Board> getBoardList(Board board) {
		
		return boardService.getBoardList(board);
	
	}

}
