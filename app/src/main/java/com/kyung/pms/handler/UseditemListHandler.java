package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.Iterator;
import java.util.List;
import com.kyung.pms.domain.Useditem;

public class UseditemListHandler extends AbstractUseditemHandler {

  public UseditemListHandler(List<Useditem> UseditemList) {
    super(UseditemList);
    this.UseditemList = UseditemList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 목록]");

    Iterator<Useditem> iterator = UseditemList.iterator();

    while (iterator.hasNext()) {
      Useditem p = iterator.next();
      System.out.printf("사진: %s\n이름: %s 가격: %d원\n",p.getPhoto(), p.getName(), p.getPrice());
      System.out.println("-----------------------------------------------------");

    }
  }
}