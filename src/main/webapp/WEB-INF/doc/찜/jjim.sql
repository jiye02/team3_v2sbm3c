/**********************************/
/* Table Name: 찜 */
/**********************************/

DROP TABLE jjim CASCADE CONSTRAINTS; 
CREATE TABLE jjim(
    jjimno NUMERIC(20) NOT NULL  PRIMARY KEY,
    memberno NUMERIC(10) NOT NULL,
    galleryno NUMERIC(10) NOT NULL,
    rdate DATE NOT NULL,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (galleryno) REFERENCES gallery (galleryno)
);

COMMENT ON TABLE jjim is '찜 - 순위';
COMMENT ON COLUMN jjim.jjimno is '찜 번호';
COMMENT ON COLUMN jjim.memberno is '회원 번호';
COMMENT ON COLUMN jjim.galleryno is '갤러리 번호';
COMMENT ON COLUMN jjim.rdate is '날짜';

DROP SEQUENCE jjim_seq;

CREATE SEQUENCE jjim_seq
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO jjim(jjimno, memberno, galleryno, rdate)
VALUES(jjim_seq.nextval, 5, 3, sysdate)

INSERT INTO jjim(jjimno, memberno, galleryno, rdate)
VALUES(jjim_seq.nextval, 6, 4, sysdate)

INSERT INTO jjim(jjimno, memberno, galleryno, rdate)
VALUES(jjim_seq.nextval, 7, 5, sysdate)

INSERT INTO jjim(jjimno, memberno, galleryno, rdate)
VALUES(jjim_seq.nextval, 6, 4, sysdate);

SELECT jjimno, memberno, galleryno, rdate FROM jjim ORDER BY jjimno ASC;

SELECT jjimno, memberno, galleryno, rdate 
FROM jjim 
WHERE jjimno=1;

DELETE FROM jjim WHERE jjimno=3;

SELECT * FROM jjim;

commit;

-- 찜을 했는지 확인후 안했으면 INSERT 실행, 했으면 DELETE 실행
SELECT COUNT(*) as cnt FROM jjim WHERE memberno=6 AND galleryno=4;

-- 관심 상품의 중복 제거
SELECT COUNT(*) as cnt FROM favorite WHERE memberno=6 AND galleryno=4;



