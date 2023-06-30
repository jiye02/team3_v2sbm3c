package dev.mvc.basket;

/*
basketno                        NUMBER(10) NOT NULL PRIMARY KEY,
galleryno                 NUMBER(10) NULL ,
memberno                 NUMBER(10) NOT NULL,
cnt                            NUMBER(10) DEFAULT 0 NOT NULL,
rdate                          DATE NOT NULL,
  
SELECT t.basketno, c.galleryno, c.title, c.thumb1, c.price, c.dc, c.saleprice, c.point, t.memberno, t.cnt, t.rdate 
FROM gallery c, basket t
WHERE c.galleryno = t.galleryno
ORDER BY basketno ASC;
 */
public class BasketVO {
    /** 장바구니 번호 */
    private int basketno;
    /** 컨텐츠 번호 */
    private int galleryno;
    /** 제목 */
    private String title = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 정가 */
    private int price;
    /** 할인률 */
    private int dc;
    /** 판매가 */
    private int saleprice;
    /** 포인트 */
    private int point;
    /** 회원 번호 */
    private int memberno;
    /** 수량 */
    private int cnt;
    /** 금액 = 판매가 x 수량 */
    private int tot;
    /** 등록일 */
    private String rdate;
    /** 예약일 */
    private String labeldate;
    /** 전시 시작일 */
    private String min;
    /** 전시 종료일 */
    private String max;
    
    public int getBasketno() {
        return basketno;
    }

    public void setBasketno(int basketno) {
        this.basketno = basketno;
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

    public String getThumb1() {
        return thumb1;
    }

    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDc() {
        return dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }

    public int getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(int saleprice) {
        this.saleprice = saleprice;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getMemberno() {
        return memberno;
    }

    public void setMemberno(int memberno) {
        this.memberno = memberno;
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

    public String getMin() {
      return min;
    }

    public void setMin(String min) {
      this.min = min;
    }

    public String getMax() {
      return max;
    }

    public void setMax(String max) {
      this.max = max;
    }
    
    

}