package com.kyung.pms.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemService {

  static LinkedList<Useditem> UseditemList = new LinkedList<>();

  public LinkedList<Useditem> getUseditemList() {
    return UseditemList;
  }

  public void menu() {

    loadUseditems();
    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new UseditemAddHandler(UseditemList));
    commandMap.put("2", new UseditemListHandler(UseditemList));
    commandMap.put("3", new UseditemDetailHandler(UseditemList));
    commandMap.put("4", new UseditemUpdateHandler(UseditemList));
    commandMap.put("5", new UseditemDeleteHandler(UseditemList));

    loop:
      while(true) {
        System.out.println("[메인 > 상품]");
        System.out.println("1. 등록하기");
        System.out.println("2. 목록 보기");
        System.out.println("3. 상세하게 보기");
        System.out.println("4. 수정하기");
        System.out.println("5. 삭제하기");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = Prompt.inputString("번호를 입력해주세요! (0~5) >> ");
        System.out.println();
        try {
          switch(command) {
            case "0" :
              System.out.println("메인으로 돌아갑니다.. ");
              System.out.println();
              break loop;
            default :
              Command commandHandler = commandMap.get(command);
              if(commandHandler == null) {
                System.out.println("실행할 수 없는 메뉴 번호 입니다.");
              }else {
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
    saveUseditems();
  }

  static void loadUseditems() {
    try(FileInputStream in = new FileInputStream("Useditems.data")){

      int size = in.read() << 8 | in.read();

      for(int i = 0; i < size; i++) {

        Useditem Useditem = new Useditem();
        byte[] bytes = new byte[30000];

        int value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        Useditem.setNumber(value);

        int len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        Useditem.setName(new String(bytes, 0, len, "UTF-8"));

        value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        Useditem.setPrice(value);

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        Useditem.setPhoto(new String(bytes, 0, len, "UTF-8"));

        UseditemList.add(Useditem);
        System.out.println("상품 데이터 로딩중입니다.. ");
      }
    } catch (Exception e) {
      System.out.println("상품 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveUseditems() {
    try(FileOutputStream out = new FileOutputStream("Useditems.data")) {

      out.write(UseditemList.size() >> 8);
      out.write(UseditemList.size());

      for (Useditem Useditem : UseditemList) {
        out.write(Useditem.getNumber() >> 24);
        out.write(Useditem.getNumber() >> 16);
        out.write(Useditem.getNumber() >> 8);
        out.write(Useditem.getNumber());

        byte[] bytes = Useditem.getName().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        out.write(Useditem.getPrice() >> 24);
        out.write(Useditem.getPrice() >> 16);
        out.write(Useditem.getPrice() >> 8);
        out.write(Useditem.getPrice());

        bytes = Useditem.getPhoto().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }

      System.out.println("상품 데이터를 저장했습니다!");
    } catch (Exception e) {
      System.out.println("상품 데이터 파일로 저장 중 오류 발생!");
    }
  }
}
