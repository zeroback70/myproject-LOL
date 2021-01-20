package com.kyung.pms;

import java.sql.Date;

public class TierHandler {

  // 실력을 나타내는 Tier 핸들러
  static final int LENGTH = 100;
  static int[] tno = new int[LENGTH]; // 번호
  static int[] tstars = new int[LENGTH]; // 평점
  static String[] tbrtodia = new String[LENGTH]; // 브론즈~다이아
  static String[] tname = new String[LENGTH]; // 관리자
  static Date[] recentDate = new Date[LENGTH]; // 최근 게임일
  static int[] tonefour = new int[LENGTH]; //  티어 1~4

  static int tsize = 0;

  static void add() {
    System.out.println("[티어 등록]");

    tno[tsize] = Prompt.inputInt("번호> ");
    tstars[tsize] = Prompt.inputInt("평점> (1~10) ");
    tbrtodia[tsize] = Prompt.inputString("티어> 1.브론즈 2.실버 3.골드 4.플레티넘 5.다이아 ");
    tname[tsize] = Prompt.inputString("관리자 이름> ");
    recentDate[tsize] = new java.sql.Date(System.currentTimeMillis());
    tonefour[tsize] = Prompt.inputInt("티어상태?\n 1: 1티어\n 2: 2티어\n 3: 3티어\n> ");

    tsize++;
  }

  static void list() {
    System.out.println("[티어 목록]");


    for (int i = 0; i < tsize; i++) {
      String tlevel = null;
      switch (tonefour[i]) {
        case 1:
          tlevel = "모험실패";
          break;
        case 2:
          tlevel = "모험중";
          break;
        case 3:
          tlevel = "3티어";
          break;
        default:
          tlevel = "4티어";
      }
      //출력
      System.out.printf("[%d] ㅣ 평점: %d, 티어: %s, 관리자: %s, 기준일: %s, 현재 티어는 %s이고 %s단계 입니다.\n", 
          tno[i], tstars[i], tbrtodia[i], tname[i], recentDate[i], tbrtodia[i], tlevel);
    }
  }

}
