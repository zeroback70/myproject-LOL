package com.kyung.pms.handler;

import java.sql.Date;
import com.kyung.pms.domain.Write;
import com.kyung.util.Prompt;

public class WriteHandler {

  static final int LENGTH = 100;
  Write[] Writes = new Write[LENGTH];
  int size = 0;

  public void add() {
    System.out.println("『새로 문의하기』");
    Write b = new Write();

    b.number = Prompt.promptInt("주문번호: ");
    b.title = Prompt.promptString("제목: ");
    b.content = Prompt.promptString("문의내용: ");
    b.writer = Prompt.promptString("회원 ID: ");
    b.registeredDate = new Date(System.currentTimeMillis());

    this.Writes[this.size++] = b;
    System.out.println("문의내용이 접수되었습니다..");
    System.out.println();
  }

  public void list() {
    System.out.println("『고객문의 내용 목록』");

    for(int i = 0; i < this.size; i++) {
      Write b = this.Writes[i];
      System.out.printf("번호: %d 제목: %s 작성자: %s 등록일: %s\n조회수: %d 좋아요: %d\n",
          b.number, b.title, b.writer, b.registeredDate, b.views, b.like);
      System.out.println("-------------------------------------------------------------");
    }
  }

  public void detail() {
    System.out.println("『고객문의 글 상세조회]");

    Write Write = findByNo(Prompt.promptInt("번호를 입력해주세요: "));

    if (Write == null) {
      System.out.println("해당 번호의 게시글이 없습니다! 다시 입력해 주시겠어요?");
      System.out.println();
    }else {
      Write.views++;
      System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
      System.out.printf("제목: %s ", Write.title);
      System.out.printf("회원ID: %s\n", Write.writer);
      System.out.printf("내용: %s\n", Write.content);
      System.out.printf("등록일: %s ", Write.registeredDate);
      System.out.printf("조회수: %d\n", Write.views);
      System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
      return;

    }
  }

  public void update() {
    System.out.println("『문의글 수정하기』");

    Write Write = findByNo(Prompt.promptInt("번호를 입력해주세요: "));
    if(Write == null) {

      System.out.println("해당 번호의 게시글이 없습니다! 다시 입력해 주시겠어요?");
      System.out.println();

    }else {

      String title = Prompt.promptString(String.format("제목(%s): ",Write.title));
      String content = Prompt.promptString(String.format("내용(%s): ",Write.content));

      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(네/아니오) ");
      if(userChoice.equalsIgnoreCase("네")) {
        Write.title = title;
        Write.content = content;
        Date reRegisteredDate = new Date(System.currentTimeMillis());
        Write.registeredDate = reRegisteredDate;
        System.out.println("수정이 완료되었습니다!");
        System.out.println();
      }else {
        System.out.println("수정을 취소합니다..");
        System.out.println();
        return;
      }
    }
  }


  public void delete() {
    System.out.println("『문의글 삭제』");

    int index = indexOf(Prompt.promptInt("번호를 입력해주세요: "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다. 다시 입력해주시겠어요?");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(네/아니오) ");

      if(userChoice.equalsIgnoreCase("네")) {

        for(int x = index + 1; x < this.size; x++) {

          Writes[x - 1] = Writes[x];
        }
        this.Writes[--this.size] = null;
        System.out.println("삭제가 완료되었습니다!");
        System.out.println();
        return;
      }else {
        System.out.println("삭제를 취소합니다..");
        System.out.println();
        return;
      }
    }
  }


  int indexOf(int WriteNo) {
    for(int i = 0; i < this.size; i++) {
      Write Write = this.Writes[i];
      if(WriteNo == Write.number) {
        return i;
      }
    }
    return -1;
  }

  Write findByNo(int WriteNo) {
    int i = indexOf(WriteNo);
    if(i == -1) {
      return null;
    }else {
      return this.Writes[i];
    }
  }
}