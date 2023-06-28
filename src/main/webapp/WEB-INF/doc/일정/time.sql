DROP TABLE time CASCADE CONSTRAINTS; 
CREATE TABLE time(
  timeno  NUMBER(8)     NOT NULL   PRIMARY KEY,
  labeldate   VARCHAR(10)   NOT NULL,  -- 출력할 날짜 2013-10-20 
  rdate       DATE          NOT NULL,   -- 등록 날짜
  memberno    NUMBER(10) NOT NULL,     -- 회원 번호, 레코드를 구분하는 컬럼
  galleryno   NUMBER(10) NOT NULL,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (galleryno) REFERENCES gallery (galleryno)
);

DROP SEQUENCE time_seq;

CREATE SEQUENCE time_seq
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 99999999    -- 최대값: 99999999 --> NUMBER(8) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;
  
INSERT INTO time(timeno, labeldate, rdate, memberno, galleryno)
VALUES(time_seq.nextval, '2023-06-26', sysdate, 2,6);

commit;