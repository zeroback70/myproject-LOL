package com.kyung.pms.handler;

import java.util.ArrayList;
import com.kyung.pms.App;
import com.kyung.pms.domain.Board;

public class BoardServiceExchangeReturn {

  ArrayList<Board> boardExchangeReturnList = new ArrayList<>();

  BoardAddHandler boardAddHandler = new BoardAddHandler(boardExchangeReturnList);
  BoardListHandler boardListHandler = new BoardListHandler(boardExchangeReturnList);
  BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardExchangeReturnList);
  BoardUpdateHandler boardUpdateHandler = new BoardUpdateHandler(boardExchangeReturnList);
  BoardDeleteHandler boardDeleteHandler = new BoardDeleteHandler(boardExchangeReturnList);

  public void menu(String choice) {

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

        try {
            switch(command) {
                case "1" :
                  boardAddHandler.service();
                  break;
                case "2" :
                  boardListHandler.service();
                  break;
                case "3" :
                  boardDetailHandler.service();
                  break;
                case "4" :
                  boardUpdateHandler.service();
                  break;
                case "5" :
                  boardDeleteHandler.service();
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

}