package com.kyung.pms.handler;
// 2021-03-31 Update
import java.util.List;
import com.kyung.pms.domain.Useditem;

public abstract class AbstractUseditemHandler implements Command {

  protected List<Useditem> UseditemList;

  public AbstractUseditemHandler(List<Useditem> UseditemList) {
    this.UseditemList = UseditemList;
  }

  protected Useditem findByNo(int UseditemNo) {
    Useditem[] list = UseditemList.toArray(new Useditem[UseditemList.size()]);
    for(Useditem p : list) {
      if(p.getNumber() == UseditemNo) {
        return p;
      }
    }
    return null;
  }

  protected Useditem findByName(String UseditemName) {
    Useditem[] list = UseditemList.toArray(new Useditem[UseditemList.size()]);
    for(Useditem p : list) {
      if(p.getName().equals(UseditemName)) {
        return p;
      }
    }
    return null;
  }
}