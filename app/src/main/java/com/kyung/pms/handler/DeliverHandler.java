package com.kyung.pms.handler;

import com.kyung.pms.domain.Deliver;
import com.kyung.util.List;
import com.kyung.util.Prompt;

public class DeliverHandler {

  private MemberHandler memberHandler;
  private OrderHandler orderHandler;

  public List DeliverList = new List();

  public DeliverHandler(MemberHandler memberHandler, OrderHandler orderHandler) {
    this.memberHandler = memberHandler;
    this.orderHandler = orderHandler;
  }

  public void service() {

    loop:
      while(true) {
        System.out.println("【토끼마켓 / 상품 배송】");
        System.out.println("1. 등록하기");
        System.out.println("2. 목록 보기");
        System.out.println("3. 배송 상세 보기");
        System.out.println("4. 수정하기");
        System.out.println("5. 삭제하기");
        System.out.println("0. 이전 메뉴로 돌아가기");
        System.out.println("원하시는 번호를 눌러주세요!");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("명령> ");
        System.out.println();

        switch(command) {
          case "1" :
            this.add();
            break;
          case "2" :
            this.list();
            break;
          case "3" :
            this.detail();
            break;
          case "4" :
            this.update();
            break;
          case "5" :
            this.delete();
            break;
          case "0" :
            System.out.println("이전 메뉴로 돌아갑니다.");
            System.out.println();
            break loop;
          default :
            System.out.println("번호를 잘못 입력하신것 같습니다. 0번부터 5번까지 다시 입력해주시겠어요?");
            System.out.println();

        }
        System.out.println();
      }
  }

  public void add() {
    System.out.println("[상품 배송 > 등록]");

    Deliver s = new Deliver();

    s.setNumber(Prompt.inputInt("배송 번호: "));

    s.setMemberId(memberHandler.inputMember("회원 아이디(enter(취소)): "));
    if(s.getMemberId() == null) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.setOrderNumber(orderHandler.inputOrderNumber("주문 번호(-1(취소)): "));
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

  public void list() {
    System.out.println("[상품 배송 > 목록]");

    Object[] list = DeliverList.toArray();
    for(Object obj : list) {
      Deliver s = (Deliver)obj;

      System.out.printf("배송 번호: %d 고객 아이디: %s\n운송장 번호: %d\n"
          ,s.getNumber(), s.getMemberId(), s.getTrackingNumber());
      System.out.println("-----------------------------------------");

    }
    System.out.println();
  }

  public void detail() {
    System.out.println("[상품 배송 > 상세 보기]");

    Deliver Deliver = findByNo(Prompt.inputInt("번호? "));

    if (Deliver == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("고객 아이디: %s ", Deliver.getMemberId());
      System.out.printf("주문 번호: %s\n", Deliver.getOrderNumber());
      System.out.printf("운송장 번호: %s\n", Deliver.getTrackingNumber());

      String statusLabel = getStatusLabel(Deliver.getStatus());

      System.out.printf("배송 상태: %s ", statusLabel);
      System.out.printf("담당자: %s\n", Deliver.getManager());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[상품 배송 > 수정]");

    Deliver Deliver = findByNo(Prompt.inputInt("번호? "));
    if(Deliver == null) {

      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {

      String memberId = memberHandler.inputMember(String.format("고객아이디(%s)(enter(취소))? ",Deliver.getMemberId()));
      if(memberId == null) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
      }

      int orderNumber = orderHandler.inputOrderNumber(String.format("주문 번호(%s)(-1(취소))? ",Deliver.getOrderNumber()));
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


  public void delete() {
    System.out.println("[상품 배송 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Deliver Deliver = findByNo(no);
    if(Deliver == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        DeliverList.delete(Deliver);
        System.out.println("배송 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("배송 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  String getStatusLabel(int status) {
    switch (status) {
      case 1:
        return "배송중";
      case 2: 
        return "배송 완료";
      default:
        return "배송 준비중";
    }
  }

  private Deliver findByNo(int DeliverNo) {
    Object[] list = DeliverList.toArray();
    for(Object obj : list) {
      Deliver s = (Deliver)obj;
      if(s.getNumber() == DeliverNo) {
        return s;
      }
    }
    return null;
  }

}