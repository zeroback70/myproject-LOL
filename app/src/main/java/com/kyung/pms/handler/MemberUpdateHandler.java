package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  public MemberUpdateHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 회원 > 수정]");

    Member member = findByNo(Prompt.inputInt("번호? "));
    if(member == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",member.getName()));
      String id = Prompt.inputString(String.format("아이디(%s)? ",member.getId()));
      String tel = Prompt.inputString(String.format("전화번호(%s)? ",member.getTel()));
      String address = Prompt.inputString(String.format("주소(%s)? ",member.getAddress()));
      String email = Prompt.inputString(String.format("메일(%s)? ",member.getEmail()));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y: 비밀번호 입력) ");
      if(userChoice.equals(member.getPassword())) {
        member.setName(name);
        member.setId(id);
        member.setTel(tel);
        member.setAddress(address);
        member.setEmail(email);
        System.out.println("회원 정보 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("잘못된 비밀번호입니다. 회원 정보 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }
}