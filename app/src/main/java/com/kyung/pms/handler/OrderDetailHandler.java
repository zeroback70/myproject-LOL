package com.kyung.pms.handler;

import java.util.List;
import com.kyung.pms.domain.Order;
import com.kyung.util.Prompt;

public class OrderDetailHandler extends AbstractOrderHandler {

  public OrderDetailHandler(List<Order> orderList){
    super(orderList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 주문 > 상세 보기]");

    Order order = findByNo(Prompt.inputInt("번호? "));

    if (order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("회원 아이디: %s\n", order.getMemberId());
      System.out.printf("주문한 상품: %s\n", order.getProducts());
      System.out.printf("주문 날짜: %s\n", order.getRegisteredDate());
      System.out.printf("요청사항: %s\n", order.getRequest());
      System.out.printf("총 가격: %d원\n", order.getTotalPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }
}
