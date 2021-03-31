package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Deliver;
import com.kyung.util.Prompt;

public class DeliverDeleteHandler extends AbstractDeliverHandler {

  public DeliverDeleteHandler(List<Deliver> DeliverList) {
    super(DeliverList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Deliver Deliver = findByNo(no);
    if(Deliver == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        DeliverList.remove(Deliver);
        System.out.println("배송 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("배송 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }
}
