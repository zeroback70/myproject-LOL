package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemUpdateHandler extends AbstractUseditemHandler{

  public UseditemUpdateHandler(List<Useditem> UseditemList) {
    super(UseditemList);
    this.UseditemList = UseditemList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 수정하기]");

    Useditem Useditem = findByNo(Prompt.inputInt("번호를 입력해주세요! (0~5) >> "));
    if(Useditem == null) {

      System.out.println("해당 번호의 상품이 없습니다!");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",Useditem.getName()));
      String photo = Prompt.inputString(String.format("사진(%s)? ",Useditem.getPhoto()));
      int price = Prompt.inputInt(String.format("가격(%s원)? ",Useditem.getPrice()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        Useditem.setName(name);
        Useditem.setPhoto(photo);
        Useditem.setPrice(price);
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }
}