package com.kyung.pms.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.kyung.pms.App;
import com.kyung.pms.domain.Board;

public class BoardServiceUseditem {

  static List<Board> boardUseditemList; //상품 문의

  static File boardsOfUseditem = new File("boardsOfUseditem.data");

  public void menu(String choice) {

    boardUseditemList = loadObjects(boardsOfUseditem, Board.class);

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

      String command = com.kyung.util.Prompt.inputString("명령> ");
      System.out.println();
      try {
        switch(command) {
          case "0" :
            System.out.println("게시판으로 돌아갑니다.");
            System.out.println();
            App.chooseBoard();
            break;

          default :
            Command commandHandler = commandMap.get(command);
            if(commandHandler == null) {
              System.out.println("실행할 수 없는 메뉴 번호 입니다.");
              break;
            } else {
              commandHandler.service();
              break;
            }
        }
      }catch(Exception e) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------------------------------------------");
      }
      saveObjects(boardsOfUseditem, boardUseditemList);
      System.out.println();
    }
  }

  @SuppressWarnings("unchecked")
  static <T extends Serializable> List<T> loadObjects(File file, Class<T> dataType) {
    try(ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream(file)))) {

      System.out.printf("파일 %s 로딩!\n", file.getName());
      return (List<T>) in.readObject();

    } catch (Exception e) {
      System.out.printf("파일 %s 로딩 중 오류 발생!\n", file.getName());
      return new ArrayList<T>();
    }
  }

  static <T extends Serializable>void saveObjects(File file, List<T> dataList) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(file)))) { 

      out.writeObject(dataList);
      System.out.printf("파일 %s 저장!\n", file.getName());

    } catch (Exception e) {
      System.out.printf("파일 %s 저장 중 오류 발생!\n", file.getName());
    }
  }
}