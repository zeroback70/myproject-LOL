package com.kyung.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  private String memberId;
  private int number;
  private String Useditems;
  private Date registeredDate;
  private String request;
  private int totalPrice;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
    result = prime * result + number;
    result = prime * result + ((Useditems == null) ? 0 : Useditems.hashCode());
    result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (memberId == null) {
      if (other.memberId != null)
        return false;
    } else if (!memberId.equals(other.memberId))
      return false;
    if (number != other.number)
      return false;
    if (Useditems == null) {
      if (other.Useditems != null)
        return false;
    } else if (!Useditems.equals(other.Useditems))
      return false;
    if (registeredDate == null) {
      if (other.registeredDate != null)
        return false;
    } else if (!registeredDate.equals(other.registeredDate))
      return false;
    return true;
  }
  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getUseditems() {
    return Useditems;
  }
  public void setUseditems(String Useditems) {
    this.Useditems = Useditems;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public String getRequest() {
    return request;
  }
  public void setRequest(String request) {
    this.request = request;
  }
  public int getTotalPrice() {
    return totalPrice;
  }
  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }
}