package com.kyung.pms;

import java.sql.Date;

public class ChampionHandler {

  // 챔피언 정보 ChampionHandler
  static final int LENGTH = 100;
  static int[] cno = new int[LENGTH]; // 챔피언 번호
  static String[] cname = new String[LENGTH]; // 챔피언 이름
  static String[] cpower = new String[LENGTH]; // 챔피언 성능
  static String[] cskin = new String[LENGTH]; // 챔피언 스킨
  static String[] clevel = new String[LENGTH]; // 챔피언 숙련도
  static Date[] noticeDate = new Date[LENGTH]; // 발매일 

  static int csize = 0;

  static void add() {
    System.out.println("[챔피언 등록]");

    cno[csize] = Prompt.inputInt("번호> ");
    cname[csize] = Prompt.inputString("이름> ");
    cpower[csize] = Prompt.inputString("성능> ");
    cskin[csize] = Prompt.inputString("스킨> ");
    clevel[csize] = Prompt.inputString("숙련도> ");

    noticeDate[csize] = new java.sql.Date(System.currentTimeMillis());

    csize++;
  }

  static void list() {
    System.out.println("[챔피언 목록]");

    for (int i = 0; i < csize; i++) {
      System.out.printf("[%d] ㅣ 챔피언 이름 : %s, 성능 : %s, 스킨 : %s, 숙련도 : %s\n", 
          cno[i], cname[i], cpower[i], cskin[i], clevel[i]);
    }
  }
}