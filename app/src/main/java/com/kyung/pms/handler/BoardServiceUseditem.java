package com.kyung.pms.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.kyung.pms.domain.Board;

public class BoardServiceUseditem {

  static List<Board> boardUseditemList = new ArrayList<>();

  public void menu(String choice) {

    loadBoards();
    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new BoardAddHandler(boardUseditemList));
    commandMap.put("2", new BoardListHandler(boardUseditemList));
    commandMap.put("3", new BoardDetailHandler(boardUseditemList));
    commandMap.put("4", new BoardUpdateHandler(boardUseditemList));
    commandMap.put("5", new BoardDeleteHandler(boardUseditemList));

    while(true) {
      System.out.printf("[메인 > 게시판 > %s]\n", choice);
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 상세 보기");
      System.out.println("4. 수정");
      System.out.println("5. 삭제");
      System.out.println("0. 이전 메뉴");
      System.out.println();

      String command = com.kyung.util.Prompt.inputString("번호를 입력해주세요(0~5) >> ");
      System.out.println();
      try {
        switch(command) {
          case "0" :
            System.out.println("게시판으로 돌아갑니다.");
            System.out.println();
            return;
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
      saveBoards();
      System.out.println();
    }
  }

  static void loadBoards() {
    try(BufferedReader in = new BufferedReader(new FileReader("boardsOfUseditem.data"))) {

      String csvStr = null;
      while ((csvStr = in.readLine()) != null) {
        boardUseditemList.add(Board.valueOfCsv(csvStr));
      }
      System.out.println("상품 문의 로딩!");

    } catch (Exception e) {
      System.out.println("상품 문의 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveBoards() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter("boardsOfUseditem.data"))) { 

      for (Board b : boardUseditemList) {
        out.write(b.toCsvString());
      }
      System.out.println("상품문의가 등록되었습니다.");

    } catch (Exception e) {
      System.out.println("상품문의 데이터 파일로 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }
}