package com.eomcs.lang;


import java.sql.Date;
import java.util.Scanner;


public class App {

  public static void main(String[] args) {
    Scanner keyboardScan = new Scanner(System.in);


    final int LENGTH = 10;
    int[] no = new int[LENGTH];
    String[] name = new String[LENGTH];
    String[] email = new String[LENGTH];
    String[] password = new String[LENGTH];
    String[] photo = new String[LENGTH];
    String[] tel = new String[LENGTH];
    Date[] registeredDate = new Date[LENGTH];
    int size = 0;

    
    int[] pno = new int[LENGTH];
    String[] ptitle = new String[LENGTH];
    String[] pcontent = new String[LENGTH];
    Date[] pstartDate = new Date[LENGTH];
    Date[] pendDate = new Date[LENGTH];
    String[] powner = new String[LENGTH];
    String[] pmembers = new String[LENGTH];
    int psize = 0;

    

    int[] tno = new int[LENGTH];
    String[] tcontent = new String[LENGTH];
    Date[] tdeadline = new Date[LENGTH];
    String[] towner = new String[LENGTH];
    int[] tstatus = new int[LENGTH];    
    int tsize = 0;



    while(true){
      System.out.print("명령> ");
      String input = keyboardScan.nextLine();

      if(input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
        System.out.println("명령을 그만받습니다. ");
        break;

      }else if(input.equalsIgnoreCase("/member/add")) {

        System.out.println("소환사 정보");

        System.out.print("번호는요? ");
        no[size] = Integer.parseInt(keyboardScan.nextLine());

        System.out.print("닉네임은요? ");
        name[size] = keyboardScan.nextLine();

        System.out.print("아이디는요? ");
        email[size] = keyboardScan.nextLine();

        System.out.print("암호는요? ");
        password[size] = keyboardScan.nextLine();

        System.out.print("얼굴은요 ?");
        photo[size] = keyboardScan.nextLine();

        System.out.print("번호는요? ");
        tel[size] = keyboardScan.nextLine();

        registeredDate[size] = new java.sql.Date(System.currentTimeMillis());

        size++;
      }else if(input.equalsIgnoreCase("/member/list")) {
        System.out.println("소환사 정보");

        for (int i = 0; i < size; i++) {
          
          System.out.printf("%d, %s, %s, %s, %s\n", 
              no[i], name[i], email[i], tel[i], registeredDate[i]);
        }

      }else if(input.equalsIgnoreCase("/project/add")){
        System.out.println("게임 프로젝트 정보");

        System.out.print("번호는요 ?");
        pno[psize] = Integer.valueOf(keyboardScan.nextLine());

        System.out.print("게임 프로젝트는요? ");
        ptitle[psize] = keyboardScan.nextLine();

        System.out.print("프로젝트 내용은요? ");
        pcontent[psize] = keyboardScan.nextLine();

        System.out.print("언제 시작했어요! ");
        pstartDate[psize] = Date.valueOf(keyboardScan.nextLine());

        System.out.print("언제 끝나요!? ");
        pendDate[psize] = Date.valueOf(keyboardScan.nextLine());

        System.out.print(" 누가 만들었어요? ");
        powner[psize] = keyboardScan.nextLine();

        System.out.print("동료는 누구에요? ");
        pmembers[psize] = keyboardScan.nextLine();

        psize++;

      }else if(input.equalsIgnoreCase("/project/list")) {
        System.out.println("게임 프로젝트 목록> ");

        for (int i = 0; i < psize; i++) {
          
          System.out.printf("%d, %s, %s, %s, %s\n", 
              pno[i], ptitle[i], pstartDate[i], pendDate[i], powner[i]);
        }

      }else if(input.equalsIgnoreCase("/task/add")) {
        System.out.println("[작업 등록]");

        System.out.print("번호는요? ");
        tno[tsize] = Integer.parseInt(keyboardScan.nextLine());

        System.out.print("작업 내용은요? ");
        tcontent[tsize] = keyboardScan.nextLine();

        System.out.print("마감일은요? ");
        tdeadline[tsize] = Date.valueOf(keyboardScan.nextLine());

        System.out.println("현재 상태는요?");
        System.out.println("0: 이제 막 시작");
        System.out.println("1: 진행중");
        System.out.println("2: 완료");
        System.out.print("> ");
        tstatus[tsize] = Integer.valueOf(keyboardScan.nextLine());

        System.out.print("담당자는요? ");
        towner[tsize] = keyboardScan.nextLine();

        tsize++;

      }else if(input.equalsIgnoreCase("/task/list")) {
        System.out.println("[작업 목록]");


        for (int i = 0; i < tsize; i++) {
          String stateLabel = null;
          switch (tstatus[i]) {
            case 1:
              stateLabel = "진행중";
              break;
            case 2:
              stateLabel = "완료";
              break;
            default:
              stateLabel = "신규";
          }
          
          System.out.printf("%d, %s, %s, %s, %s\n", 
              tno[i], tcontent[i], tdeadline[i], stateLabel, towner[i]);
        }


      }else {
        System.out.println("실행할 수 없어요.");
      }
      System.out.println();
    }

    keyboardScan.close();
  }
}
