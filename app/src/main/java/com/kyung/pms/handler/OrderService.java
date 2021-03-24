package com.kyung.pms.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import com.kyung.pms.domain.Order;

public  class OrderService {

  private ArrayList<Order> orderList = new ArrayList<>();
  public ArrayList<Order> getOrderList() {
    return orderList;
  }
  private MemberValidatorHandler memberValidatorHandler;
  private UseditemValidatorHandler UseditemValidatorHandler;

  public OrderService(MemberValidatorHandler memberValidatorHandler, UseditemValidatorHandler UseditemValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.UseditemValidatorHandler = UseditemValidatorHandler;
  }

  public void menu() {

    loadOrders();

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new OrderAddHandler(memberValidatorHandler, UseditemValidatorHandler, orderList));
    commandMap.put("2", new OrderListHandler(orderList));
    commandMap.put("3", new OrderDetailHandler(orderList));
    commandMap.put("4", new OrderUpdateHandler(memberValidatorHandler, UseditemValidatorHandler, orderList));
    commandMap.put("5", new OrderDeleteHandler(orderList));


    loop:
      while(true) {
        System.out.println("[메인 > 주문]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("명령> ");
        System.out.println();
        try {
          switch(command) {
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              Command commandHandler = commandMap.get(command);
              if(commandHandler == null) {
                System.out.println("실행할 수 없는 메뉴 번호 입니다.");
              }else {
                commandHandler.service();
              }
          }
        }catch(Exception e){
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
    saveOrders();
  }

  private void loadOrders() {
    try(FileInputStream in = new FileInputStream("orders.data")) {
      int size = in.read() << 8 | in.read();

      for (int i = 0; i < size; i++) {
        Order order = new Order();

        byte[] bytes = new byte[30000];

        int len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        order.setMemberId(new String(bytes,0,len,"UTF-8"));

        int value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        order.setNumber(value);

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        order.setUseditems(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        order.setRegisteredDate(Date.valueOf(new String(bytes, 0, len, "UTF-8")));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        order.setRequest(new String(bytes, 0, len, "UTF-8"));

        value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        order.setTotalPrice(value);

        orderList.add(order);
      }
      System.out.println("주문 데이터 로딩!");

    } catch (Exception e) {
      System.out.println("주문 데이터 로딩 중 오류 발생!");
    }
  }

  private void saveOrders() {
    try(FileOutputStream  out = new FileOutputStream("orders.data")) {

      int size = orderList.size();
      out.write(size >> 8);
      out.write(size);

      for (Order o : orderList) {

        byte[] buf = o.getMemberId().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        out.write(o.getNumber() >> 24);
        out.write(o.getNumber() >> 16);
        out.write(o.getNumber() >> 8);
        out.write(o.getNumber());

        buf = o.getUseditems().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = o.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = o.getRequest().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        out.write(o.getTotalPrice() >> 24);
        out.write(o.getTotalPrice() >> 16);
        out.write(o.getTotalPrice() >> 8);
        out.write(o.getTotalPrice());

      }
      System.out.println("주문 데이터 저장");

    } catch (Exception e) {
      System.out.println("주문 데이터 파일로 저장하는 중에 오류 발생!");
    }
  }
}