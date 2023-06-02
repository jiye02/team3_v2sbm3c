/**********************************/
/* Table Name: 예약상세 */
/**********************************/
DROP TABLE order_item CASCADE CONSTRAINTS;

CREATE TABLE order_item(
        order_itemno                      NUMBER(10)         NOT NULL         PRIMARY KEY,
        memberno                          NUMBER(10)         NULL ,
        order_payno                       NUMBER(10)         NOT NULL,
        galleryno                        NUMBER(10)         NULL ,
        cnt                               NUMBER(5)          DEFAULT 1         NOT NULL,
        tot                               NUMBER(10)         DEFAULT 0         NOT NULL,
        stateno                           NUMBER(1)          DEFAULT 0         NOT NULL,
        rdate                             DATE               NOT NULL,
        FOREIGN KEY (order_payno) REFERENCES order_pay (order_payno),
        FOREIGN KEY (memberno) REFERENCES MEMBER (memberno),
        FOREIGN KEY (galleryno) REFERENCES gallery (galleryno)
);

COMMENT ON TABLE order_item is '예약 상세';
COMMENT ON COLUMN order_item.order_itemno is '예약상세번호';
COMMENT ON COLUMN order_item.MEMBERNO is '회원 번호';
COMMENT ON COLUMN order_item.order_payno is '예약 번호';
COMMENT ON COLUMN order_item.GALLERYNO is '컨텐츠 번호';
COMMENT ON COLUMN order_item.cnt is '수량';
COMMENT ON COLUMN order_item.tot is '합계';
COMMENT ON COLUMN order_item.stateno is '예약상태';
COMMENT ON COLUMN order_item.rdate is '예약날짜';

DROP SEQUENCE order_item_seq;
CREATE SEQUENCE order_item_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지

-- 등록  
-- 배송 상태(stateno):  1: 결재 완료, 2: 상품 준비중, 3: 배송 시작, 4: 배달중, 5: 오늘 도착, 6: 배달 완료
-- FK(사전에 레코드가 등록되어 있어야함): memberno, order_payno, galleryno
-- 예) 3번 회원이 4번 결제를 했으며 구입 상품은 1번인 경우: 3, 4, 1
INSERT INTO order_item(order_itemno, memberno, order_payno, galleryno, cnt, tot, stateno, rdate)
VALUES (order_item_seq.nextval, 3, 1, 1, 1, 10000, 1, sysdate);

commit; 


-- 전체 목록
SELECT order_itemno, memberno, order_payno, galleryno, cnt, tot, stateno, rdate
FROM order_item
ORDER BY order_itemno DESC;

--회원별 목록
SELECT order_itemno, memberno, order_payno, galleryno, cnt, tot, stateno, rdate
FROM order_item
WHERE memberno=1
ORDER BY order_itemno DESC;


-- 수정: 개발 안함.


-- 삭제
DELETE FROM order_item
WHERE memberno=1;

commit;
