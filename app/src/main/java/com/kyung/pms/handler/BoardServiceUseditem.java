package com.kyung.pms.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.kyung.pms.App;
import com.kyung.pms.domain.Board;

public class BoardServiceUseditem {

  static List<Board> boardUseditemList = new ArrayList<>(); //상품 문의

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
      saveBoards();
      System.out.println();
    }
  }

  static void saveBoards() {
    try (FileOutputStream out = new FileOutputStream("boardsOfUseditem.data")) { 
      int size = boardUseditemList.size();
      out.write(size >> 8);
      out.write(size);

      for (Board b : boardUseditemList) { // 번호 제목 내용 글쓴이 등록일 조회수 좋아요
        out.write(b.getNumber() >> 24);
        out.write(b.getNumber() >> 16);
        out.write(b.getNumber() >> 8);
        out.write(b.getNumber());

        byte[] buf = b.getTitle().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getContent().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getWriter().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        out.write(b.getViewCount() >> 24);
        out.write(b.getViewCount() >> 16);
        out.write(b.getViewCount() >> 8);
        out.write(b.getViewCount());

        out.write(b.getLike() >> 24);
        out.write(b.getLike() >> 16);
        out.write(b.getLike() >> 8);
        out.write(b.getLike());
      }
      System.out.println("상품문의가 등록되었습니다.");

    } catch (Exception e) {
      System.out.println("상품문의 데이터 파일로 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  static void loadBoards() {
    try(FileInputStream in = new FileInputStream("boardsOfUseditem.data")) {
      int size = in.read() << 8 | in.read();

      for(int i = 0; i < size ; i++) {// 번호 제목 내용 글쓴이 등록일 조회수 좋아요
        Board b = new Board();

        int value = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
        b.setNumber(value);

        byte[] bytes = new byte[30000];

        int len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        b.setTitle(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        b.setContent(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        b.setWriter(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        b.setRegisteredDate(Date.valueOf(new String(bytes, 0, len, "UTF-8")));

        value = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
        b.setViewCount(value);

        value = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
        b.setLike(value);

        boardUseditemList.add(b);
        System.out.println("상품 문의 로딩!");
      }

    } catch (Exception e) {
      System.out.println("상품 문의 데이터 로딩 중 오류 발생!");
    }
  }

}