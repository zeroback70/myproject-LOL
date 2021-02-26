package com.kyung.pms.handler;

import java.util.List;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberValidatorHandler extends AbstractMemberHandler {

  @Override
  public void service() {}

  public MemberValidatorHandler(List<Member> memberList) {
    super(memberList);
  }

  public String inputMemberId(){
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

  public String inputMember(String promptTitle) {
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

  @Override
  protected Member findByNo(int memberNo) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getNumber() == memberNo) {
        return m;
      }
    }
    return null;
  }

  @Override
  protected Member findById(String id) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

}