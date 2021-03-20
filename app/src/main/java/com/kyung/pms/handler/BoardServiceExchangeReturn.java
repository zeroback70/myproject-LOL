package com.kyung.pms.handler;

import java.util.ArrayList;
import java.util.HashMap;
import com.kyung.pms.domain.Board;

public class BoardServiceExchangeReturn {

  ArrayList<Board> boardExchangeReturnList = new ArrayList<>();

  public void menu(String choice) {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new BoardAddHandler(boardExchangeReturnList));
    commandMap.put("2", new BoardListHandler(boardExchangeReturnList));
    commandMap.put("3", new BoardDetailHandler(boardExchangeReturnList));
    commandMap.put("4", new BoardUpdateHandler(boardExchangeReturnList));
    commandMap.put("5", new BoardDeleteHandler(boardExchangeReturnList));

    while(true) {
        System.out.printf("【토끼마켓 / 토끼들 게시판 / %s】\n", choice);
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

        if (command.length() == 0)
        continue;


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