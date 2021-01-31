package com.kyung.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);
  //사용자에게 명령을 입력 받는 메소드
  public static String promptString (String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  //명령으로 string 입력받아 int값으로 변환해주는 메소드
  public static int promptInt (String title) {
    String str = promptString(title);
    return Integer.valueOf(str);
  }
  //명령으로 string 입력받아 Date값으로 변환해주는 메소드
  public static Date promptDate (String title) {
    String str = promptString(title);
    return Date.valueOf(str);
  }

  public static void close() {
    scanner.close();
  }
}