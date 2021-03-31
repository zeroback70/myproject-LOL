package com.kyung.pms.handler;
// 2021-03-31 Update
import java.sql.Date;
import java.util.List;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service(){
    System.out.println("[메인 > 회원 > 등록]");
    Member m = new Member();

    m.setNumber(Prompt.inputInt("회원 번호: "));
    m.setName(Prompt.inputString("이름: "));
    m.setId(Prompt.inputString("아이디: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setTel(Prompt.inputString("전화번호: "));
    m.setAddress(Prompt.inputString("주소: "));
    m.setEmail(Prompt.inputString("메일: "));
    m.setJoinDate(new Date(System.currentTimeMillis()));

    memberList.add(m);
    System.out.println();
    System.out.println("회원 등록이 완료되었습니다.");
    System.out.println();
  }
}