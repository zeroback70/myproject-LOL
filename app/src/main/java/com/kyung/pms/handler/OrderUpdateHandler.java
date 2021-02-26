package com.kyung.pms.handler;

import java.sql.Date;
import java.util.List;
import com.kyung.pms.domain.Order;
import com.kyung.util.Prompt;

public class OrderUpdateHandler extends AbstractOrderHandler {

    private UseditemValidatorHandler useditemValidatorHandler;

    public OrderUpdateHandler(MemberValidatorHandler memberValidatorHandler,
            UseditemValidatorHandler useditemValidatorHandler, List<Order> orderList) {
        super(orderList);
        this.useditemValidatorHandler = useditemValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 주문 > 수정]");

    Order order = findByNo(Prompt.inputInt("번호? "));
    if(order == null) {

      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {

      String Useditems = useditemValidatorHandler.inputUseditems(String.format("주문할 상품(%s)?(완료: 빈문자열)",order.getUseditems()));

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
}