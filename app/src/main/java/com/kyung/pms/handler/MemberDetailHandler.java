package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberDetailHandler  extends AbstractMemberHandler {

  public MemberDetailHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 회원 > 상세]");

    Member member = findByNo(Prompt.inputInt("번호? "));

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("아이디: %s\n", member.getId());
      System.out.printf("전화번호: %s\n", member.getTel());
      System.out.printf("주소: %s\n", member.getAddress());
      System.out.printf("메일: %s\n", member.getEmail());
      System.out.printf("가입 날짜: %s\n", member.getJoinDate());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }
}