
/**********************************/
/* Table Name: 갤러리 */
/**********************************/
CREATE TABLE GALLERY(
    GALLERYNO NUMERIC(10) NOT NULL PRIMARY KEY,
    ADMINNO NUMBER(10) NOT NULL,
    EXHINO NUMBER(10) NOT NULL,
    TITLE VARCHAR2(50) NOT NULL,
    GALLERY CLOB(4000) NOT NULL,
    RECOM NUMBER(7) NOT NULL,
    CNT NUMBER(7) NOT NULL,
    REPLYCNT NUMBER(7) NOT NULL,
    PASSWD VARCHAR2(15) NOT NULL,
    WORD VARCHAR2(100),
    RDATE DATE NOT NULL,
    FILE1 VARCHAR2(100),
    FILE1SAVED VARCHAR2(100),
    THUMB1 VARCHAR2(100),
    SIZE1 NUMBER(10),
    PRICE NUMBER(10),
    SALEPRICE NUMBER(10),
    SALECNT NUMBER(10),
    MAP VARCHAR2(1000),
    YOUTUBE VARCHAR2(1000),
    adminno NUMBER(10),
    NAME NUMERIC(30),
    UDATE DATE,
    SEQNO DATE,
    visible CHAR(1),
    memberno NUMBER(10),
  FOREIGN KEY (adminno) REFERENCES admin (adminno),
  FOREIGN KEY (EXHINO) REFERENCES EXHI (EXHINO),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE GALLERY is '컨텐츠 - 순례길';
COMMENT ON COLUMN GALLERY.GALLERYno is '컨텐츠 번호';
COMMENT ON COLUMN GALLERY.adminno is '관리자 번호';
COMMENT ON COLUMN GALLERY.EXHIno is '카테고리 번호';
COMMENT ON COLUMN GALLERY.title is '제목';
COMMENT ON COLUMN GALLERY.content is '내용';
COMMENT ON COLUMN GALLERY.recom is '추천수';
COMMENT ON COLUMN GALLERY.cnt is '조회수';
COMMENT ON COLUMN GALLERY.replycnt is '댓글수';
COMMENT ON COLUMN GALLERY.passwd is '패스워드';
COMMENT ON COLUMN GALLERY.word is '검색어';
COMMENT ON COLUMN GALLERY.rdate is '등록일';
COMMENT ON COLUMN GALLERY.file1 is '메인 이미지';
COMMENT ON COLUMN GALLERY.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN GALLERY.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN GALLERY.size1 is '메인 이미지 크기';
COMMENT ON COLUMN GALLERY.price is '정가';
COMMENT ON COLUMN GALLERY.dc is '할인률';
COMMENT ON COLUMN GALLERY.saleprice is '판매가';
COMMENT ON COLUMN GALLERY.point is '포인트';
COMMENT ON COLUMN GALLERY.salecnt is '수량';
COMMENT ON COLUMN GALLERY.map is '지도';
COMMENT ON COLUMN GALLERY.youtube is 'Youtube 영상';



/**********************************/
/* Table Name: 찜 */
/**********************************/
CREATE TABLE jjim(
    jjimnum NUMERIC(20) NOT NULL,
    memberno NUMERIC(10) NOT NULL,
    gallerysno NUMERIC(10) NOT NULL,
    GALLERYNO NUMBER(10),
    basketno NUMERIC(10),
  PRIMARY KEY (jjimnum, memberno, gallerysno),
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (GALLERYNO) REFERENCES GALLERY (GALLERYNO)
);

COMMENT ON TABLE jjim is '찜 - 순위';
COMMENT ON COLUMN GALLERY.jjimno is '찜 번호';
COMMENT ON COLUMN GALLERY.memberno is '회원 번호';
COMMENT ON COLUMN GALLERY.gallerysno is '갤러리 번호';
COMMENT ON COLUMN GALLERY.GALLERYNO is '제목';
COMMENT ON COLUMN GALLERY.basketno is '장바구니 번호';









