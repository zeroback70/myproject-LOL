package com.kyung.pms.handler;

import java.sql.Date;
import java.util.Iterator;
import java.util.ArrayList;
import com.kyung.pms.App;
import com.kyung.pms.domain.Board;
import com.kyung.util.Prompt;

public class BoardHandler<E> {

  private ArrayList<Board> boardList = new ArrayList<>();

  public void service(String number1) throws CloneNotSupportedException{

      while(true) {
        System.out.printf("【토끼마켓 / 토끼들 게시판 / %s】\n", number1);
        System.out.println("1. 게시물 등록하기");
        System.out.println("2. 게시물 목록보기");
        System.out.println("3. 상세하게 보기");
        System.out.println("4. 게시물 수정하기");
        System.out.println("5. 게시물 삭제하기");
        System.out.println("0. 이전 메뉴로 돌아가기");
        System.out.println("원하시는 번호를 눌러주세요!");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호입력(0~5)>> ");
        System.out.println();

        try {
          switch(command) {
            case "1" :
              this.add();
              break;
            case "2" :
              this.list();
              break;
            case "3" :
              this.detail();
              break;
            case "4" :
              this.update();
              break;
            case "5" :
              this.delete();
              break;
            case "0" :
              System.out.println("게시판으로 돌아갑니다.");
              System.out.println();
              App.chooseBoard();
            default :
              System.out.println("잘못된 메뉴 번호 입니다.");
              System.out.println();
  
          }
        }catch(Exception e) {
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
    }

  public void add() {
    System.out.println("[토끼들 게시판 > 새 게시글 등록]");

    Board b = new Board();

    b.setNumber(Prompt.inputInt("번호: "));
    b.setTitle(Prompt.inputString("제목: "));
    b.setContent(Prompt.inputString("내용: "));
    b.setWriter(Prompt.inputString("작성자: "));
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("게시글을 등록하겠습니다.");
    System.out.println();
  }

  public void list() throws CloneNotSupportedException{
    System.out.println("[토끼들 게시판 > 게시글 목록]");

    Iterator<Board> iterator = boardList.iterator();

    while(iterator.hasNext()) {
      Board b = iterator.next();

      System.out.printf("번호: %d 제목: %s 작성자: %s 등록일: %s\n조회수: %d 좋아요: %d\n",
          b.getNumber(), b.getTitle(), b.getWriter(), b.getRegisteredDate(), b.getViewCount(), b.getLike());
      System.out.println("-------------------------------------------------------------");

    }
  }

  public void detail() {
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

  public void update() {
    System.out.println("[게시글 수정하기]");

    Board board = findByNo(Prompt.inputInt("번호? "));
    if(board == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      String title = Prompt.inputString(String.format("제목(%s)? ",board.getTitle()));
      String content = Prompt.inputString(String.format("내용(%s)? ",board.getContent()));

      String usernumber1 = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(usernumber1.equalsIgnoreCase("y")) {
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

  public void delete() {
    System.out.println("[게시글 삭제]");

    int no = Prompt.inputInt("번호? ");
    Board board = findByNo(no);
    if(board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      String usernumber1 = Prompt.inputString("정말 삭제하시겠습니까?(Y/N) ");

      if(usernumber1.equalsIgnoreCase("Y")) {
        boardList.remove(board);

        System.out.println("게시글을 삭제하였습니다.");
        System.out.println();

      }else {
        System.out.println("게시글 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  private Board findByNo(int boardNo) {
    Board[] list = boardList.toArray(new Board[boardList.size()]);
    for(Board b : list) {
      if(b.getNumber() == boardNo) {
        return b;
      }
    }
    return null;
  }

}