package com.kyung.pms.handler;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.kyung.pms.domain.Board;

public class BoardServiceDeliver {
  static List<Board> boardDeliverList = new ArrayList<>(); 

  public void menu(String choice) throws CloneNotSupportedException {
    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new BoardAddHandler(boardDeliverList));
    commandMap.put("2", new BoardListHandler(boardDeliverList));
    commandMap.put("3", new BoardDetailHandler(boardDeliverList));
    commandMap.put("4", new BoardUpdateHandler(boardDeliverList));
    commandMap.put("5", new BoardDeleteHandler(boardDeliverList));

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
  static void saveBoards() {
    try(FileOutputStream out = new FileOutputStream("boardsOfDeliver.data")) {
      int size = boardDeliverList.size();
      out.write(size >> 8);
      out.write(size);

      for(Board b : boardDeliverList) {
        out.write(b.getNumber() >> 24);
        out.write(b.getNumber() >> 16);
        out.write(b.getNumber() >> 8);
        out.write(b.getNumber());
      }

    } catch (Exception e) {

    }
  }
}

  // 코드 향후에 추가!