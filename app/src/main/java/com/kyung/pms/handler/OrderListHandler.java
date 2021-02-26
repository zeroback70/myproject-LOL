package com.kyung.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.kyung.pms.domain.Order;

public class OrderListHandler extends AbstractOrderHandler {

  public OrderListHandler(List<Order> orderList){
    super(orderList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 주문 > 목록]");

    Iterator<Order> iterator = orderList.iterator();
    while (iterator.hasNext()) {
      Order o = iterator.next();
      System.out.printf("주문 번호: %d 회원 아이디: %s\n주문 날짜: %s\n"
          , o.getNumber(), o.getMemberId(), o.getRegisteredDate());
      System.out.println("----------------------------------------------------------");

    }

    System.out.println();
  }
}
