package com.kyung.pms.handler;

import com.kyung.pms.domain.Useditem;
import com.kyung.util.List;
import com.kyung.util.Prompt;

public class UseditemHandler {

  private List UseditemList = new List();

  public List getUseditemList(List UseditemList) {
    return this.UseditemList;
  }

  public void service() {

    loop:
      while(true) {
        System.out.println("【토끼마켓 / 중고상품】");
        System.out.println("1. 중고상품 등록하기");
        System.out.println("2. 중고상품 목록보기");
        System.out.println("3. 상품 상세 보기");
        System.out.println("4. 중고상품 수정하기");
        System.out.println("5. 중고상품 삭제하기");
        System.out.println("0. 이전 메뉴로 돌아가기");
        System.out.println("원하시는 번호를 눌러주세요!");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호입력(0~5)>> ");
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
    System.out.println("[중고상품 > 등록]");
    Useditem p = new Useditem();

    p.setNumber(Prompt.inputInt("상품 번호: "));
    p.setName(Prompt.inputString("상품 이름: "));
    p.setPrice(Prompt.inputInt("상품 가격: "));
    p.setPhoto(Prompt.inputString("상품 사진: "));

    UseditemList.add(p);
    System.out.println();
  }

  public void list() {
    System.out.println("[중고상품 > 목록]");

    Object[] list = UseditemList.toArray();

    for(Object obj : list) {
      Useditem p = (Useditem)obj;
      System.out.printf("사진: %s\n이름: %s 가격: %d원\n",p.getPhoto(), p.getName(), p.getPrice());
      System.out.println("-----------------------------------------------------");

    }
  }

  public void detail() {
    System.out.println("중고상품 > 상세 보기]");

    Useditem Useditem = findByNo(Prompt.inputInt("번호? "));

    if (Useditem == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("번호: %s ", Useditem.getNumber());
      System.out.printf("이름: %s\n", Useditem.getName());
      System.out.printf("사진: %s\n", Useditem.getPhoto());
      System.out.printf("가격: %s원\n", Useditem.getPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[중고상품 > 수정]");

    Useditem Useditem = findByNo(Prompt.inputInt("번호? "));
    if(Useditem == null) {

      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",Useditem.getName()));
      String photo = Prompt.inputString(String.format("사진(%s)? ",Useditem.getPhoto()));
      int price = Prompt.inputInt(String.format("가격(%s원)? ",Useditem.getPrice()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        Useditem.setName(name);
        Useditem.setPhoto(photo);
        Useditem.setPrice(price);
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
    System.out.println("[중고상품 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Useditem Useditem = findByNo(no);
    if(Useditem == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        UseditemList.delete(Useditem);

        System.out.println("상품 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("상품 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  public String inputUseditems(String promptTitle) {
    String Useditems = "";
    while(true) {
      String name = Prompt.inputString(promptTitle);
      if(name.isEmpty()) {
        return Useditems;
      }
      if(findByName(name) != null) {
        if(Useditems.length() != 0) {
          Useditems += ", ";
        }
        Useditems += name;
      }else {
        System.out.println("등록된 상품이 아닙니다.");
      }
    }
  }

  private Useditem findByNo(int UseditemNo) {
    Object[] list = UseditemList.toArray();
    for(Object obj : list) {
      Useditem p = (Useditem)obj;
      if(p.getNumber() == UseditemNo) {
        return p;
      }
    }
    return null;
  }

  private Useditem findByName(String UseditemName) {
    Object[] list = UseditemList.toArray();
    for(Object obj : list) {
      Useditem p = (Useditem)obj;
      if(p.getName().equals(UseditemName)) {
        return p;
      }
    }
    return null;
  }
}
