package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberDeleteHandler  extends AbstractMemberHandler {

  public MemberDeleteHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 회원 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Member member = findByNo(no);
    if(member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        memberList.remove(member);

        System.out.println("회원 정보 삭제를 완료하였습니다.");
        System.out.println();
      }else {

        System.out.println("회원 정보 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }
}