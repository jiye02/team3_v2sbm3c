package dev.mvc.jjim;

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
public class JjimVO {
    /** 찜 번호 */
    private int jjimnum;
    /** 컨텐츠 번호 */
    private int galleryno;
    /** 쇼핑 카트 번호 */
    private int basketno;
    /** 제목 */
    private String title = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 회원 번호 */
    private int memberno;
<<<<<<< HEAD
=======
    /** 수량 */
    private int cnt;
>>>>>>> 120d172d1e1cbea0ac3fe0d85effbeb71e27e5de
    /** 등록일 */
    private String rdate;

    
    public int getJjimnum() {
      return jjimnum;
    }

    public void setJjimnum(int jjimnum) {
      this.jjimnum = jjimnum;
    }

    public int getGalleryno() {
      return galleryno;
    }

    public void setGalleryno(int galleryno) {
      this.galleryno = galleryno;
    }

    public int getBasketno() {
      return basketno;
    }

    public void setBasketno(int basketno) {
      this.basketno = basketno;
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

    public int getMemberno() {
      return memberno;
    }

    public void setMemberno(int memberno) {
<<<<<<< HEAD
      this.memberno = memberno;
=======
        this.memberno = memberno;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
>>>>>>> 120d172d1e1cbea0ac3fe0d85effbeb71e27e5de
    }


    public String getRdate() {
      return rdate;
    }

    public void setRdate(String rdate) {
      this.rdate = rdate;
    }
    
    
    
    



}