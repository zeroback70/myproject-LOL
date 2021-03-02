package com.kyung.pms.handler;

import java.io.ObjectInputStream;
import java.util.List;
// 미완성
public class FileHandler {

  List<?> dataList;

  public <T> FileHandler(List<T> dataList) {
    this.dataList = dataList;
  }

  public void loadObject() {
    try(ObjectInputStream in = new ObjectInputStream()){

    } catch ()
  }

  public void saveObject() {

  }
}