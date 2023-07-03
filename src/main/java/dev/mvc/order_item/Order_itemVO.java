
package dev.mvc.order_item;

public class Order_itemVO {


  /*
COMMENT ON COLUMN order_item.order_payno is '주문 번호';
COMMENT ON COLUMN order_item.CONTENTSNO is '컨텐츠 번호';
COMMENT ON COLUMN order_item.cnt is '수량';
COMMENT ON COLUMN order_item.tot is '합계';
COMMENT ON COLUMN order_item.stateno is '주문상태';
COMMENT ON COLUMN order_item.rdate is '주문날짜';
 * 
 * */
  /** 주문상세번호 */
  private int order_itemno=0;  // 초기값: 0
  
  /** 회원 번호 */
  private int memberno = 0;
  
  /** 주문 번호 */
  private int order_payno = 0;
  
  /** 컨텐츠 번호 */
  private int galleryno = 0;
  
  /** 제목, gallery join */
  private String title = "";
  
  /** 판매가, gallery join */
  private int saleprice;
  
  /** 수량 */
  private int cnt = 0;
  
  private int tot;
  
  /** 배송 상태(stateno):  1: 결재 완료, 2: 상품 준비중, 3: 배송 시작, 4: 배달중, 5: 오늘 도착, 6: 배달 완료   */
  private int stateno;
  
  /** 주문날짜 */
  private String rdate = ""; // 초기값: null
  
  private String labeldate = "";

  public int getOrder_itemno() {
    return order_itemno;
  }

  public void setOrder_itemno(int order_itemno) {
    this.order_itemno = order_itemno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getOrder_payno() {
    return order_payno;
  }

  public void setOrder_payno(int order_payno) {
    this.order_payno = order_payno;
  }

  public int getGalleryno() {
    return galleryno;
  }

  public void setGalleryno(int galleryno) {
    this.galleryno = galleryno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSaleprice() {
    return saleprice;
  }

  public void setSaleprice(int saleprice) {
    this.saleprice = saleprice;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public int getTot() {
    return tot;
  }

  public void setTot(int tot) {
    this.tot = tot;
  }

  public int getStateno() {
    return stateno;
  }

  public void setStateno(int stateno) {
    this.stateno = stateno;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  public String getLabeldate() {
    return labeldate;
  }

  public void setLabeldate(String labeldate) {
    this.labeldate = labeldate;
  }
}