package com.kyung.pms.handler;

import java.util.List;
import com.kyung.pms.domain.Useditem;
import com.kyung.util.Prompt;

public class UseditemValidatorHandler extends AbstractUseditemHandler {

  @Override
  public void service() {}

  public UseditemValidatorHandler(List<Useditem> UseditemList) {
    super(UseditemList);
  }

  public String inputUseditems(String promptTitle) {
    String Useditems = "";
    while(true) {
      String name = Prompt.inputString(promptTitle);
      if(name.isEmpty()) {
        return Useditems;
      }
      if(findByName(name) != null) {
        if(Useditems.length() != 0) {
          Useditems += ", ";
        }
        Useditems += name;
      }else {
        System.out.println("등록된 상품이 아닙니다.");
      }
    }
  }
}