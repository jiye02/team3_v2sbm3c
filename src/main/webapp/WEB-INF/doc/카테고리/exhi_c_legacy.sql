/**********************************/
/* Table Name: 카테고리 */
/**********************************/
DROP TABLE exhi;

CREATE TABLE exhi(
    exhino                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    name                              VARCHAR2(30)     NOT NULL,
    cnt                               NUMBER(7)    DEFAULT 0     NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE exhi is '카테고리';
COMMENT ON COLUMN exhi.exhino is '카테고리번호';
COMMENT ON COLUMN exhi.name is '카테고리 이름';
COMMENT ON COLUMN exhi.cnt is '관련 자료수';
COMMENT ON COLUMN exhi.rdate is '등록일';

DROP SEQUENCE exhi_seq;

CREATE SEQUENCE exhi_seq
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
  
-- CREATE -> SELECT LIST -> SELECT READ -> UPDATE -> DELETE -> COUNT(*)
-- CREATE
INSERT INTO exhi(exhino, name, cnt, rdate) VALUES(exhi_seq.nextval, '여행', 0, sysdate);
INSERT INTO exhi(exhino, name, cnt, rdate) VALUES(exhi_seq.nextval, '영화', 0, sysdate);
INSERT INTO exhi(exhino, name, cnt, rdate) VALUES(exhi_seq.nextval, '바다', 0, sysdate);
INSERT INTO exhi(exhino, name, cnt, rdate) VALUES(exhi_seq.nextval, '산', 0, sysdate);
commit;

-- SELECT LIST
SELECT exhino, name, cnt, rdate FROM exhi ORDER BY exhino ASC;
    EXHINO NAME                                  CNT RDATE              
---------- ------------------------------ ---------- -------------------
         1 여행                                    0 2023-03-21 12:10:00
         2 영화                                    0 2023-03-21 12:10:00
         3 바다                                    0 2023-03-21 12:10:00
         4 산                                      0 2023-03-21 12:10:00
         
-- SELECT READ
SELECT exhino, name, cnt, rdate FROM exhi WHERE exhino=1;
    EXHINO NAME                                  CNT RDATE              
---------- ------------------------------ ---------- -------------------
         1 여행                                    0 2023-03-21 12:10:00
         
-- UPDATE
UPDATE exhi SET name='캠핑' WHERE exhino=4;
commit;
SELECT * FROM exhi;
    EXHINO NAME                                  CNT RDATE              
---------- ------------------------------ ---------- -------------------
         1 여행                                    0 2023-03-21 12:18:26
         2 영화                                    0 2023-03-21 12:18:26
         3 바다                                    0 2023-03-21 12:18:26
         4 캠핑                                    0 2023-03-21 12:18:26

-- DELETE
-- DELETE FROM exhi;
-- COMMIT;

DELETE FROM exhi WHERE exhino=6;
commit;

SELECT * FROM exhi;
    EXHINO NAME                                  CNT RDATE              
---------- ------------------------------ ---------- -------------------
         1 여행                                    0 2023-03-21 12:18:26
         2 영화                                    0 2023-03-21 12:18:26
         3 바다                                    0 2023-03-21 12:18:26

-- COUNT(*)
SELECT COUNT(*) as cnt FROM exhi;
       CNT
----------
         3


