/**********************************/
/* Table Name: 설문조사항목 */
/**********************************/
DROP TABLE calendar;
CREATE TABLE calendar(
  calendarno  NUMBER(8)     NOT NULL   PRIMARY KEY,
  labeldate   VARCHAR(10)   NOT NULL,  -- 출력할 날짜 2013-10-20 
  label       VARCHAR(100)  NOT NULL,  -- 달력에 출력될 레이블
  title       VARCHAR(100)  NOT NULL,  -- 제목(*)
  content     CLOB          NOT NULL,  -- 글 내용
  cnt         NUMBER(7)     DEFAULT 0, -- 조회수
  rdate       DATE          NOT NULL,   -- 등록 날짜
  memberno    NUMBER(10) NOT NULL,     -- 회원 번호, 레코드를 구분하는 컬럼
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE calendar is '일정';
COMMENT ON COLUMN calendar.calendarno is '일정 번호';
COMMENT ON COLUMN calendar.labeldate is '달력에 출력되는 문장의 기준 날짜';
COMMENT ON COLUMN calendar.label is '달력에 출력될 레이블';
COMMENT ON COLUMN calendar.title is '제목';
COMMENT ON COLUMN calendar.content is '글 내용';
COMMENT ON COLUMN calendar.cnt is '조회수';
COMMENT ON COLUMN calendar.rdate is '등록 날짜';
COMMENT ON COLUMN calendar.memberno is '회원 번호';

DROP SEQUENCE calendar_seq;

CREATE SEQUENCE calendar_seq
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 99999999    -- 최대값: 99999999 --> NUMBER(8) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지


INSERT INTO calendar(calendarno, labeldate, label, title, content, cnt, rdate, memberno)
VALUES(calendar_seq.nextval, '2023-06-26', '주차장 건설로 임시 폐쇄',
       '주차장 건설로 임시 폐쇄합니다.', '주차장 건설로 임시 폐쇄합니다.', 0, sysdate, 1);

INSERT INTO calendar(calendarno, labeldate, label, title, content, cnt, rdate, memberno)
VALUES(calendar_seq.nextval, '2023-06-26', '주차장 건설로 임시 폐쇄2',
       '주차장 건설로 임시 폐쇄합니다.2', '주차장 건설로 임시 폐쇄합니다.2', 0, sysdate, 1);
       
INSERT INTO calendar(calendarno, labeldate, label, title, content, cnt, rdate, memberno)
VALUES(calendar_seq.nextval, '2023-06-26', '주차장 건설로 임시 폐쇄3',
       '주차장 건설로 임시 폐쇄합니다.3', '주차장 건설로 임시 폐쇄합니다.3', 0, sysdate, 1);       

-- 전체 목록
SELECT calendarno, labeldate, label, title, content, cnt, rdate, memberno
FROM calendar
ORDER BY calendarno DESC;

-- 6월달만 출력, SUBSTR(labeldate, 1, 7): 첫번재부터 7개의 문자 추출
SELECT calendarno, labeldate, label, title, content, cnt, rdate, memberno
FROM calendar
WHERE SUBSTR(labeldate, 1, 7) = '2023-06'
ORDER BY calendarno ASC;

-- 특정 날짜만 출력
SELECT calendarno, labeldate, label, title, content, cnt, rdate, memberno
FROM calendar
WHERE labeldate = '2023-06-26'
ORDER BY calendarno ASC;

-- 조회
SELECT calendarno, labeldate, label, title, content, cnt, rdate, memberno
FROM calendar
WHERE calendarno=1;
          
-- 수정
UPDATE calendar
SET labeldate='2023-06-27', label='주차장 완공', title='주차장 완공', content='주차장 완공했습니다.'
WHERE calendarno = 1;

-- 조회수 증가
UPDATE calendar
SET cnt = cnt + 1
WHERE calendarno = 1;

-- 삭제
DELETE FROM calendar
WHERE calendarno = 3;





