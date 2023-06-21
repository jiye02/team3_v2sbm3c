/**********************************/
/* Table Name: 예약_결제 */
/**********************************/
DROP TABLE pay CASCADE CONSTRAINTS;
DROP TABLE pay;

CREATE TABLE pay(
        payno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        memberno                              NUMBER(10)     NOT NULL , -- FK
        tname         VARCHAR(30)      NOT NULL, -- 성명, 한글 10자 저장 가능
        ttel             VARCHAR(14)      NOT NULL, -- 전화번호
        tzipcode     VARCHAR(5)        NOT NULL, -- 우편번호, 12345
        taddress1    VARCHAR(80)     NOT NULL, -- 주소 1
        taddress2    VARCHAR(50)     NOT NULL, -- 주소 2
        ptype                           NUMBER(1)    DEFAULT 0     NOT NULL,
        amount                            NUMBER(10)     DEFAULT 0     NOT NULL,
        rdate                              DATE     NOT NULL, 
        FOREIGN KEY (memberno) REFERENCES member (memberno) ON DELETE CASCADE
);

COMMENT ON TABLE pay is '결제';
COMMENT ON COLUMN pay.payno is '결제 번호';
COMMENT ON COLUMN pay.memberno is '회원 번호';
COMMENT ON COLUMN PAY.tname is '받는이 성명';
COMMENT ON COLUMN PAY.ttel is '받는이 전화번호';
COMMENT ON COLUMN PAY.tzipcode is '받는이 우편번호';
COMMENT ON COLUMN PAY.TADDRESS1 is '받는이 주소1';
COMMENT ON COLUMN PAY.TADDRESS2 is '받는이 주소2';
COMMENT ON COLUMN pay.ptype is '결재 종류';
COMMENT ON COLUMN pay.amount is '결재금액';
COMMENT ON COLUMN pay.rdate is '주문날짜';

DROP SEQUENCE pay_seq;

CREATE SEQUENCE pay_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;      
  
  commit;
  
  select * from pay;
-- 등록  
-- 결제 종류(paytype):  1: 신용 카드, 2: 모바일, 3: 계좌 이체  
INSERT INTO order_pay(order_payno, memberno, rname, rtel, rzipcode,
                                 raddress1, raddress2, paytype, amount, rdate)
VALUES (order_pay_seq.nextval, 3, '길동이', '111-2222-3333', '12345',
             '서울시 종로구', '관철동', 1, 32000, sysdate);
INSERT INTO order_pay(order_payno, memberno, rname, rtel, rzipcode,
                                 raddress1, raddress2, paytype, amount, rdate)
VALUES (order_pay_seq.nextval, 3, '아로미', '111-2222-3333', '12345',
             '서울시 중랑구', '면목동', 1, 15000, sysdate);
INSERT INTO order_pay(order_payno, memberno, rname, rtel, rzipcode,
                                 raddress1, raddress2, paytype, amount, rdate)
VALUES (order_pay_seq.nextval, 3, '왕눈이', '111-2222-3333', '12345',
             '서울시 동대문구', '전농동', 1, 63000, sysdate);
commit; 

-- 전체 목록
SELECT order_payno, memberno, rname, rtel, rzipcode, raddress1, raddress2, paytype, amount, rdate
FROM order_pay
ORDER BY order_payno DESC;

--회원별 목록
SELECT order_payno, memberno, rname, rtel, rzipcode, raddress1, raddress2, paytype, amount, rdate
FROM order_pay
WHERE memberno=3
ORDER BY order_payno DESC;

-- 수정: 개발 안함.

-- 삭제
DELETE FROM order_pay
WHERE order_payno=1;

commit;

