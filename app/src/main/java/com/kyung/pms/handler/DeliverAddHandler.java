package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Deliver;
import com.kyung.util.Prompt;

public class DeliverAddHandler extends AbstractDeliverHandler {

  MemberValidatorHandler memberValidatorHandler;
  OrderValidatorHandler orderValidatorHandler;

  public DeliverAddHandler(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler, List<Deliver> DeliverList) {
    super(DeliverList);
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 등록]");

    Deliver s = new Deliver();

    s.setNumber(Prompt.inputInt("배송 번호: "));

    s.setMemberId(memberValidatorHandler.inputMember("회원 아이디(enter(취소)): "));
    if(s.getMemberId() == null) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.setOrderNumber(orderValidatorHandler.inputOrderNumber("주문 번호(-1(취소)): "));
    if(s.getOrderNumber() == -1) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.setTrackingNumber(Prompt.inputInt("운송장 번호: "));
    s.setStatus(Prompt.inputInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> "));
    s.setManager(Prompt.inputString("배송 담당자: "));

    DeliverList.add(s);

    System.out.println();
  }
}