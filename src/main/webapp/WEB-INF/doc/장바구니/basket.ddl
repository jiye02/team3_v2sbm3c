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
		memberno NUMBER(10)
);

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		memberno NUMBER(10) NOT NULL PRIMARY KEY,
		adminno NUMBER(10)
);

/**********************************/
/* Table Name: 주문 결제 */
/**********************************/
CREATE TABLE ORDER_PAY(
		ORDER_PAYNO NUMBER(10) NOT NULL PRIMARY KEY,
		memberno NUMBER(10),
		RNAME VARCHAR2(30) NOT NULL,
		RTEL VARCHAR2(14) NOT NULL,
		RZIPCODE VARCHAR2(5),
		RADDRESS1 VARCHAR2(80) NOT NULL,
		RADDRESS2 VARCHAR2(50) NOT NULL,
		PAYTYPE NUMBER(1) NOT NULL,
		AMOUNT NUMBER(10) NOT NULL,
		RDATE DATE NOT NULL,
		ORDER_ITEMNO NUMBER(10),
		basketno NUMERIC(10),
		quantity NUMERIC(10),
		adminno NUMBER(10)
);

/**********************************/
/* Table Name: 장바구니 */
/**********************************/
DROP TABLE basket CASCADE CONSTRAINTS;

CREATE TABLE basket(
    basketno NUMERIC(10) NOT NULL PRIMARY KEY,
    quantity NUMERIC(10) NOT NULL,
    galleryno NUMERIC(10),
    memberno NUMBER(10),
    order_payno NUMBER(10),
  FOREIGN KEY (galleryno) REFERENCES gallery (galleryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (order_payno) REFERENCES order_pay (order_payno)
);

COMMENT ON TABLE basket is '장바구니';
COMMENT ON COLUMN basket.basketno is '장바구니 번호';
COMMENT ON COLUMN basket.quantity is '수량';
COMMENT ON COLUMN basket.galleryno is '갤러리 번호';
COMMENT ON COLUMN basket.memberno is '회원 번호';
COMMENT ON COLUMN basket.order_payno is '주문 번호';

DROP SEQUENCE basket_seq;
CREATE SEQUENCE basket_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO basket(basketno, quantity, galleryno, memberno, order_payno)
VALUES (basket_seq.nextval, 1, '1', '1', '1');
INSERT INTO basket(basketno, quantity, galleryno, memberno, order_payno)
VALUES (basket_seq.nextval, 2, '2', '2', '2');
          
          
-- 전체 목록            
SELECT basketno, quantity, galleryno, memberno, order_payno
FROM basket
ORDER BY basketno DESC;


commit;