package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Deliver;

public abstract class AbstractDeliverHandler implements Command {

  protected List<Deliver> DeliverList;

  public AbstractDeliverHandler(List<Deliver> DeliverList) {
    this.DeliverList = DeliverList;
  }

  protected String getStatusLabel(int status) {
    switch (status) {
      case 1:
        return "배송중";
      case 2: 
        return "배송 완료";
      default:
        return "배송 준비중";
    }
  }

  protected Deliver findByNo(int DeliverNo) {
    Deliver[] list = DeliverList.toArray(new Deliver[DeliverList.size()]);
    for(Deliver s : list) {
      if(s.getNumber() == DeliverNo) {
        return s;
      }
    }
    return null;
  }
}