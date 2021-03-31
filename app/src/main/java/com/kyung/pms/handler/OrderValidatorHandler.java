package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Order;
import com.kyung.util.Prompt;

public class OrderValidatorHandler extends AbstractOrderHandler {

  @Override
  public void service() {}

  public OrderValidatorHandler(List<Order> orderList) {
    super(orderList);
  }

  public int inputOrderNumber(String promptTitle) {
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
}