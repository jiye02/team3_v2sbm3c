package dev.mvc.gallery;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

/*
        galleryno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        adminno                              NUMBER(10)     NOT NULL ,
        exhino                                NUMBER(10)         NOT NULL ,
        title                                 VARCHAR2(300)         NOT NULL,
        content                               CLOB                  NOT NULL,
        recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
        cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
        replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
        passwd                                VARCHAR2(15)         NOT NULL,
        word                                  VARCHAR2(300)         NULL ,
        rdate                                 DATE               NOT NULL,
        file1                                   VARCHAR(100)          NULL,
        file1saved                            VARCHAR(100)          NULL,
        thumb1                              VARCHAR(100)          NULL,
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  
        price                                 NUMBER(10)      DEFAULT 0 NULL,  
        dc                                    NUMBER(10)      DEFAULT 0 NULL,  
        saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
        point                                 NUMBER(10)      DEFAULT 0 NULL,  
        salecnt                               NUMBER(10)      DEFAULT 0 NULL,  
        map                                  VARCHAR2(1000)            NULL,
        youtube                             VARCHAR2(1000)            NULL,        
 */

public class GalleryVO {

  /** 컨텐츠 번호 */
  private int galleryno;
  /** 관리자 번호 */
  private int adminno;
  /** 카테고리 번호 */
  private int exhino;
  /** 제목 */
  private String title = "";
  /** 내용 */
  private String content = "";
  /** 추천수 */
  private int jjim = 0;
  /** 조회수 */
  private int cnt = 0;
  /** 댓글수 */
  private int replycnt = 0;
  /** 패스워드 */
  private String passwd = "";
  /** 검색어 */
  private String word = "";
  /** 등록 날짜 */
  private String rdate = "";
  
  /** 전시 시작일 */
  private String min = "";
  /** 전시 종료일 */
  private String max = "";
 
  /** 메인 이미지 */
  private String file1 = "";
  /** 실제 저장된 메인 이미지 */
  private String file1saved = "";
  /** 메인 이미지 preview */
  private String thumb1 = "";
  /** 메인 이미지 크기 */
  private long size1;

  /** 정가 */
  private int price;
  /** 할인률 */
  private int dc;
  /** 판매가 */
  private int saleprice;
  /** 포인트 */
  private int point;
  /** 재고 수량 */
  private int salecnt;

  /** 지도 */
  private String map;

  /** Youtube */
  private String youtube;

  /**
   * 이미지 파일
   * <input type='file' class="form-control" name='file1MF' id='file1MF' value=''
   * placeholder="파일 선택">
   */
  private MultipartFile file1MF;

  /** 메인 이미지 크기 단위, 파일 크기 */
  private String size1_label = "";

  /** 시작 rownum */
  private int start_num;

  /** 종료 rownum */
  private int end_num;

  /** 현재 페이지 */
  private int now_page = 1; 
  public int getGrpno() {
    return grpno;
  }

  public void setGrpno(int grpno) {
    this.grpno = grpno;
  }

  public int getIndent() {
    return indent;
  }

  public void setIndent(int indent) {
    this.indent = indent;
  }

  public int getAnsnum() {
    return ansnum;
  }

  public void setAnsnum(int ansnum) {
    this.ansnum = ansnum;
  }

  // 답변 관련 시작
  // -----------------------------------------------------
  /** 그룹번호 */
  private int grpno;
  /** 답변 차수 */
  private int indent;
  /** 답변 출력 순서 */
  private int ansnum;
  // -----------------------------------------------------  
  // 답변 관련 종료

  public int getGalleryno() {
    return galleryno;
  }

  public void setGalleryno(int galleryno) {
    this.galleryno = galleryno;
  }

  public int getAdminno() {
    return adminno;
  }

  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }

  public int getExhino() {
    return exhino;
  }

  public void setExhino(int exhino) {
    this.exhino = exhino;
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

  public int getJjim() {
    return jjim;
  }

  public void setJjim(int jjim) {
    this.jjim = jjim;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }


  public int getReplycnt() {
    return replycnt;
  }

  public void setReplycnt(int replycnt) {
    this.replycnt = replycnt;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public String getFile1() {
    return file1;
  }

  public void setFile1(String file1) {
    this.file1 = file1;
  }

  public String getFile1saved() {
    return file1saved;
  }

  public void setFile1saved(String file1saved) {
    this.file1saved = file1saved;
  }

  public String getThumb1() {
    return thumb1;
  }

  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }

  public long getSize1() {
    return size1;
  }

  public void setSize1(long size1) {
    this.size1 = size1;
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

  public int getSalecnt() {
    return salecnt;
  }

  public void setSalecnt(int salecnt) {
    this.salecnt = salecnt;
  }

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public String getYoutube() {
    return youtube;
  }

  public void setYoutube(String youtube) {
    this.youtube = youtube;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  public String getSize1_label() {
    return size1_label;
  }

  public void setSize1_label(String size1_label) {
    this.size1_label = size1_label;
  }

  public int getStart_num() {
    return start_num;
  }

  public void setStart_num(int start_num) {
    this.start_num = start_num;
  }

  public int getEnd_num() {
    return end_num;
  }

  public void setEnd_num(int end_num) {
    this.end_num = end_num;
  }

  public int getNow_page() {
    return now_page;
  }

  public void setNow_page(int now_page) {
    this.now_page = now_page;
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