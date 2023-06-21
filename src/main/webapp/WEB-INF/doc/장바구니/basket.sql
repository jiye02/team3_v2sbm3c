/**********************************/
/* Table Name: 장바구니 */
/**********************************/
DROP TABLE basket CASCADE CONSTRAINTS;
CREATE TABLE basket (
  basketno                        NUMBER(10) NOT NULL PRIMARY KEY,
  galleryno                    NUMBER(10) NOT NULL ,
  memberno                      NUMBER(10) NOT NULL,
  quantity                           NUMBER(10) DEFAULT 0 NOT NULL,
  rdate                         DATE NOT NULL,
  FOREIGN KEY (galleryno) REFERENCES gallery (galleryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);
 
COMMENT ON TABLE basket is '장바구니';
COMMENT ON COLUMN basket.basketno is '장바구니 번호';
COMMENT ON COLUMN basket.galleryno is '갤러리 번호';
COMMENT ON COLUMN basket.memberno is '회원 번호';
COMMENT ON COLUMN basket.quantity is '수량';
COMMENT ON COLUMN basket.rdate is '날짜';

DROP SEQUENCE basket_seq;
CREATE SEQUENCE basket_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지
  
-- INSERT
SELECT galleryno, title, price FROM gallery;
galleryNO TITLE                 PRICE
---------- --------------------- ----------
         4 유럽 단풍 여행           6000
         5 알래스카 단풍 여행        6000  <- 사용 예정
         3 환상의 단풍 여행         6000

         
SELECT memberno, mname FROM member;
  MEMBERNO MNAME                         
---------- ------------------------------
         1 질문답변관리자                
         2 고객관리자                    
         3 왕눈이   <- 사용 예정                     
         4 아로미                        
         5 투투투                                

INSERT INTO basket(basketno, galleryno, memberno, quantity, rdate)
VALUES(basket_seq.nextval, 5, 3, 1, sysdate); -- 3번 회원이 5번 상품을 1개 구입

INSERT INTO basket(basketno, galleryno, memberno, quantity, rdate)
VALUES(basket_seq.nextval, 5, 3, 1, sysdate);
commit;

-- ERROR
-- ORA-02291: integrity constraint (KD.SYS_C007449) violated - parent key not found
INSERT INTO basket(basketno, galleryno, memberno, quantity, rdate)
VALUES(basket_seq.nextval, 50000, -3000, 1, sysdate);

-- LIST
SELECT basketno, galleryno, memberno, quantity, rdate FROM basket ORDER BY basketno ASC;

    basketNO galleryNO   MEMBERNO        quantity RDATE              
---------- ---------- ---------- ---------- -------------------
         1          5          3          1 2022-10-11 10:13:42
         2          5          3          1 2022-10-11 10:13:42
         
-- LIST gallery join
SELECT t.basketno, c.galleryno, c.title, c.thumb1, c.price, c.dc, c.saleprice, c.point, t.memberno, t.quantity, t.rdate 
FROM gallery c, basket t
WHERE c.galleryno = t.galleryno
ORDER BY basketno ASC;

-- 3번 회원의 장바구니 목록
SELECT t.basketno, c.galleryno, c.title, c.thumb1, c.price, c.dc, c.saleprice, c.point, t.memberno, t.quantity, t.rdate 
FROM gallery c, basket t
WHERE (c.galleryno = t.galleryno) AND t.memberno = 3
ORDER BY basketno ASC
         
-- READ
-- 잘못된 방법
SELECT t.basketno, c.galleryno, c.title, c.price, t.memberno, t.quantity, t.rdate 
FROM gallery c, basket t
WHERE t.basketno=1;
    basketNO galleryNO TITLE                                                   PRICE   MEMBERNO        quantity RDATE              
---------- ---------- -------------------------------------------------- ---------- ---------- ---------- -------------------
         1          4 유럽 단풍 여행                                           6000          3          1 2022-10-11 10:13:42
         1          5 알래스카 단풍 여행                                       6000          3          1 2022-10-11 10:13:42
         1          3 환상의 단풍 여행                                         6000          3          1 2022-10-11 10:13:42

-- join을 기본적으로 선언하고 추가적인 조건 명시
SELECT t.basketno, c.galleryno, c.title, c.price, t.memberno, t.quantity, t.rdate 
FROM gallery c, basket t
WHERE (c.galleryno = t.galleryno) AND t.basketno=1;
    basketNO galleryNO TITLE                                                   PRICE   MEMBERNO        quantity RDATE              
---------- ---------- -------------------------------------------------- ---------- ---------- ---------- -------------------
         1          5 알래스카 단풍 여행                                       6000          3          1 2022-10-11 10:13:42

-- UPDATE
UPDATE basket
SET quantity=2
WHERE basketno=1;
commit;

-- DELETE
DELETE FROM basket WHERE basketno=2;
commit;