package com.kyung.pms.handler;

import java.util.List;
import com.kyung.pms.domain.Board;

public abstract class AbstractBoardHandler implements Command{

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  protected Board findByNo(int boardNo) {
    Board[] list = boardList.toArray(new Board[boardList.size()]);
    for(Board b : list) {
      if(b.getNumber() == boardNo) {
        return b;
      }
    }
    return null;
  }

}
