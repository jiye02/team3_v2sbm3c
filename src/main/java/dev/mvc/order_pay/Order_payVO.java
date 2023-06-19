package dev.mvc.order_pay;

public class Order_payVO {
  /** 예약 번호 **/
  private int order_payno;
  /** 회원 번호 **/
  private int memberno;
  /** 회원 성명 **/
  private String rname;
  /** 회원 전화번호 **/
  private String rtel;
  /** 회원 우편번호 **/
  private String rzipcode;
  /** 회원 주소1 **/
  private String raddress1;
  /** 회원 주소2 **/
  private String raddress2;
  /** 결제 방식 **/
  private int paytype;
  /** 결제 금액 **/
  private int amount;
  /** 예약 날짜 **/
  private String rdate;

  public int getOrder_payno() {
    return order_payno;
  }

  public void setOrder_payno(int order_payno) {
    this.order_payno = order_payno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getRname() {
    return rname;
  }

  public void setRname(String rname) {
    this.rname = rname;
  }

  public String getRtel() {
    return rtel;
  }

  public void setRtel(String rtel) {
    this.rtel = rtel;
  }

  public String getRzipcode() {
    return rzipcode;
  }

  public void setRzipcode(String rzipcode) {
    this.rzipcode = rzipcode;
  }

  public String getRaddress1() {
    return raddress1;
  }

  public void setRaddress1(String raddress1) {
    this.raddress1 = raddress1;
  }

  public String getRaddress2() {
    return raddress2;
  }

  public void setRaddress2(String raddress2) {
    this.raddress2 = raddress2;
  }

  public int getPaytype() {
    return paytype;
  }

  public void setPaytype(int paytype) {
    this.paytype = paytype;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

}
