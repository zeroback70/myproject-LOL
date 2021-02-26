package com.kyung.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.kyung.pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 회원 > 목록]");

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();

      System.out.printf("번호: %d 아이디: %s 이름: %s\n가입 날짜: %s\n"
          ,m.getNumber(), m.getId(), m.getName(), m.getJoinDate());
      System.out.println("--------------------------------------------------");

    }

    System.out.println();
  }
}