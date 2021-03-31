package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemAddHandler extends AbstractUseditemHandler {

  public UseditemAddHandler(List<Useditem> UseditemList) {
    super(UseditemList);
    this.UseditemList = UseditemList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 등록]");
    Useditem p = new Useditem();

    p.setNumber(Prompt.inputInt("상품 번호: "));
    p.setName(Prompt.inputString("상품 이름: "));
    p.setPrice(Prompt.inputInt("상품 가격: "));
    p.setPhoto(Prompt.inputString("상품 사진: "));

    UseditemList.add(p);
    System.out.println();
  }

}
