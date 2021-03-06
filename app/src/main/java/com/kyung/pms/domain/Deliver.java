package com.kyung.pms.domain;

import java.io.Serializable;

public class Deliver implements Serializable {

  private static final long serialVersionUID = 1L;
  private int number;
  private String memberId;
  private int orderNumber;
  private int trackingNumber;
  private int status;
  private String manager;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
    result = prime * result + number;
    result = prime * result + orderNumber;
    result = prime * result + trackingNumber;
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
    Deliver other = (Deliver) obj;
    if (memberId == null) {
      if (other.memberId != null)
        return false;
    } else if (!memberId.equals(other.memberId))
      return false;
    if (number != other.number)
      return false;
    if (orderNumber != other.orderNumber)
      return false;
    if (trackingNumber != other.trackingNumber)
      return false;
    return true;
  }

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }
  public int getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }
  public int getTrackingNumber() {
    return trackingNumber;
  }
  public void setTrackingNumber(int trackingNumber) {
    this.trackingNumber = trackingNumber;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getManager() {
    return manager;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }
}