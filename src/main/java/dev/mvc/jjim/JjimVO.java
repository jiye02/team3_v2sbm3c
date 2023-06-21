package dev.mvc.jjim;

/*
basketno                        NUMBER(10) NOT NULL PRIMARY KEY,
galleryno                 NUMBER(10) NULL ,
memberno                 NUMBER(10) NOT NULL,
quantity                            NUMBER(10) DEFAULT 0 NOT NULL,
rdate                          DATE NOT NULL,
  
SELECT t.basketno, c.galleryno, c.title, c.thumb1, c.price, c.dc, c.saleprice, c.point, t.memberno, t.quantity, t.rdate 
FROM gallery c, basket t
WHERE c.galleryno = t.galleryno
ORDER BY basketno ASC;
 */
public class JjimVO {
    /** 쇼핑 카트 번호 */
    private int jjimno;
    /** 컨텐츠 번호 */
    private int galleryno;
    /** 제목 */
    private String title = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 회원 번호 */
    private int memberno;
    /** 수량 */
    private int quantity;
    /** 등록일 */
    private String rdate;

    public int getjjimno() {
        return jjimno;
    }

    public void setJjimno(int jjimno) {
        this.jjimno = jjimno;
    }

    public int getContentsno() {
        return galleryno;
    }

    public void setContentsno(int galleryno) {
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


    public int getMemberno() {
        return memberno;
    }

    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }

    public int getCnt() {
        return quantity;
    }

    public void setCnt(int quantity) {
        this.quantity = quantity;
    }


    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

}