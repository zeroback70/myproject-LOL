package com.kyung.pms.handler;

import java.util.HashMap;
import java.util.ArrayList;
import com.kyung.pms.domain.Deliver;

public class DeliverService {

  ArrayList<Deliver> DeliverList = new ArrayList<>();
  private MemberValidatorHandler memberValidatorHandler;
  private OrderValidatorHandler orderValidatorHandler;

  public DeliverService(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  public void menu() throws CloneNotSupportedException {

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

        String command = com.kyung.util.Prompt.inputString("명령> ");
        System.out.println();
        try {
          switch(command) {
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
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
  }
}