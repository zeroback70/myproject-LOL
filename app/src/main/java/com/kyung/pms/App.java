package com.kyung.pms;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.kyung.pms.handler.BoardServiceExchangeReturn;
import com.kyung.pms.handler.BoardServiceUseditem;
import com.kyung.pms.handler.BoardServiceReview;
import com.kyung.pms.handler.BoardServiceDeliver;
import com.kyung.pms.handler.MemberService;
import com.kyung.pms.handler.MemberValidatorHandler;
import com.kyung.pms.handler.OrderService;
import com.kyung.pms.handler.OrderValidatorHandler;
import com.kyung.pms.handler.UseditemService;
import com.kyung.pms.handler.UseditemValidatorHandler;
import com.kyung.pms.handler.DeliverService;
import com.kyung.util.Prompt;

// 2021-03-21 Update
public class App {

  static BoardServiceUseditem boardServiceUseditem = new BoardServiceUseditem();
  static BoardServiceReview boardServiceReview = new BoardServiceReview();
  static BoardServiceDeliver boardServiceDeliver = new BoardServiceDeliver();
  static BoardServiceExchangeReturn boardServiceExchangeReturn = new BoardServiceExchangeReturn();

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException{

    MemberService memberService = new MemberService();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberService.getMemberList());

    UseditemService UseditemService = new UseditemService();
    UseditemValidatorHandler UseditemValidatorHandler = new UseditemValidatorHandler(UseditemService.getUseditemList());

    OrderService orderService = new OrderService(memberValidatorHandler, UseditemValidatorHandler);
    OrderValidatorHandler orderValidatorHandler = new OrderValidatorHandler(orderService.getOrderList());

    DeliverService DeliverService = new DeliverService(memberValidatorHandler, orderValidatorHandler);

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

        String command = Prompt.inputString("번호 입력(0~5)>> ");
        System.out.println();

        commandStack.push(command);
        commandQueue.offer(command);

        switch(command) {
          case "1" :
            memberService.menu();
            break;
          case "2" :
            UseditemService.menu();
            break;
          case "3" :
            orderService.menu();
            break;
          case "4" :
            DeliverService.menu();
            break;
          case "5" :
            chooseBoard();
            break;
          case "0" :
            System.out.println("이용해주셔서 감사합니다! \n 더 나은 서비스로 보답하겠습니다!");
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
            System.out.println("번호를 잘못 입력하신것 같습니다! 0번부터 5번까지 다시 입력해주시겠어요?");
            System.out.println();

        }
      }
  }

  public static void chooseBoard() throws CloneNotSupportedException {
    while(true) {
      System.out.println("[메인 > 게시판]");
      System.out.println("1. 상품 문의");
      System.out.println("2. 배송 문의");
      System.out.println("3. 교환/반품 문의");
      System.out.println("4. 리뷰 남기기");
      System.out.println("0. 이전 메뉴로 돌아가기");
      System.out.println();

      String command = com.sunwoo.util.Prompt.inputString("명령> ");
      System.out.println();

      switch(command) {
        case "1" :
          boardServiceProduct.menu("상품 문의");
          break;
        case "2" :
          boardServiceShipping.menu("배송 문의");
          break;
        case "3" :
          boardServiceExchangeReturn.menu("교환/반품 문의");
          break;
        case "4" :
          boardServiceReview.menu("리뷰 남기기");
          break;
        case "0" :
          System.out.println("메인으로 돌아갑니다.");
          System.out.println();
          return;
        default :
          System.out.println("잘못된 메뉴 번호 입니다.");

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