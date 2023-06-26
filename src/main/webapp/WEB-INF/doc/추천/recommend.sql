/**********************************/
/* Table Name: 추천 */
/**********************************/
. 추천 분류(회원 분류 정보): favorite table
/**********************************/
/* Table Name: 관심분야 */
/**********************************/

DROP TABLE favorite CASCADE CONSTRAINTS;
CREATE TABLE favorite(
        FAVORITENO                         NUMBER(10)         NOT NULL         PRIMARY KEY,
        SEQNO                                NUMBER(7)         DEFAULT 1         NULL ,
        RDATE                                 DATE         NOT NULL,
        EXHINO                               NUMBER(10)         NULL ,
        MEMBERNO                          NUMBER(10)         NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (EXHINO) REFERENCES EXHI (EXHINO)
);

COMMENT ON TABLE favorite is '관심분야';
COMMENT ON COLUMN favorite.FAVORITENO is '관심분야번호';
COMMENT ON COLUMN favorite.SEQNO is '출력 순서';
COMMENT ON COLUMN favorite.RDATE is '등록일';
COMMENT ON COLUMN favorite.EXHINO is '카테고리 번호';
COMMENT ON COLUMN favorite.MEMBERNO is '회원 번호';

DROP TABLE RECOMMEND;

CREATE TABLE RECOMMEND(
        RECOMMENDNO                           NUMBER(8)         NOT NULL         PRIMARY KEY,
        MEMBERNO                              NUMBER(10)         NULL ,
        exhiNO                                NUMBER(10)         NULL ,
        SEQ                                   NUMBER(2)         DEFAULT 1         NOT NULL,
        RDATE                                 DATE         NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (exhiNO) REFERENCES exhi (exhiNO)
);

COMMENT ON TABLE RECOMMEND is '추천';
COMMENT ON COLUMN RECOMMEND.RECOMMENDNO is '추천번호';
COMMENT ON COLUMN RECOMMEND.MEMBERNO is '회원번호';
COMMENT ON COLUMN RECOMMEND.exhiNO is '카테고리번호';
COMMENT ON COLUMN RECOMMEND.seq is '추천 우선순위';
COMMENT ON COLUMN RECOMMEND.RDATE is '추천 날짜';

DROP SEQUENCE RECOMMEND_SEQ;

CREATE SEQUENCE RECOMMEND_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

-- 존재하는 memberno, exhino 등록
INSERT INTO recommend(recommendno, memberno, exhino, seq, rdate)
VALUES(RECOMMEND_SEQ.nextval, 1, , 1, sysdate);

SELECT recommendno, memberno, exhino, seq, rdate 
FROM recommend 
ORDER BY recommendno ASC;
-- 1번회원은 1번 카테고리를 추천필요.
RECOMMENDNO   MEMBERNO     exhiNO        SEQ RDATE              
----------- ---------- ---------- ---------- -------------------
          6          1          1          1 2023-06-15 02:53:32

DELETE FROM recommend;
DELETE FROM recommend WHERE memberno=1;
COMMIT;

1. 기존에 추천을 했는지 확인하는 쿼리, 1: 좋아요 클릭함, 0: 아직 좋아요 클릭안함.
SEELCT COUNT(*) as cnt
FROM jjim
WHERE memberno = 1 AND contentsno=1;

2. 좋아요 처리
INSERT INTO good(goodno, memberno, contentsno, rdate)
VALUES(good_seq.nextval, 1, 1, sysdata)


