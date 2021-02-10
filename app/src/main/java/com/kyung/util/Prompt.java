package com.kyung.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);
  
  public static String inputString (String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static int inputInt (String title) {
    String str = inputString(title);
    return Integer.valueOf(str);
  }
  
  public static Date inputDate (String title) {
    String str = inputString(title);
    return Date.valueOf(str);
  }

  public static void close() {
    scanner.close();
  }
}