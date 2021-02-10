package com.kyung.pms.handler;

import java.sql.Date;
import com.kyung.pms.domain.Order;
import com.kyung.util.List;
import com.kyung.util.Prompt;

public class OrderHandler {

  private MemberHandler memberHandler;
  private UseditemHandler UseditemHandler;

  private List orderList = new List();

  public List getOrderList(List orderList) {
    return this.orderList;
  }

  public OrderHandler(MemberHandler memberHandler, UseditemHandler UseditemHandler){
    this.memberHandler = memberHandler;
    this.UseditemHandler = UseditemHandler;
  }

  public void service() {

    loop:
      while(true) {
        System.out.println("【토끼마켓 / 상품 주문】");
        System.out.println("1. 주문 하기");
        System.out.println("2. 주문 목록 보기");
        System.out.println("3. 주문 상세 보기");
        System.out.println("4. 주문 변경/수정");
        System.out.println("5. 주문 취소");
        System.out.println("0. 이전 메뉴로 돌아가기");
        System.out.println("원하시는 번호를 눌러주세요!");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호입력(0~5)>> ");
        System.out.println();

        switch(command) {
          case "1" :
            this.add();
            break;
          case "2" :
            this.list();
            break;
          case "3" :
            this.detail();
            break;
          case "4" :
            this.update();
            break;
          case "5" :
            this.delete();
            break;
          case "0" :
            System.out.println("이전 메뉴로 돌아갑니다.");
            System.out.println();
            break loop;
          default :
            System.out.println("번호를 잘못 입력하신것 같습니다. 0번부터 5번까지 다시 입력해주시겠어요?");
            System.out.println();

        }
        System.out.println();
      }
  }

  public void add() {

    System.out.println("[상품 주문 > 등록]");

    Order o = new Order();

    o.setNumber(Prompt.inputInt("주문 번호: "));

    o.setMemberId(memberHandler.inputMemberId()); 
    if(o.getMemberId() == null) {
      System.out.println("주문 등록을 취소합니다.");
      System.out.println();
      return;
    }

    o.setUseditems(UseditemHandler.inputUseditems("상품명(enter(완료)): "));

    o.setRequest(Prompt.inputString("요청사항: "));
    o.setRegisteredDate(new Date(System.currentTimeMillis()));

    orderList.add(o);

    System.out.println();
  }

  public void list() {
    System.out.println("[상품 주문 > 목록]");

    Object[] list = orderList.toArray();
    for(Object obj : list) {
      Order o = (Order)obj;
      System.out.printf("주문 번호: %d 회원 아이디: %s\n주문 날짜: %s\n"
          , o.getNumber(), o.getMemberId(), o.getRegisteredDate());
      System.out.println("----------------------------------------------------------");

    }

    System.out.println();
  }


  public void detail() {
    System.out.println("[상품 주문 > 상세 보기]");

    Order order = findByNo(Prompt.inputInt("번호? "));

    if (order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("회원 아이디: %s\n", order.getMemberId());
      System.out.printf("주문한 상품: %s\n", order.getUseditems());
      System.out.printf("주문 날짜: %s\n", order.getRegisteredDate());
      System.out.printf("요청사항: %s\n", order.getRequest());
      System.out.printf("총 가격: %d원\n", order.getTotalPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[상품 주문 > 수정]");

    Order order = findByNo(Prompt.inputInt("번호? "));
    if(order == null) {

      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {

      String Useditems = UseditemHandler.inputUseditems(String.format("주문할 상품(%s)?(완료: 빈문자열)",order.getUseditems()));

      String request = Prompt.inputString(String.format("요청사항(%s)? ",order.getRequest()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        order.setUseditems(Useditems);
        order.setRequest(request);
        order.setRegisteredDate(new Date(System.currentTimeMillis()));
        System.out.println("주문 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("주문 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }

  public void delete() {
    System.out.println("[상품 주문 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);
    if(index == -1) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        orderList.delete(index);
        System.out.println("주문 삭제를 완료하였습니다.");
        System.out.println();
      }else {
        orderList.delete(no);
        System.out.println("주문 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  int inputOrderNumber(String promptTitle) {
    int number = -1;
    while(true) {
      number = Prompt.inputInt(promptTitle);
      if(number == -1) {
        return -1;
      }else if(findByNo(number) != null) {
        return number;
      }
      System.out.println("잘못된 주문 번호 입니다.");
    }
  }

  private int indexOf(int orderNo) {
    Object[] list = orderList.toArray();
    for(int i = 0; i < list.length; i++) {
      Order o = (Order)list[i];
      if(o.getNumber() == orderNo) {
        return i;
      }
    }
    return -1;
  }

  private Order findByNo(int orderNo) {
    Object[] list = orderList.toArray();
    for(Object obj : list) {
      Order o = (Order)obj;
      if(o.getNumber() == orderNo) {
        return o;
      }
    }
    return null;
  }


}
