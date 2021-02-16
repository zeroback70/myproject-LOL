package com.kyung.util;

import com.kyung.pms.domain.Board;
import com.kyung.pms.domain.Deliver;
import com.kyung.pms.domain.Member;
import com.kyung.pms.domain.Order;
import com.kyung.pms.domain.Useditem;

public class List<E> {
  private Node<E> first;
  private Node<E> last;
  protected int size = 0;

  public void add(E command) {
    Node<E> node = new Node<>(command);

    if(first == null) {
      first = node;
      last = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size++;

}

public Object[] toArray() {

  Object[] arr = new Object[size];
    Node<E> cursor = this.first;
    int i = 0;
    while(cursor != null) {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }

    return arr;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {

    if(index < 0 || index >= size) {
      return null;
    }

    Node<E> cursor = first;
    int count = 0;
    while(cursor != null) {
      if(index == count++) {
        return (E) cursor.obj;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public boolean delete (Deliver deliver) {

    Node<E> cursor = first;
    while(cursor != null) {
      if(cursor.obj.equals(deliver)) {
        this.size--;
        if(first == last) { 
          first = null;
          last = null;
        }else if(cursor == first){ 
          first = cursor.next;
          cursor.prev = null;
        }else if(cursor == last) { 
          cursor.prev.next = null;
          last = cursor.prev;
        }else{
          cursor.prev.next = cursor.next;
          if(cursor.next !=null) {
            cursor.next.prev = cursor.prev;
          }
        }
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public E delete(int index) {

    if(index < 0 || index >= size) {
      return null;
    }

    E deleted = null;
    Node<E> cursor = first;
    int count = 0;
    while(cursor != null) {
      if(index == count++) {
        deleted = (E) cursor.obj;
        this.size--;
        if(first == last) { 
          first = null;
          last = null;
        }else if(cursor == first){
          first = cursor.next;
          cursor.prev = null;
        }else if(cursor == last) {
          cursor.prev.next = null;
          last = cursor.prev;
        }else{
          cursor.prev.next = cursor.next;
          if(cursor.next !=null) {
            cursor.next.prev = cursor.prev;
          }
        }
        break;
      }
      cursor = cursor.next;
    }
    return deleted;
  }

  public int indexOf(E obj) {
    Object[] list = this.toArray();
    for(int i = 0; i < list.length; i++) {
      if(list[i].equals(obj)) {
        return i;
      }
    }
    return -1;
  }

  public int size() {
    return this.size;
  }
 
  // 중요한 중첩 클래스 Node<T>
  static class Node<T>{
    Object obj;
    Node<T> next;
    Node<T> prev;

    Node(T obj){
      this.obj = obj;
    }
  }

public void add(Board b) {
}

public void delete(Board board) {
}

public void add(Member m) {
}

public void delete(Member member) {
}

public void add(Useditem p) {
}

public void delete(Useditem useditem) {
}

public void add(Order o) {
}

public void add(Deliver s) {
}
}
