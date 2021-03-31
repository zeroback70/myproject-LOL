package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Order;

public abstract class AbstractOrderHandler implements Command {

  protected List<Order> orderList;

  public AbstractOrderHandler(List<Order> orderList){
    this.orderList = orderList;
  }

  protected Order findByNo(int orderNo) {
    Order[] list = orderList.toArray(new Order[orderList.size()]);
    for(Order o : list) {
      if(o.getNumber() == orderNo) {
        return o;
      }
    }
    return null;
  }
}