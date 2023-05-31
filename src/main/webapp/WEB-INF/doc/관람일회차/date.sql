DROP TABLE iljung;
CREATE TABLE iljung(
		dateno      NUMERIC(10)     NOT NULL    PRIMARY KEY,
		timeno        DATE          NOT NULL,
		opentime    NUMERIC(10)     NOT NULL,
		closetime   NUMERIC(10)     NOT NULL,
        galleryno   NUMBER(10)     NOT NULL,
  FOREIGN KEY (galleryno) REFERENCES gallery (galleryno)
);

COMMENT ON TABLE iljung is '일정';
COMMENT ON COLUMN iljung.dateno is '일정번호';
COMMENT ON COLUMN iljung.timeno is '회차시간';
COMMENT ON COLUMN iljung.opentime is '개장시간';
COMMENT ON COLUMN iljung.closetime is '마감시간';
COMMENT ON COLUMN iljung.galleryno is '갤러리 번호';