package com.kyung.util;

public class Stack<E> extends List<E>{

    public E push(E command) {
      this.add(command);
      return command;
    }
  
    public E pop() {
      E obj = this.delete(this.size - 1);
      return obj;
    }

	public void push(String command) {
	}
  }