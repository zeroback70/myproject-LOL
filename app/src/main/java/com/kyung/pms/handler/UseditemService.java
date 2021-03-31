package com.kyung.pms.handler;
// 2021-03-31 Update
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemService {

  static LinkedList<Useditem> UseditemList = new LinkedList<>();

  public LinkedList<Useditem> getUseditemList() {
    return UseditemList;
  }

  public UseditemService() { 
    loadUseditems();
  }

  public void menu() {

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
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정하기");
        System.out.println("5. 삭제하기");
        System.out.println("0. 이전 메뉴로 돌아가기");
        System.out.println();

        String command = Prompt.inputString("번호를 입력해주세요! (0~5) >>");
        System.out.println();
        try {
          switch(command) {
            case "0" :
              System.out.println("메인으로 돌아갑니다..");
              System.out.println();
              break loop;
            default :
              Command commandHandler = commandMap.get(command);
              if(commandHandler == null) {
                System.out.println("실행할 수 없는 메뉴 번호 입니다!");
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
    try(BufferedReader in = new BufferedReader(new FileReader("Useditems.csv"))){
      String record = null;
      while((record = in.readLine()) != null) {
        try {
          String[] fields = record.split(",");
          Useditem p = new Useditem();
          p.setNumber(Integer.parseInt(fields[0]));
          p.setName(fields[1]);
          p.setPrice(Integer.parseInt(fields[2]));
          p.setPhoto(fields[3]);

          UseditemList.add(p);
          System.out.println("상품 데이터 로딩중 ..");
        } catch (Exception e) {
          break;
        }
      }
    }catch (Exception e) {
      System.out.println("상품 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveUseditems() {
    try(BufferedWriter out = new BufferedWriter(new FileWriter("Useditems.csv"))) {

      for (Useditem p : UseditemList) {
        out.write(String.format("%d,%s,%d,%s",
            p.getNumber(),
            p.getName(),
            p.getPrice(),
            p.getPhoto()
            ));
      }

      System.out.println("상품 데이터 저장중 ..");
    } catch (Exception e) {
      System.out.println("상품 데이터 파일로 저장 중 오류 발생!");
    }
  }
}