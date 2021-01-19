package com.kyung.pms;

public class App {

  public static void main(String[] args) {


    while(true){
      System.out.println("명령어 = /user/add, /user/list, /champion/add, /champion/list, /tier/add, /tier/list");
      String command = Prompt.inputString("명령> ");

      if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println();
        System.out.println("사용해주셔서 감사합니다.");
        break;

      }else if(command.equalsIgnoreCase("/user/add")) {
        System.out.println();
        UserHandler.add();

      }else if(command.equalsIgnoreCase("/user/list")) {
        System.out.println();
        UserHandler.list();

      }else if(command.equalsIgnoreCase("/champion/add")){
        System.out.println();
        ChampionHandler.add();

      }else if(command.equalsIgnoreCase("/champion/list")) {
        System.out.println();
        ChampionHandler.list();

      }else if(command.equalsIgnoreCase("/tier/add")) {
        System.out.println();
        TierHandler.add();

      }else if(command.equalsIgnoreCase("/tier/list")) {
        System.out.println();
        TierHandler.list();


      }else {
        System.out.println("실행할 수 없는 명령입니다.");
      }

      System.out.println();

    }

    Prompt.close();
  }
}
