package com.kyung.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.kyung.pms.domain.Deliver;

public class DeliverListHandler extends AbstractDeliverHandler {

  public DeliverListHandler(List<Deliver> DeliverList) {
    super(DeliverList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 목록]");

    Iterator<Deliver> iterator = DeliverList.iterator();

    while (iterator.hasNext()) {
      Deliver s = iterator.next();

      System.out.printf("배송 번호: %d 고객 아이디: %s\n운송장 번호: %d\n"
          ,s.getNumber(), s.getMemberId(), s.getTrackingNumber());
      System.out.println("-----------------------------------------");

    }
    System.out.println();
  }
}
