package com.kyung.util;

public class Stack extends List{

    public Object push(Object item) {
      this.add(item);
      return item;
    }
  
    public Object pop() {
      Object obj = this.delete(this.size - 1);
      return obj;
    }
  }