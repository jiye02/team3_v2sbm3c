package dev.mvc.jjim;

/*
jjimno                        NUMBER(10) NOT NULL PRIMARY KEY,
galleryno                 NUMBER(10) NULL ,
memberno                 NUMBER(10) NOT NULL,
cnt                            NUMBER(10) DEFAULT 0 NOT NULL,
rdate                          DATE NOT NULL,
  
SELECT t.jjimno, c.galleryno, c.title, c.thumb1, c.price, c.dc, c.saleprice, c.point, t.memberno, t.cnt, t.rdate 
FROM gallery c, jjim t
WHERE c.galleryno = t.galleryno
ORDER BY jjimno ASC;
 */
public class JjimVO {
    /** 장바구니 번호 */
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
    private int tot;
    /** 삭제 */
    private int delete;
    /** 확인 */
    private int jjim_check;
    /** 확인 */
    private int count;
    /** 등록일 */
    private String rdate;
    /** 생성 */
    private int create;

    public int getJjimno() {
        return jjimno;
    }

    public void setJjimno(int jjimno) {
        this.jjimno = jjimno;
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

    public int getMemberno() {
        return memberno;
    }

    public void setMemberno(int memberno) {
        this.memberno = memberno;
    }

    public int getTot() {
        return tot;
    }

    public void setTot(int tot) {
        this.tot = tot;
    }
    
    
    public int getJjim_check() {
      return jjim_check;
    }

    public void setJjim_check(int jjim_check) {
      this.jjim_check = jjim_check;
    }
    

    public int getDelete() {
      return delete;
    }

    public void setDelete(int delete) {
      this.delete = delete;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }


    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public int getCreate() {
      return create;
    }

    public void setCreate(int create) {
      this.create = create;
    }
    

}