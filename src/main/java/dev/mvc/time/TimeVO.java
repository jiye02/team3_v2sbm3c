package dev.mvc.time;

public class TimeVO {
  /** 글 번호 */
  private int timeno;
  /** 레이블 출력 날짜 */
  private String labeldate;
  /** 글 등록일 */
  private String rdate;
  /** 패스워드 */
  private String passwd; 
  /** 회원 번호 */
  private int memberno;
  /** 갤러리 번호 */
  private int galleryno;
  
  
  public int getTimeno() {
    return timeno;
  }
  public void setTimeno(int timeno) {
    this.timeno = timeno;
  }
  public String getLabeldate() {
    return labeldate;
  }
  public void setLabeldate(String labeldate) {
    this.labeldate = labeldate;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getGalleryno() {
    return galleryno;
  }
  public void setGalleryno(int galleryno) {
    this.galleryno = galleryno;
  }
  
  
  
}
