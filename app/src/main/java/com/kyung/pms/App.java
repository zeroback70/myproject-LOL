package com.kyung.pms;

import com.kyung.pms.handler.WriteHandler;
import com.kyung.pms.handler.MemberHandler;
import com.kyung.pms.handler.ItemHandler;
import com.kyung.pms.handler.DeliverHandler;
import com.kyung.util.Prompt;

public class App {

  public static void main(String[] args) {

    WriteHandler writeProduct = new WriteHandler();
    WriteHandler writedeliver = new WriteHandler();
    WriteHandler writeReturn = new WriteHandler();
    MemberHandler memberList = new MemberHandler();
    ItemHandler itemList = new ItemHandler();
    DeliverHandler DeliverList = new DeliverHandler(memberList);

    while(true) {
      System.out.println("토끼마켓");
      System.out.println(" _______________________________________________ ");
      System.out.println("｜회원가입｜회원목록｜회원상세｜회원수정｜탈퇴하기｜");
      System.out.println("｜중고팔기｜중고목록｜상세보기｜수정하기｜상품삭제｜");
      System.out.println("｜배송하기｜배송목록｜배송현황｜배송수정｜배송취소｜");
      System.out.println("｜문의하기｜불량회원신고｜");
      System.out.println();

      String command = Prompt.promptString("입력_ ");

      if(command.equalsIgnoreCase("회원가입")) {
        memberList.add();

      }else if(command.equalsIgnoreCase("회원목록")) {
        memberList.list();

      }else if(command.equalsIgnoreCase("회원상세")) {
        memberList.detail();

      }else if(command.equalsIgnoreCase("회원수정")) {
        memberList.update();

      }else if(command.equalsIgnoreCase("탈퇴하기")) {
        memberList.delete();

      }else if (command.equalsIgnoreCase("중고팔기")) {
        itemList.add();

      }else if (command.equalsIgnoreCase("중고목록")) {
        itemList.list();

      }else if (command.equalsIgnoreCase("수정하기")) {
        itemList.detail();

      }else if (command.equalsIgnoreCase("상품수정")) {
        itemList.update();

      }else if (command.equalsIgnoreCase("상품삭제")) {
        itemList.delete();

      }else if (command.equalsIgnoreCase("배송하기")) {
        DeliverList.add();

      }else if (command.equalsIgnoreCase("배송목록")) {
        DeliverList.list();

      }else if(command.equalsIgnoreCase("배송현황")) {
        DeliverList.detail();

      }else if(command.equalsIgnoreCase("배송수정")) {
        DeliverList.update();

      }else if(command.equalsIgnoreCase("배송취소")) {
        DeliverList.delete();


      }else if(command.equals("문의하기")) {
        while(true) {
          System.out.println("[상담원 연결중입니다....]");
          System.out.println("원하시는 버튼을 입력해주세요!");
          System.out.println(" _________________________________________________________________ ");
          System.out.println("｜상품문의｜상품문의 목록｜상품문의 상세｜상품문의 수정｜상품문의 삭제｜");
          System.out.println("｜배송문의｜배송문의 목록｜");
          System.out.println("｜반품문의｜반품문의 목록｜");
          System.out.println("｜뒤로가기｜");
          System.out.println();
          command = Prompt.promptString("명령> ");

          if (command.equalsIgnoreCase("상품 문의")) {
            writeProduct.add();

          }else if (command.equalsIgnoreCase("상품문의 목록")) {
            writeProduct.list();

          }else if (command.equalsIgnoreCase("상품문의 상세")) {
            writeProduct.detail();

          }else if (command.equalsIgnoreCase("상품문의 수정")) {
            writeProduct.update();

          }else if (command.equalsIgnoreCase("상품문의 삭제")) {
            writeProduct.delete();

          }else if (command.equalsIgnoreCase("배송문의")) {
            writedeliver.add();

          }else if (command.equalsIgnoreCase("배송문의 목록")) {
            writedeliver.list();

          }else if (command.equalsIgnoreCase("반품 문의")) {
            writeReturn.add();

          }else if (command.equalsIgnoreCase("반품 문의 목록")) {
            writeReturn.list();

          }else if (command.equalsIgnoreCase("뒤로가기")) {

            break;
          }
        }
      }else if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println("감사합니다.");
        break;
      }else {
        System.out.println("잘못 입력하셨습니다.");
        System.out.println();
      }
    }
    Prompt.close();
  }


}