package com.kyung.pms.handler;

import java.util.List;
import com.kyung.pms.domain.Deliver;
import com.kyung.util.Prompt;

public class DeliverUpdateHandler extends AbstractDeliverHandler {

  private MemberValidatorHandler memberValidatorHandler;
  private OrderValidatorHandler orderValidatorHandler;

  public DeliverUpdateHandler(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler, List<Deliver> DeliverList) {
    super(DeliverList);
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 수정]");

    Deliver Deliver = findByNo(Prompt.inputInt("번호? "));
    if(Deliver == null) {

      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {

      String memberId = memberValidatorHandler.inputMember(String.format("고객아이디(%s)(enter(취소))? ",Deliver.getMemberId()));
      if(memberId == null) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
      }

      int orderNumber = orderValidatorHandler.inputOrderNumber(String.format("주문 번호(%s)(-1(취소))? ",Deliver.getOrderNumber()));
      if(orderNumber == -1) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
        return;
      }

      int trackingNumber = Prompt.inputInt(String.format("운송장번호(%s)? ",Deliver.getTrackingNumber()));

      int status =  Prompt.inputInt(String.format
          ("0: 배송준비중\n"+ "1: 배송중\n" + "2: 배송완료\n" + "배송상태(%s)? ",getStatusLabel(Deliver.getStatus())));

      String manager = Prompt.inputString(String.format("담당자(%s)? ",Deliver.getManager()));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        Deliver.setMemberId(memberId);
        Deliver.setOrderNumber(orderNumber);
        Deliver.setTrackingNumber(trackingNumber);
        Deliver.setStatus(status);
        Deliver.setManager(manager);
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }
}