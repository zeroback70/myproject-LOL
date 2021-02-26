package com.kyung.pms.handler;

import java.util.LinkedList;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemService {

  LinkedList<Useditem> UseditemList = new LinkedList<>();

  public LinkedList<Useditem> getUseditemList() {
    return UseditemList;
  }

  UseditemAddHandler UseditemAddHandler = new UseditemAddHandler(UseditemList);
  UseditemListHandler UseditemListHandler = new UseditemListHandler(UseditemList);
  UseditemDetailHandler UseditemDetailHandler = new UseditemDetailHandler(UseditemList);
  UseditemUpdateHandler UseditemUpdateHandler = new UseditemUpdateHandler(UseditemList);
  UseditemDeleteHandler UseditemDeleteHandler = new UseditemDeleteHandler(UseditemList);

  public void menu() {

    loop:
      while(true) {
        System.out.println("[메인 > 상품]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = Prompt.inputString("명령> ");
        System.out.println();
        try {
          switch(command) {
            case "1" :
              UseditemAddHandler.service();
              break;
            case "2" :
              UseditemListHandler.service();
              break;
            case "3" :
              UseditemDetailHandler.service();
              break;
            case "4" :
              UseditemUpdateHandler.service();
              break;
            case "5" :
              UseditemDeleteHandler.service();
              break;
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              System.out.println("잘못된 메뉴 번호 입니다.");
              System.out.println();

          }
        }catch(Exception e) {
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
  }
}