package com.kyung.pms.handler;

import com.kyung.pms.domain.Item;
import com.kyung.util.Prompt;

public class ItemHandler {

  static final int LENGTH = 100;
  Item[] Items = new Item[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("『중고상품 등록』");
    Item p = new Item();

    p.number = Prompt.promptInt("상품 번호: ");
    p.name = Prompt.promptString("상품 이름: ");
    p.price = Prompt.promptInt("상품 가격: ");

    this.Items[size++] = p;
    System.out.println();
  }
  public void list() {
    System.out.println("『중고상품 목록』");
    for(int i = 0; i < this.size; i++) {
      Item p = this.Items[i];
      System.out.printf("번호: %d 이름: %s 가격: %d원\n", p.number, p.name, p.price);
      System.out.println("-----------------------------------------------------");
    }
  }
  boolean exist(String name) {
    for(int i = 0; i < this.size; i++) {
      if(name.equals(this.Items[i].name)) {
        return true;
      }
    }
    return false;
  }

  public void detail() {
    System.out.println("『중고상품 상세보기』");

    Item Item = findByNo(Prompt.promptInt("번호? "));

    if (Item == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("번호: %s ", Item.number);
      System.out.printf("이름: %s\n", Item.name);
      System.out.printf("가격: %s원\n", Item.price);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("『수정하기』");

    Item Item = findByNo(Prompt.promptInt("번호? "));
    if(Item == null) {

      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.promptString(String.format("이름(%s)? ",Item.name));
      int price = Prompt.promptInt(String.format("가격(%s원)? ",Item.price));

      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        Item.name = name;
        Item.price = price;
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }


  public void delete() {
    System.out.println("『상품판매글 삭제』");

    int index = indexOf(Prompt.promptInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        for(int x = index + 1; x < this.size; x++) {

          Items[x - 1] = Items[x];
        }
        this.Items[--this.size] = null;
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


  int indexOf(int ItemNo) {
    for(int i = 0; i < this.size; i++) {
      Item Item = this.Items[i];
      if(ItemNo == Item.number) {
        return i;
      }
    }
    return -1;
  }

  Item findByNo(int ItemNo) {
    int i = indexOf(ItemNo);
    if(i == -1) {
      return null;
    }else {
      return this.Items[i];
    }
  }
}