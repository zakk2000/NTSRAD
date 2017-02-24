package com.celizion.app.service.board;

import java.util.List;

import com.celizion.app.model.board.Board;

/**
 * <pre>
 * com.celizion.app.service
 * BoardService.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
public interface BoardService {

	List<Board> getBoardList(Board board);

}
