package com.kyung.pms.handler;
// 2021-03-31 Update
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import com.kyung.pms.domain.Deliver;

public class DeliverService {

  ArrayList<Deliver> DeliverList = new ArrayList<>();
  private MemberValidatorHandler memberValidatorHandler;
  private OrderValidatorHandler orderValidatorHandler;

  public DeliverService(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  public void menu() {

    loadDelivers();
    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new DeliverAddHandler(memberValidatorHandler, orderValidatorHandler, DeliverList));
    commandMap.put("2", new DeliverListHandler(DeliverList));
    commandMap.put("3", new DeliverDetailHandler(DeliverList));
    commandMap.put("4", new DeliverUpdateHandler(memberValidatorHandler, orderValidatorHandler, DeliverList));
    commandMap.put("5", new DeliverDeleteHandler(DeliverList));

    loop:
      while(true) {
        System.out.println("[메인 > 배송]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호를 입력해주세요! (0~5) >> ");
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
    saveDelivers();
  }

  private void loadDelivers() {

    try(BufferedReader in = new BufferedReader(new FileReader("Delivers.csv"))) {
      String record = null;
      while ((record = in.readLine()) != null) {
        try {
          String[] fields = record.split(",");
          Deliver s = new Deliver();
          s.setMemberId(fields[0]);
          s.setOrderNumber(Integer.parseInt(fields[1]));
          s.setTrackingNumber(Integer.parseInt(fields[2]));
          s.setStatus(Integer.parseInt(fields[3]));
          s.setManager(fields[4]);
        } catch (Exception e) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("배송 데이터 로딩 중 오류 발생!");
    }
  }

  private void saveDelivers() {
    try(BufferedWriter out = new BufferedWriter(new FileWriter("Delivers.csv"))) {

      for (Deliver s : DeliverList) {
        out.write(String.format("%d,%s,%d,%d,%d,%s",
            s.getNumber(),
            s.getMemberId(),
            s.getOrderNumber(),
            s.getTrackingNumber(),
            s.getStatus(),
            s.getManager()
            ));
      }
      System.out.println("배송 데이터 저장중 ..");
    } catch (Exception e) {
      System.out.println("배송 데이터 파일로 저장하는 중에 오류 발생!");
    }
  }
}