package com.kyung.pms;

import com.kyung.pms.handler.BoardHandler;
import com.kyung.pms.handler.MemberHandler;
import com.kyung.pms.handler.OrderHandler;
import com.kyung.pms.handler.UseditemHandler;
import com.kyung.pms.handler.DeliverHandler;
import com.kyung.util.Prompt;
import com.kyung.util.Stack;

public class App {

  static BoardHandler BoardUseditem = new BoardHandler();
  static BoardHandler BoardDeliver = new BoardHandler();
  static BoardHandler BoardExchangeReturn = new BoardHandler();
  static BoardHandler BoardReview = new BoardHandler();

  static Stack commandStack = new Stack();

  public static void main(String[] args) {


    MemberHandler memberHandler = new MemberHandler();
    UseditemHandler UseditemHandler = new UseditemHandler();
    OrderHandler orderHandler = new OrderHandler(memberHandler, UseditemHandler);
    DeliverHandler DeliverHandler = new DeliverHandler(memberHandler, orderHandler);

    loop: 
      while(true) {
        System.out.println("｜토끼마켓｜");
        System.out.println("1. 회원정보");
        System.out.println("2. 중고상품");
        System.out.println("3. 상품주문");
        System.out.println("4. 상품배송");
        System.out.println("5. 토끼들 게시판");
        System.out.println("0. 종료하기");
        System.out.println("원하시는 번호를 눌러주세요!");
        System.out.println();

        String command = Prompt.inputString("번호입력(0~5)>> ");
        System.out.println();

        commandStack.push(command);

        switch(command) {

          case "1" :
            memberHandler.service();
            break;

          case "2" :
            UseditemHandler.service();
            break;

          case "3" :
            orderHandler.service();
            break;

          case "4" :
            DeliverHandler.service();
            break;

          case "5" :
            chooseBoard();
            break;

          case "0" :
            System.out.println("이용해주셔서 감사합니다.\n 더 나은 서비스로 보답하겠습니다.");
            break loop;

          default :
            System.out.println("번호를 잘못 입력하신것 같습니다. 0번부터 5번까지 다시 입력해주시겠어요?");
            System.out.println();

        }
      }
  }

  public static void chooseBoard() {
    loop:
      while(true) {
        System.out.println("[메인 > 게시판]");
        System.out.println("1. 상품 문의");
        System.out.println("2. 배송 문의");
        System.out.println("3. 교환/반품 문의");
        System.out.println("4. 리뷰");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("명령> ");
        System.out.println();

        switch(command) {
          case "1" :
            BoardUseditem.service("상품 문의");

          case "2" :
            BoardDeliver.service("배송 문의");

          case "3" :
            BoardExchangeReturn.service("교환/반품 문의");

          case "4" :
            BoardReview.service("리뷰");

          case "0" :
            System.out.println("메인으로 돌아갑니다.");
            break loop;
          default :
            System.out.println("잘못된 메뉴 번호 입니다.");

        }
        System.out.println();
      }
  }
}