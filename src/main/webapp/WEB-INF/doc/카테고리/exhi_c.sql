/**********************************/
/* Table Name: 카테고리 */
/**********************************/
DROP TABLE exhi CASCADE CONSTRAINTS; 
DROP TABLE exhi;

CREATE TABLE exhi(
    exhino                  NUMBER(10)     NOT NULL PRIMARY KEY,
    name                   VARCHAR2(30)    NOT NULL,
    cnt                      NUMBER(7)     DEFAULT 0  NOT NULL,
    rdate                    DATE            NOT NULL,
    udate                   DATE                 NULL,
    seqno                   NUMBER(10)        DEFAULT 0  NOT NULL,    
    visible                   CHAR(1)    DEFAULT 'N'     NOT NULL
);

COMMENT ON TABLE exhi is '카테고리';
COMMENT ON COLUMN exhi.exhino is '카테고리번호';
COMMENT ON COLUMN exhi.name is '카테고리 이름';
COMMENT ON COLUMN exhi.cnt is '관련 자료수';
COMMENT ON COLUMN exhi.rdate is '등록일';
COMMENT ON COLUMN exhi.udate is '수정일';
COMMENT ON COLUMN exhi.seqno is '출력 순서';
COMMENT ON COLUMN exhi.visible is '출력 모드';

DROP SEQUENCE exhi_seq;

CREATE SEQUENCE exhi_seq
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
  
-- CREATE -> SELECT LIST -> SELECT READ -> UPDATE -> DELETE -> COUNT(*)
-- CREATE
INSERT INTO exhi(exhino, name, cnt, rdate, seqno) VALUES(exhi_seq.nextval, '미술 전시회', 0, sysdate, 1);
INSERT INTO exhi(exhino, name, cnt, rdate, seqno) VALUES(exhi_seq.nextval, '의류 전시회', 0, sysdate, 2);
INSERT INTO exhi(exhino, name, cnt, rdate, seqno) VALUES(exhi_seq.nextval, '이색 전시회', 0, sysdate, 3);
INSERT INTO exhi(exhino, name, cnt, rdate, seqno) VALUES(exhi_seq.nextval, '팝업스토어', 0, sysdate, 4);
INSERT INTO exhi(exhino, name, cnt, rdate, seqno) VALUES(exhi_seq.nextval, '지역축제', 0, sysdate, 5);
commit;

-- SELECT LIST
SELECT exhino, name, cnt, rdate, seqno, visible FROM exhi ORDER BY exhino ASC;

    EXHINO NAME                                  CNT RDATE                    SEQNO V
---------- ------------------------------ ---------- ------------------- ---------- -
         1 미술 전시회                                    0 2023-05-18 10:39:07          1 Y
         2 의류 전시회                              0 2023-05-18 10:39:07          2 Y
         3 팝업스토어                                    0 2023-05-18 10:39:07          3 Y
         4 이색 전시회                                   0 2023-05-18 10:39:07          4 Y
         5 지역축제                                  0 2023-05-18 10:39:07          5 Y

-- SELECT READ
SELECT exhino, name, cnt, rdate, seqno, visible FROM exhi WHERE exhino=1;
    EXHINO NAME                                  CNT RDATE                    SEQNO V
---------- ------------------------------ ---------- ------------------- ---------- -
         1 서울                                    0 2023-05-18 10:39:07          1  Y
         
-- UPDATE
UPDATE exhi SET seqno=3 WHERE exhino=4;
commit;
SELECT * FROM exhi;
    EXHINO NAME                                  CNT RDATE                    SEQNO V
---------- ------------------------------ ---------- ------------------- ---------- -
         1 서울                                    0 2023-05-18 10:39:07          1 Y


1 행 이(가) 업데이트되었습니다.


    EXHINO NAME                                  CNT RDATE               UDATE                    SEQNO V
---------- ------------------------------ ---------- ------------------- ------------------- ---------- -
         1 서울                                    0 2023-05-18 10:39:07                              2 Y
         2 경기/인천                               0 2023-05-18 10:39:07                              2 Y
         3 대구                                    0 2023-05-18 10:39:07                              3 Y
         4 부산                                    0 2023-05-18 10:39:07                              4 Y
         5 제주                                    0 2023-05-18 10:39:07                              5 Y


-- DELETE
-- DELETE FROM exhi;
-- COMMIT;

DELETE FROM exhi WHERE exhino=6;
commit;
SELECT * FROM exhi;

-- COUNT(*)
SELECT COUNT(*) as cnt FROM exhi;
       CNT
----------
         5

-- ------------------------------------------------------------------
-- 출력 순서 변경 관련 SQL
-- ------------------------------------------------------------------
-- 출력 순서 상향(10등 -> 1등), seqno 컬럼의 값 감소, id: update_seqno_decrease
UPDATE exhi SET seqno = seqno - 1 WHERE exhino=1;

-- 출력 순서 하향(1등 -> 10등), seqno 컬럼의 값 증가, id: update_seqno_increase
UPDATE exhi SET seqno = seqno + 1 WHERE exhino=1;

commit;
-- seqno 컬럼 기준 오름차순 정렬 SELECT LIST, id: list_all
SELECT exhino, name, cnt, rdate, seqno, visible FROM exhi ORDER BY seqno ASC;

-- 한번에 다수의 컬럼값 수정은 사용자가 불편을 느낄수 있음으로 필요시 컬럼을 분할하여 값 변경
-- 예) 패스워드 변경, 별명 변경, 이름 변경등
-- 출력, id: update_visible_Y
UPDATE exhi SET visible='Y' WHERE exhino=1;

-- 숨김, id: update_visible_N
UPDATE exhi SET visible='N' WHERE exhino=2;
commit;

SELECT * FROM exhi;

-- 비회원/회원 SELECT LIST, id: list_all_y
SELECT exhino, name, cnt, rdate, seqno, visible 
FROM exhi 
WHERE visible='Y'
ORDER BY exhino ASC;

-- 자료수 증가, cnt 커럼 1씩 증가, id: update_cnt_add
UPDATE exhi SET cnt = cnt + 1 WHERE exhino=1;

-- 자료수 감소, cnt 커럼 1씩 감소, id: update_cnt_sub
UPDATE exhi SET cnt = cnt - 1 WHERE exhino=1;

commit;


