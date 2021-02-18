package com.kyung.pms;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.kyung.pms.handler.BoardHandler;
import com.kyung.pms.handler.MemberHandler;
import com.kyung.pms.handler.OrderHandler;
import com.kyung.pms.handler.UseditemHandler;
import com.kyung.pms.handler.DeliverHandler;
import com.kyung.util.Prompt;


public class App {

  static BoardHandler<String> BoardUseditem = new BoardHandler<>();
  static BoardHandler<String> BoardDeliver = new BoardHandler<>();
  static BoardHandler<String> BoardExchangeReturn = new BoardHandler<>();
  static BoardHandler<String> BoardReview = new BoardHandler<>();

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException{

    MemberHandler<String> memberHandler = new MemberHandler<>();
    UseditemHandler<String> UseditemHandler = new UseditemHandler<>();
    OrderHandler<String> orderHandler = new OrderHandler<>(memberHandler, UseditemHandler);
    DeliverHandler<String> DeliverHandler = new DeliverHandler<>(memberHandler, orderHandler);

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
        commandQueue.offer(command);

        switch(command) {

          case "1" :
            memberHandler.service(command);
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

          case "history" : 
            printCommandHistory(commandStack.iterator());
            System.out.println();
            break;

          case "history2" : 
            printCommandHistory(commandQueue.iterator());
            System.out.println();
            break;

          default :
            System.out.println("번호를 잘못 입력하신것 같습니다. 0번부터 5번까지 다시 입력해주시겠어요?");
            System.out.println();

        }
      }
  }

  public static void chooseBoard() throws CloneNotSupportedException{

    loop:
      while(true) {
        System.out.println("[메인 > 게시판]");
        System.out.println("1. 상품 문의하기");
        System.out.println("2. 배송 문의하기");
        System.out.println("3. 교환/반품 문의하기");
        System.out.println("4. 리뷰 남기기");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("명령> ");
        System.out.println();

        switch(command) {
          case "1" :
            BoardUseditem.service("상품 문의하기");

          case "2" :
            BoardDeliver.service("배송 문의하기");

          case "3" :
            BoardExchangeReturn.service("교환/반품 문의하기");

          case "4" :
            BoardReview.service("리뷰 남기기");

          case "0" :
            System.out.println("전화면으로 돌아갑니다!");
            break loop;

          default :
            System.out.println("잘못된 메뉴 번호 입니다! 다시 입력해 주시겠어요?");

        }
        System.out.println();
      }
  }
  private static void printCommandHistory(Iterator<String> iterator) {

    int count = 0;
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
      if(++count % 5 == 0) {
        String input = Prompt.inputString(": ");
        if(input.equalsIgnoreCase("q")) {
          break;
        }

      }
    }
  }

}