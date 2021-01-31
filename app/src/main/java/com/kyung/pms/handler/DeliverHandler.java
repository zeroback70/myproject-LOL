package com.kyung.pms.handler;

import com.kyung.pms.domain.Deliver;
import com.kyung.util.Prompt;

public class DeliverHandler {

  static final int LENGTH = 100;

  Deliver[] Delivers = new Deliver[LENGTH];
  int size = 0;

  MemberHandler memberList;


  public DeliverHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void add() {
    System.out.println("결제 완료! 배송 하기");

    Deliver s = new Deliver();

    s.number = Prompt.promptInt("배송 번호: ");

    while(true) {
      String id = Prompt.promptString("회원 아이디(enter(취소)): ");
      if(id.length() == 0) {
        System.out.println("배송 등록을 취소합니다.");
        return;
      }else if(this.memberList.exist(id)) {
        s.memberId = id;
        break;
      }
      System.out.println("잘못된 아이디 입니다.");
    }

    int number = -1;
    while(true) {
      number = Prompt.promptInt("주문 번호(-1(취소)): ");
      if(number == -1) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
        return;
      }else{
        s.newNumber = number;
        break;
      }
    }
    s.tNumber = Prompt.promptInt("운송장 번호: ");
    s.status = Prompt.promptInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");
    s.manager = Prompt.promptString("배송 담당자: ");

    this.Delivers[this.size++] = s;

    System.out.println();
  }

  public void list() {
    System.out.println("[배송 목록]");

    for (int i = 0; i < this.size; i++) {

      Deliver s = this.Delivers[i];

      String statusLabel = null;

      switch (s.status) {
        case 0:
          statusLabel = "배송 준비중";
          break;
        case 1: 
          statusLabel = "배송중";
          break;
        case 2:
          statusLabel = "배송 완료";
          break;
      }

      System.out.printf("배송 번호: %d 고객 아이디: %s 주문 번호: %d\n운송장 번호: %d 배송상태: %s\n담당자: %s\n"
          ,s.number, s.memberId, s.newNumber, s.tNumber, statusLabel, s.manager);
      System.out.println("-----------------------------------------");
    }
    System.out.println();
  }
  public void detail() {
    System.out.println("[배송 상세보기]");

    Deliver Deliver = findByNo(Prompt.promptInt("번호? "));

    if (Deliver == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("고객 아이디: %s ", Deliver.memberId);
      System.out.printf("주문 번호: %s\n", Deliver.newNumber);
      System.out.printf("운송장 번호: %s\n", Deliver.tNumber);

      String statusLabel = null;

      switch (Deliver.status) {
        case 0:
          statusLabel = "배송 준비중";
          break;
        case 1: 
          statusLabel = "배송중";
          break;
        case 2:
          statusLabel = "배송 완료";
          break;
      }

      System.out.printf("배송 상태: %s ", statusLabel);
      System.out.printf("담당자: %s\n", Deliver.manager);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[배송 정보 수정]");

    Deliver Deliver = findByNo(Prompt.promptInt("번호? "));
    if(Deliver == null) {

      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {

      String memberId = "";
      while(true) {
        memberId = Prompt.promptString(String.format("고객아이디(%s)(enter(취소))? ",Deliver.memberId));
        if(memberId.length() == 0) {
          System.out.println("배송 등록을 취소합니다.");
          System.out.println();
          return;
        }else if(this.memberList.exist(memberId)) {
          break;
        }
        System.out.println("잘못된 아이디 입니다.");
      }

      int orderNumber = -1;
      while(true) {
        orderNumber = Prompt.promptInt(String.format("주문 번호(%s)(-1(취소))? ",Deliver.newNumber));
        if(orderNumber == -1) {
          System.out.println("배송 등록을 취소합니다.");
          System.out.println();
          return;
        }else{
          Deliver.newNumber = orderNumber;
          break;
        }
      }

      int trackingNumber = Prompt.promptInt(String.format("운송장번호(%s)? ",Deliver.tNumber));

      int status =  Prompt.promptInt(String.format("0: 배송준비중\n"+ "1: 배송중\n" + "2: 배송완료\n" + "배송상태(%s)? ",Deliver.tNumber));

      String manager = Prompt.promptString(String.format("담당자(%s)? ",Deliver.manager));


      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        Deliver.memberId = memberId;
        Deliver.newNumber = orderNumber;
        Deliver.tNumber = trackingNumber;
        Deliver.status = status;
        Deliver.manager = manager;
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
    System.out.println("[배송 삭제]");

    int index = indexOf(Prompt.promptInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        for(int x = index + 1; x < this.size; x++) {

          Delivers[x - 1] = Delivers[x];
        }
        this.Delivers[--this.size] = null;
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


  int indexOf(int DeliverNo) {
    for(int i = 0; i < this.size; i++) {
      Deliver Deliver = this.Delivers[i];
      if(DeliverNo == Deliver.number) {
        return i;
      }
    }
    return -1;
  }

  Deliver findByNo(int DeliverNo) {
    int i = indexOf(DeliverNo);
    if(i == -1) {
      return null;
    }else {
      return this.Delivers[i];
    }
  }
}