package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemDetailHandler extends AbstractUseditemHandler {

  public UseditemDetailHandler(List<Useditem> UseditemList) {
    super(UseditemList);
    this.UseditemList = UseditemList;
  }

  @Override
  public void service() {
    System.out.println("메인 > 상품 > 상세 보기]");

    Useditem useditem = findByNo(Prompt.inputInt("번호? "));

    if (useditem == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("번호: %s ", useditem.getNumber());
      System.out.printf("이름: %s\n", useditem.getName());
      System.out.printf("사진: %s\n", useditem.getPhoto());
      System.out.printf("가격: %s원\n", useditem.getPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }
}
