package com.kyung.pms;

import java.sql.Date;

public class UserHandler {

  //게임 유저 회원 -> User
  static final int LENGTH = 100;
  static int[] no = new int[LENGTH]; // 유저 번호 
  static String[] name = new String[LENGTH]; // 유저 이름
  static String[] country = new String[LENGTH]; // 유저 소속 국가
  static String[] id = new String[LENGTH]; // 유저 아이디
  static String[] password = new String[LENGTH]; // 유저 비밀 번호
  static String[] nickname = new String[LENGTH]; // 유저 닉네임
  static String[] tel = new String[LENGTH]; // 유저 전화 번호
  static Date[] registeredDate = new Date[LENGTH]; // 가입일
  
  static int size = 0;

  static void add() {
    System.out.println("[회원 가입]");

    no[size] = Prompt.inputInt("번호> ");
    name[size] = Prompt.inputString("이름> ");
    country[size] = Prompt.inputString("국가> ");
    id[size] = Prompt.inputString("이메일> ");
    password[size] = Prompt.inputString("비밀번호> ");
    nickname[size] = Prompt.inputString("닉네임> ");
    tel[size] = Prompt.inputString("전화> ");

    registeredDate[size] = new java.sql.Date(System.currentTimeMillis());

    size++;
  }

  static void list() {
    System.out.println("[회원 목록]");

    for (int i = 0; i < size; i++) {
      System.out.printf("[%d] ㅣ 이름 : %s, 소속 국가 : %s, 아이디 : %s, 닉네임 : %s, 전화번호 : %s, 가입일 : %s\n", 
          no[i], name[i], country[i], id[i], nickname[i], tel[i], registeredDate[i]);
    }
  }
}
