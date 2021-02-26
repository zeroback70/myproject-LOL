package com.kyung.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.kyung.pms.domain.Board;

public class BoardListHandler extends AbstractBoardHandler {

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[토끼들 게시판 > 게시글 목록]");

    Iterator<Board> iterator = boardList.iterator();

    while(iterator.hasNext()) {
      Board b = iterator.next();

      System.out.printf("번호: %d 제목: %s 작성자: %s 등록일: %s\n조회수: %d 좋아요: %d\n",
          b.getNumber(), b.getTitle(), b.getWriter(), b.getRegisteredDate(), b.getViewCount(), b.getLike());
      System.out.println("-------------------------------------------------------------");
    }
  }
}