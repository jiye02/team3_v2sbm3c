package dev.mvc.order_pay;

public class Order_payVO {
/*
COMMENT ON COLUMN order_item.order_itemno is '예약상세번호';
COMMENT ON COLUMN order_item.MEMBERNO is '회원 번호';
COMMENT ON COLUMN order_item.order_payno is '예약 번호';
COMMENT ON COLUMN order_item.GALLERYNO is '컨텐츠 번호';
COMMENT ON COLUMN order_item.cnt is '수량';
COMMENT ON COLUMN order_item.tot is '합계';
COMMENT ON COLUMN order_item.stateno is '예약상태';
COMMENT ON COLUMN order_item.rdate is '예약날짜';
 * */
  
  /** 주문 번호 */
  private int order_payno;
  /** 회원 번호 */
  private int memberno;
  /** 수취인 성명 */
  private String rname = "";
  /** 수취인 전화 번호 */
  private String rtel = "";
  /** 수취인 우편 번호 */
  private String rzipcode = "";
  /** 수취인 주소 1 */
  private String raddress1 = "";
  /** 수취인 주소 2 */
  private String raddress2 = "";
  /** 결재 타입 1: 신용 카드, 2. 모바일 결재, 3. 계좌 이체*/
  private int paytype = 1;
  /** 결재 금액 */
  private int amount = 0;
  /** 주문일 */
  private String rdate = "";
  
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