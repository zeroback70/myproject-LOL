package com.kyung.pms.handler;

import java.util.ArrayList;
import com.kyung.pms.domain.Order;

public  class OrderService {

  private ArrayList<Order> orderList = new ArrayList<>();
  public ArrayList<Order> getOrderList() {
    return orderList;
  }
  private MemberValidatorHandler memberValidatorHandler;
  private UseditemValidatorHandler useditemValidatorHandler;

  public OrderService(MemberValidatorHandler memberValidatorHandler, UseditemValidatorHandler useditemValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.useditemValidatorHandler = useditemValidatorHandler;
  }



  OrderAddHandler orderAddHandler = new OrderAddHandler(memberValidatorHandler, useditemValidatorHandler, orderList);
  OrderListHandler orderListHandler = new OrderListHandler(orderList);
  OrderDetailHandler orderDetailHandler = new OrderDetailHandler(orderList);
  OrderUpdateHandler orderUpdateHandler = new OrderUpdateHandler(memberValidatorHandler, useditemValidatorHandler, orderList);
  OrderDeleteHandler orderDeleteHandler = new OrderDeleteHandler(orderList);

  public void menu() {

    loop:
      while(true) {
        System.out.println("[메인 > 주문]");
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
              orderAddHandler.service();
              break;
            case "2" :
              orderListHandler.service();
              break;
            case "3" :
              orderDetailHandler.service();
              break;
            case "4" :
              orderUpdateHandler.service();
              break;
            case "5" :
              orderDeleteHandler.service();
              break;
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              System.out.println("잘못된 메뉴 번호 입니다.");
              System.out.println();

          }
        }catch(Exception e){
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
  }

}