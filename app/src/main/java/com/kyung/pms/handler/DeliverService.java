package com.kyung.pms.handler;

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

  DeliverAddHandler DeliverAddHandler = new DeliverAddHandler(memberValidatorHandler, orderValidatorHandler, DeliverList);
  DeliverListHandler DeliverListHandler = new DeliverListHandler(DeliverList);
  DeliverDetailHandler DeliverDetailHandler = new DeliverDetailHandler(DeliverList);
  DeliverUpdateHandler DeliverUpdateHandler = new DeliverUpdateHandler(memberValidatorHandler, orderValidatorHandler, DeliverList);
  DeliverDeleteHandler DeliverDeleteHandler = new DeliverDeleteHandler(DeliverList);


  public void menu() throws CloneNotSupportedException {

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
            case "1" :
              DeliverAddHandler.service();
              break;
            case "2" :
              DeliverListHandler.service();
              break;
            case "3" :
              DeliverDetailHandler.service();
              break;
            case "4" :
              DeliverUpdateHandler.service();
              break;
            case "5" :
              DeliverDeleteHandler.service();
              break;
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
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