package com.kyung.pms.handler;
// 2021-03-31 Update
import java.sql.Date;
import java.util.List;
import com.kyung.pms.domain.Board;
import com.kyung.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler {

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    System.out.println("[게시글 수정하기]");

    Board board = findByNo(Prompt.inputInt("번호? "));
    if(board == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      String title = Prompt.inputString(String.format("제목(%s)? ",board.getTitle()));
      String content = Prompt.inputString(String.format("내용(%s)? ",board.getContent()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(Y/N) ");
      if(userChoice.equalsIgnoreCase("Y")) {
        board.setTitle(title);
        board.setContent(content);
        Date reRegisteredDate = new Date(System.currentTimeMillis());
        board.setRegisteredDate(reRegisteredDate);
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }
}