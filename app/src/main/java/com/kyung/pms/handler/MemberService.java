package com.kyung.pms.handler;
// 2021-03-31 Update
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import com.kyung.pms.domain.Member;

public class MemberService {

  static LinkedList<Member> memberList = new LinkedList<>();
  public LinkedList<Member> getMemberList() {
    return memberList;
  }

  MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);
  public MemberValidatorHandler getMemberValidatorHandler() {
    return memberValidatorHandler;
  }
  public MemberService() {
    loadMembers();
  }

  public void menu() {

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
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정하기");
        System.out.println("5. 삭제하기");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호를 입력해주세요! (0~5) >>  ");
        System.out.println();
        try {
          switch(command) {
            case "0" :
              System.out.println("메인으로 돌아갑니다..");
              System.out.println();
              break loop;
            default :
              Command commandHandler = commandMap.get(command);

              if(commandHandler == null) {
                System.out.println("실행할 수 없는 메뉴 번호 입니다!");
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

  static void loadMembers() {

    try(BufferedReader in = new BufferedReader(new FileReader("members.csv"))) {

      while(true) {
        try {
          String record = in.readLine();
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
      System.out.println("멤버 데이터 로딩중 ..");
    } catch (Exception e) {
      System.out.println("멤버 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveMembers() {

    try(BufferedWriter out = new BufferedWriter(new FileWriter("members.csv"))) {

      for (Member m : memberList) {
        out.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s\n",
            m.getNumber(),
            m.getName(),
            m.getId(),
            m.getPassword(),
            m.getTel(),
            m.getAddress(),
            m.getEmail(),
            m.getJoinDate()));
      }
      System.out.println("회원 데이터 저장중..");

    } catch(Exception e) {
      System.out.println("회원 데이터 파일로 저장 중 오류 발생!");
    }
  }
}