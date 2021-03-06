package com.kyung.pms.handler;

import java.util.ArrayList;
import java.util.HashMap;
import com.kyung.pms.App;
import com.kyung.pms.domain.Board;

public class BoardServiceReview {

  ArrayList<Board> boardReviewList = new ArrayList<>();

  public void menu(String choice) throws CloneNotSupportedException {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new BoardAddHandler(boardReviewList));
    commandMap.put("2", new BoardListHandler(boardReviewList));
    commandMap.put("3", new BoardDetailHandler(boardReviewList));
    commandMap.put("4", new BoardUpdateHandler(boardReviewList));
    commandMap.put("5", new BoardDeleteHandler(boardReviewList));

    while(true) {
      System.out.printf("[메인 > 게시판 > %s]\n", choice);
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 상세 보기");
      System.out.println("4. 수정");
      System.out.println("5. 삭제");
      System.out.println("0. 이전 메뉴");
      System.out.println();

      String command = com.kyung.util.Prompt.inputString("명령> ");
      System.out.println();
      
      try {
        switch(command) {
          case "0" :
            System.out.println("게시판으로 돌아갑니다.");
            System.out.println();
            App.chooseBoard();
          default :
            Command commandHandler = commandMap.get(command);
            if(commandHandler == null) {
              System.out.println("실행할 수 없는 메뉴 번호 입니다.");
            } else {
              commandHandler.service();
            }
        }
      }catch(Exception e) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------------------------------------------");
      }
      System.out.println();
    }
  }
}