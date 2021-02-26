package com.kyung.pms.handler;

import java.util.List;
import com.kyung.pms.domain.Order;
import com.kyung.util.Prompt;

public class OrderDeleteHandler extends AbstractOrderHandler {

  public OrderDeleteHandler(List<Order> orderList){
    super(orderList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 주문 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Order order = findByNo(no);
    if(order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        orderList.remove(order);
        System.out.println("주문 삭제를 완료하였습니다.");
        System.out.println();
      }else {
        orderList.remove(no);
        System.out.println("주문 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }
}
