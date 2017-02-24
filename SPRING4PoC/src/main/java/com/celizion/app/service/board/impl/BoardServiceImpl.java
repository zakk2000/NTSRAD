package com.celizion.app.service.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celizion.app.mapper.board.BoardMapper;
import com.celizion.app.model.board.Board;
import com.celizion.app.service.board.BoardService;

/**
 * <pre>
 * com.celizion.app.service.impl
 * BoardServiceImpl.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
@Service
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> getBoardList(Board board) {
		
		return boardMapper.selectBoardList(board);
	
	}

}
