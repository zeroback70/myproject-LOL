package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Board;
import com.kyung.util.Prompt;

public class BoardDetailHandler extends AbstractBoardHandler {

  public BoardDetailHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[토끼들 게시판 > 게시글 상세보기]");

    Board board = findByNo(Prompt.inputInt("번호? "));
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      board.setViewCount(board.getViewCount() + 1);
      System.out.printf("제목: %s ", board.getTitle());
      System.out.printf("작성자: %s\n", board.getWriter());
      System.out.printf("내용: %s\n", board.getContent());
      System.out.printf("등록일: %s ", board.getRegisteredDate());
      System.out.printf("조회수: %d\n", board.getViewCount());
      System.out.println("-------------------------------------------------------------");
      return;
    }
  }
}