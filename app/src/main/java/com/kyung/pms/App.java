package com.kyung.pms;

public class App {

  public static void main(String[] args) {


    while(true){
      System.out.println("명령어 = 유저등록, 유저명단, 챔피언등록, 챔피언명단, 티어등록, 티어명단");
      String command = Prompt.inputString("명령> ");

      if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println();
        System.out.println("사용해주셔서 감사합니다.");
        break;

      }else if(command.equalsIgnoreCase("유저등록")) {
        System.out.println();
        UserHandler.add();

      }else if(command.equalsIgnoreCase("유저명단")) {
        System.out.println();
        UserHandler.list();

      }else if(command.equalsIgnoreCase("챔피언등록")){
        System.out.println();
        ChampionHandler.add();

      }else if(command.equalsIgnoreCase("챔피언명단")) {
        System.out.println();
        ChampionHandler.list();

      }else if(command.equalsIgnoreCase("티어등록")) {
        System.out.println();
        TierHandler.add();

      }else if(command.equalsIgnoreCase("티어명단")) {
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
