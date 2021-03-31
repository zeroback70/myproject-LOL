package com.kyung.pms.handler;
// 2021-03-31 Update
import java.sql.Date;
import java.util.List;
import com.kyung.pms.domain.Order;
import com.kyung.util.Prompt;

public class OrderAddHandler extends AbstractOrderHandler {

  private MemberValidatorHandler memberValidatorHandler;
  private UseditemValidatorHandler UseditemValidatorHandler;

  public OrderAddHandler(MemberValidatorHandler memberValidatorHandler, UseditemValidatorHandler UseditemValidatorHandler, List<Order> orderList){
    super(orderList);
    this.memberValidatorHandler = memberValidatorHandler;
    this.UseditemValidatorHandler = UseditemValidatorHandler;
  }

  @Override
  public void service() {

    System.out.println("[메인 > 주문 > 등록]");

    Order o = new Order();

    o.setNumber(Prompt.inputInt("주문 번호: "));

    o.setMemberId(memberValidatorHandler.inputMember("회원 아이디(enter(취소)): ")); 
    if(o.getMemberId() == null) {
      System.out.println("주문 등록을 취소합니다.");
      System.out.println();
      return;
    }

    o.setUseditems(UseditemValidatorHandler.inputUseditems("상품명(enter(완료)): "));

    o.setRequest(Prompt.inputString("요청사항: "));
    o.setRegisteredDate(new Date(System.currentTimeMillis()));

    orderList.add(o);

    System.out.println();
  }
}