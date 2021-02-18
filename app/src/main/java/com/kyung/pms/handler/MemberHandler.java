package com.kyung.pms.handler;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberHandler<E> {

  private LinkedList<Member> memberList = new LinkedList<>();

  public void service(String number1) throws CloneNotSupportedException {

    loop: 
      while(true) {
        System.out.println("【토끼마켓 / 회원정보】");
        System.out.println("1. 회원가입");
        System.out.println("2. 회원목록");
        System.out.println("3. 회원 상세 보기");
        System.out.println("4. 회원 수정하기");
        System.out.println("5. 회원 탈퇴하기");
        System.out.println("0. 이전 메뉴로 돌아가기");
        System.out.println("원하시는 번호를 눌러주세요!");
        System.out.println();

        String command = com.kyung.util.Prompt.inputString("번호 입력>> ");
        System.out.println();
        try {
        switch(command) {
          case "1" :
            this.add();
            break;
          case "2" :
            this.list();
            break;
          case "3" :
            this.detail();
            break;
          case "4" :
            this.update();
            break;
          case "5" :
            this.delete();
            break;
          case "0" :
            System.out.println("이전 메뉴로 돌아갑니다.");
            System.out.println();
            break loop;
          default :
            System.out.println("번호를 잘못 입력하신것 같습니다. 0번부터 5번까지 다시 입력해주시겠어요?");
            System.out.println();
        }
      }catch(Exception e) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------------------------------------------");
      }
      System.out.println();
    }
  }

  public void add(){
    System.out.println("【토끼마켓 / 회원정보 / 회원가입]");
    Member m = new Member();

    m.setNumber(Prompt.inputInt("회원 번호: "));
    m.setName(Prompt.inputString("이름: "));
    m.setId(Prompt.inputString("아이디: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setTel(Prompt.inputString("전화번호: "));
    m.setAddress(Prompt.inputString("주소: "));
    m.setEmail(Prompt.inputString("메일: "));
    m.setJoinDate(new Date(System.currentTimeMillis()));

    memberList.add(m);
    System.out.println();
    System.out.println("회원 등록이 완료되었습니다.");
    System.out.println();

  }

  public void list() throws CloneNotSupportedException{
    System.out.println("【토끼마켓 / 회원정보 / 회원목록】");

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();

      System.out.printf("번호: %d 아이디: %s 이름: %s\n가입 날짜: %s\n"
          ,m.getNumber(), m.getId(), m.getName(), m.getJoinDate());
      System.out.println("--------------------------------------------------");

    }

    System.out.println();
  }

  public void detail() {
    System.out.println("【토끼마켓 / 회원정보 / 회원 상세 보기】");

    Member member = findByNo(Prompt.inputInt("번호? "));

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("아이디: %s\n", member.getId());
      System.out.printf("전화번호: %s\n", member.getTel());
      System.out.printf("주소: %s\n", member.getAddress());
      System.out.printf("메일: %s\n", member.getEmail());
      System.out.printf("가입 날짜: %s\n", member.getJoinDate());
      System.out.println("-------------------------------------------------------------");
      return;
    }
  }

  public void update() {
    System.out.println("【토끼마켓 / 회원정보 / 회원 수정하기】");

    Member member = findByNo(Prompt.inputInt("번호? "));
    if(member == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {
      String name = Prompt.inputString(String.format("이름(%s)? ",member.getName()));
      String id = Prompt.inputString(String.format("아이디(%s)? ",member.getId()));
      String tel = Prompt.inputString(String.format("전화번호(%s)? ",member.getTel()));
      String address = Prompt.inputString(String.format("주소(%s)? ",member.getAddress()));
      String email = Prompt.inputString(String.format("메일(%s)? ",member.getEmail()));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y: 비밀번호 입력) ");
      if(userChoice.equals(member.getPassword())) {
        member.setName(name);
        member.setId(id);
        member.setTel(tel);
        member.setAddress(address);
        member.setEmail(email);
        System.out.println("회원 정보 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("잘못된 비밀번호입니다. 회원 정보 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }


  public void delete() {
    System.out.println("【토끼마켓 / 회원정보 / 회원 삭제하기】");

    int no = Prompt.inputInt("번호? ");
    Member member = findByNo(no);
    if(member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(Y/N) ");

      if(userChoice.equalsIgnoreCase("Y")) {

        memberList.remove(member);

        System.out.println("회원 정보 삭제를 완료하였습니다.");
        System.out.println();
      }else {

        System.out.println("회원 정보 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
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

  private Member findByNo(int memberNo) {
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

}