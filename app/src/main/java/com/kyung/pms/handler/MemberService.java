package com.kyung.pms.handler;

import java.util.LinkedList;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberService {

  private LinkedList<Member> memberList = new LinkedList<>();
  public LinkedList<Member> getMemberList() {
    return memberList;
  }

  MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
  MemberListHandler memberListHandler = new MemberListHandler(memberList);
  MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberList);
  MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList);
  MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberList);

  //  MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);
  //  public MemberValidatorHandler getMemberValidatorHandler() {
  //    return memberValidatorHandler;
  //  }

  public void menu() {

    loop:

      while(true) {
        System.out.println("[메인 > 회원]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("명령> ");
        System.out.println();
        try {
          switch(command) {
            case "1" :
              memberAddHandler.service();
              break;
            case "2" :
              memberListHandler.service();
              break;
            case "3" :
              memberDetailHandler.service();
              break;
            case "4" :
              memberUpdateHandler.service();
              break;
            case "5" :
              memberDeleteHandler.service();
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
          System.out.println("-----------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n",e.getClass().getName(), e.getMessage());
          System.out.println("-----------------------------------------------------------------------------");
        }
        System.out.println();
      }
  }

  String inputMemberId(){
    while(true) {
      String id = Prompt.inputString("회원 아이디(enter(취소)): ");
      if(id.equals("")) {
        return null;
      }
      if(findById(id) != null) {
        return id;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  String inputMember(String promptTitle) {
    while(true) {
      String id = Prompt.inputString(promptTitle);
      if(id.length() == 0) {
        return null;
      }else if(findById(id) != null) {
        return id;
      }
      System.out.println("잘못된 아이디 입니다.");
    }
  }

  protected Member findByNo(int memberNo) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getNumber() == memberNo) {
        return m;
      }
    }
    return null;
  }

  private Member findById(String id) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }
}
