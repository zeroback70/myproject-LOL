package com.kyung.pms.handler;

import java.sql.Date;
import com.kyung.pms.domain.Member;
import com.kyung.util.Prompt;

public class MemberHandler {
  static final int LENGTH = 100;

  Member[] members = new Member[LENGTH];
  int size = 0;

  public void add(){
    System.out.println("『회원 가입』");

    Member m = new Member();

    m.number = Prompt.promptInt("회원 번호: ");
    m.name = Prompt.promptString("이름: ");
    m.id = Prompt.promptString("아이디: ");
    m.password = Prompt.promptString("비밀번호: ");
    m.tel = Prompt.promptString("전화번호: ");
    m.address = Prompt.promptString("주소: ");
    m.email = Prompt.promptString("메일: ");
    m.joinDate = new Date(System.currentTimeMillis());

    this.members[this.size++] = m;

    System.out.println();

  }

  public void list() {
    System.out.println("『회원 목록』");

    for(int i = 0; i < this.size; i++) {
      Member m = this.members[i];

      System.out.printf("회원 번호: %d 회원 아이디: %s 이름: %s\n전화번호: %s 주소: %s 메일: %s\n가입 날짜: %s\n"
          ,m.number, m.id,m.name, m.tel, m.address, m.email, m.joinDate);
      System.out.println("--------------------------------------------------");

    }

    System.out.println();
  }

  boolean exist(String name){
    for(int i = 0; i < this.size; i++) {
      if(name.equals(this.members[i].name)) {
        return true;
      }
    }
    return false;
  }

  public void detail() {
    System.out.println("『회원 상세정보』");

    Member member = findByNo(Prompt.promptInt("번호? "));

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      System.out.printf("이름: %s\n", member.name);
      System.out.printf("아이디: %s\n", member.id);
      System.out.printf("전화번호: %s\n", member.tel);
      System.out.printf("주소: %s\n", member.address);
      System.out.printf("메일: %s\n", member.email);
      System.out.printf("가입 날짜: %s\n", member.joinDate);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("『회원정보 수정하기』");

    Member member = findByNo(Prompt.promptInt("번호? "));
    if(member == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.promptString(String.format("이름(%s)? ",member.name));
      String id = Prompt.promptString(String.format("아이디(%s)? ",member.id));
      String tel = Prompt.promptString(String.format("전화번호(%s)? ",member.tel));
      String address = Prompt.promptString(String.format("주소(%s)? ",member.address));
      String email = Prompt.promptString(String.format("메일(%s)? ",member.email));


      String userChoice = Prompt.promptString("정말 수정하시겠습니까?( 비밀번호 입력) ");
      if(userChoice.equals(member.password)) {
        member.name = name;
        member.id = id;
        member.tel = tel;
        member.address = address;
        member.email = email;
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
    System.out.println("『회원 탈퇴하기』");

    int index = indexOf(Prompt.promptInt("번호를 입력해주세요: "));
    if(index == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(네/아니오) ");

      if(userChoice.equalsIgnoreCase("네")) {

        for(int x = index + 1; x < this.size; x++) {

          members[x - 1] = members[x];
        }
        this.members[--this.size] = null;
        System.out.println("회원 정보 삭제가 완료되었습니다! 그동안 이용해주셔서 감사합니다.");
        System.out.println();
        return;
      }else {

        System.out.println("회원 탈퇴 절차를 취소합니다..");
        System.out.println();
        return;
      }
    }
  }


  int indexOf(int memberNo) {
    for(int i = 0; i < this.size; i++) {
      Member member = this.members[i];
      if(memberNo == member.number) {
        return i;
      }
    }
    return -1;
  }

  Member findByNo(int memberNo) {
    int i = indexOf(memberNo);
    if(i == -1) {
      return null;
    }else {
      return this.members[i];
    }
  }
}