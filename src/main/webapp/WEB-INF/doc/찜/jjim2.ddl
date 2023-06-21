
/**********************************/
/* Table Name: 찜 */
/**********************************/
CREATE TABLE jjim(
		jjimnum NUMERIC(100) NOT NULL PRIMARY KEY,
		memberno NUMERIC(10) NOT NULL,
		GALLERYNO NUMERIC(10),
		RDATE DATE NOT NULL,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (GALLERYNO) REFERENCES GALLERY (GALLERYNO)
);

