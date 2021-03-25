package com.kyung.pms.handler;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberService {

  static LinkedList<Member> memberList = new LinkedList<>();
  public LinkedList<Member> getMemberList() {
    return memberList;
  }

  public void menu() {

    loadMembers();
    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new MemberAddHandler(memberList));
    commandMap.put("2", new MemberListHandler(memberList));
    commandMap.put("3", new MemberDetailHandler(memberList));
    commandMap.put("4", new MemberUpdateHandler(memberList));
    commandMap.put("5", new MemberDeleteHandler(memberList));

    loop:
      while(true) {
        System.out.println("[메인 > 회원]");
        System.out.println("1. 등록하기");
        System.out.println("2. 목록 보기");
        System.out.println("3. 상세하게 보기");
        System.out.println("4. 수정하기");
        System.out.println("5. 삭제하기");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호를 입력해주세요! (0~5) >> ");
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
        }catch(Exception e) {
          System.out.println("-----------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n",e.getClass().getName(), e.getMessage());
          System.out.println("-----------------------------------------------------------------------------");
        }
        System.out.println();
      }
    saveMembers();
  }

  String inputMemberId(){
    while(true) {
      String id = Prompt.inputString("회원 아이디(enter(취소)): ");
      if(id.equals("")) {
        return null;
      }
      if(findById(id) != null) {
        return id;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  String inputMember(String promptTitle) {
    while(true) {
      String id = Prompt.inputString(promptTitle);
      if(id.length() == 0) {
        return null;
      }else if(findById(id) != null) {
        return id;
      }
      System.out.println("잘못된 아이디 입니다.");
    }
  }

  protected Member findByNo(int memberNo) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getNumber() == memberNo) {
        return m;
      }
    }
    return null;
  }

  private Member findById(String id) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

  static void loadMembers() {

    try(Scanner in = new Scanner(new FileReader("members.data"))) {

      while(true) {
        try {
          String record = in.nextLine();
          String[] fields = record.split(",");
          Member m = new Member();
          m.setNumber(Integer.parseInt(fields[0]));
          m.setName(fields[1]);
          m.setId(fields[2]);
          m.setPassword(fields[3]);
          m.setTel(fields[4]);
          m.setAddress(fields[5]);
          m.setEmail(fields[6]);
          m.setJoinDate(Date.valueOf(fields[7]));

          memberList.add(m);
        } catch(Exception e){
          break;
        }
      }
      System.out.println("멤버 데이터 로딩 중입니다..");

    } catch (Exception e) {

      System.out.println("멤버 데이터 로딩 중 오류 발생!");

    }

  }

  static void saveMembers() {

    try(FileWriter out = new FileWriter("members.data")) {

      for (Member m : memberList) {
        out.write(String.format("%d,%s,%s,%s,%s%s,%s,%s",
            m.getNumber(),
            m.getName(),
            m.getId(),
            m.getPassword(),
            m.getTel(),
            m.getAddress(),
            m.getEmail(),
            m.getJoinDate()));

      }
      System.out.println("회원 데이터를 저장했습니다!");

    } catch(Exception e) {

      System.out.println("회원 데이터 파일로 저장 중 오류 발생!");
    }
  }
}