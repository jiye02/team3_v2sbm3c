package dev.mvc.recommend;
//CREATE TABLE RECOMMEND(
//RECOMMENDNO                           NUMBER(8)         NOT NULL         PRIMARY KEY,
//MEMBERNO                              NUMBER(10)         NULL ,
//EXHINO                                NUMBER(10)         NULL ,
//SEQ                                   NUMBER(2)         DEFAULT 1         NOT NULL,
//RDATE                                 DATE         NOT NULL,
//FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
//FOREIGN KEY (EXHINO) REFERENCES EXHI (EXHINO)
//);
public class RecommendVO {
  public int recommendno;
  public int memberno;
  public int exhino;
  public int seq;
  public String rdate ="";
  private String thumb1 = "";
  private String title = "";
  
  public int getRecommendno() {
    return recommendno;
  }
  public void setRecommendno(int recommendno) {
    this.recommendno = recommendno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getExhino() {
    return exhino;
  }
  public void setExhino(int exhino) {
    this.exhino = exhino;
  }
  public int getSeq() {
    return seq;
  }
  public void setSeq(int seq) {
    this.seq = seq;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getThumb1() {
    return thumb1;
  }
  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  
}
  
