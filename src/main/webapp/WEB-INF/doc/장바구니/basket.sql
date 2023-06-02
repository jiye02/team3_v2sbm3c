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

