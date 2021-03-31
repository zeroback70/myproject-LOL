package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemDeleteHandler extends AbstractUseditemHandler {

  public UseditemDeleteHandler(List<Useditem> useditemList) {
    super(useditemList);
    this.UseditemList = useditemList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Useditem useditem = findByNo(no);
    if(useditem == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        UseditemList.remove(useditem);

        System.out.println("상품 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("상품 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }
}