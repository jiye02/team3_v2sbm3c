package dev.mvc.calendar;

public class CalendarVO {
/*
CREATE TABLE calendar(
  calendarno  NUMBER(8)     NOT NULL   PRIMARY KEY,
  labeldate   VARCHAR(10)   NOT NULL,  -- 출력할 날짜 2013-10-20 
  title       VARCHAR(100)  NOT NULL,  -- 제목(*)
  content     CLOB          NOT NULL,  -- 글 내용
  rdate       DATE          NOT NULL,  -- 등록 날짜
  passwd     VARCHAR(60)    NOT NULL,  -- 패스워드, 영숫자 조합
  memberno    NUMBER(10)    NOT NULL,  -- 회원 번호, 레코드를 구분하는 컬럼
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);
 */

  /** 글 번호 */
  private int calendarno;
  /** 레이블 출력 날짜 */
  private String labeldate;
  /** 제목 */
  private String title;
  /** 내용 */
  private String content;
  /** 글 등록일 */
  private String rdate;
  /** 패스워드 */
  private String passwd; 
  /** 회원 번호 */
  private int memberno;
  
  
  public int getCalendarno() {
    return calendarno;
  }
  public void setCalendarno(int calendarno) {
    this.calendarno = calendarno;
  }
  public String getLabeldate() {
    return labeldate;
  }
  public void setLabeldate(String labeldate) {
    this.labeldate = labeldate;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
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

}



