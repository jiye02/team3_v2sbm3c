DROP TABLE gallery CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능
DROP TABLE gallery;

CREATE TABLE gallery(
        galleryno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        adminno                              NUMBER(10)     NOT NULL , -- FK
        exhino                                NUMBER(10)         NOT NULL , -- FK
        title                                 VARCHAR2(200)         NOT NULL,
        content                               CLOB                  NOT NULL,
        jjim                                 NUMBER(7)         DEFAULT 0         NOT NULL,
        cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
        replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
        passwd                                VARCHAR2(15)         NOT NULL,
        word                                  VARCHAR2(100)         NULL ,
        rdate                                 DATE               NOT NULL,
        file1                                   VARCHAR(100)          NULL,  -- 원본 파일명 image
        file1saved                            VARCHAR(100)          NULL,  -- 저장된 파일명, image
        thumb1                              VARCHAR(100)          NULL,   -- preview image
        size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈
        price                                 NUMBER(10)      DEFAULT 0 NULL,  
        dc                                    NUMBER(10)      DEFAULT 0 NULL,  
        saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
        point                                 NUMBER(10)      DEFAULT 0 NULL,  
        salecnt                               NUMBER(10)      DEFAULT 0 NULL,
        min                                   VARCHAR2(10)      NULL,
        max                                   VARCHAR2(10)      NULL,
        map                                   VARCHAR2(1000)  NULL,
        youtube                               VARCHAR2(1000)  NULL,
        FOREIGN KEY (adminno) REFERENCES admin (adminno),
        FOREIGN KEY (exhino) REFERENCES exhi (exhino)
);

COMMENT ON TABLE gallery is '컨텐츠 - 전시회';
COMMENT ON COLUMN gallery.galleryno is '컨텐츠 번호';
COMMENT ON COLUMN gallery.adminno is '관리자 번호';
COMMENT ON COLUMN gallery.exhino is '카테고리 번호';
COMMENT ON COLUMN gallery.title is '제목';
COMMENT ON COLUMN gallery.content is '내용';
COMMENT ON COLUMN gallery.jjim is '추천수';
COMMENT ON COLUMN gallery.cnt is '조회수';
COMMENT ON COLUMN gallery.replycnt is '댓글수';
COMMENT ON COLUMN gallery.passwd is '패스워드';
COMMENT ON COLUMN gallery.word is '검색어';
COMMENT ON COLUMN gallery.rdate is '등록일';
COMMENT ON COLUMN gallery.file1 is '메인 이미지';
COMMENT ON COLUMN gallery.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN gallery.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN gallery.size1 is '메인 이미지 크기';
COMMENT ON COLUMN gallery.price is '정가';
COMMENT ON COLUMN gallery.dc is '할인률';
COMMENT ON COLUMN gallery.saleprice is '판매가';
COMMENT ON COLUMN gallery.point is '포인트';
COMMENT ON COLUMN gallery.salecnt is '수량';
COMMENT ON COLUMN gallery.min is '전시 시작일';
COMMENT ON COLUMN gallery.max is '전시 종료일';
COMMENT ON COLUMN gallery.map is '지도';
COMMENT ON COLUMN gallery.youtube is 'Youtube 영상';

DROP SEQUENCE gallery_seq;

CREATE SEQUENCE gallery_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- 등록 화면 유형 1: 커뮤니티(공지사항, 게시판, 자료실, 갤러리,  Q/A...)글 등록
INSERT INTO gallery(galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(gallery_seq.nextval, 1, 1, '청계천 매화 거리', '제기동역에서 가까움 명품 산책로', 0, 0, 0, '123',
       '산책', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

-- 유형 1 전체 목록
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1
FROM gallery
ORDER BY galleryno DESC;

-- 유형 2 카테고리별 목록
INSERT INTO gallery(galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(gallery_seq.nextval, 1, 2, '대행사', '흙수저와 금수저의 성공 스토리', 0, 0, 0, '123',
       '드라마,K드라마,넷플릭스', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);
            
INSERT INTO gallery(galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(gallery_seq.nextval, 1, 2, '더글로리', '학폭의 결말', 0, 0, 0, '123',
       '드라마,K드라마,넷플릭스', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

INSERT INTO gallery(galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, 
                     word, rdate, file1, file1saved, thumb1, size1)
VALUES(gallery_seq.nextval, 1, 2, '더글로리', '학폭의 결말', 0, 0, 0, '123',
       '드라마,K드라마,넷플릭스', sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

COMMIT;
select * from gallery;
-- 1번 exhino 만 출력
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=1
ORDER BY galleryno DESC;

-- 2번 exhino 만 출력
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=2
ORDER BY galleryno ASC;

-- 3번 exhino 만 출력
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=3
ORDER BY galleryno ASC;

-- 모든 레코드 삭제
DELETE FROM gallery;
commit;

-- 삭제
DELETE FROM gallery
WHERE galleryno = 25;
commit;

DELETE FROM gallery
WHERE exhino=12 AND galleryno <= 41;

commit;

SELECT * FROM gallery;


-- ----------------------------------------------------------------------------------------------------
-- 검색, exhino별 검색 목록
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, word, rdate,
       file1, file1saved, thumb1, size1, map, youtube
FROM gallery
ORDER BY galleryno ASC;

-- 카테고리별 목록
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, word, rdate,
       file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=2
ORDER BY galleryno ASC;

-- 1) 검색
-- ① exhino별 검색 목록
-- word 컬럼의 존재 이유: 검색 정확도를 높이기 위하여 중요 단어를 명시
-- 글에 'swiss'라는 단어만 등장하면 한글로 '스위스'는 검색 안됨.
-- 이런 문제를 방지하기위해 'swiss,스위스,스의스,수의스,유럽' 검색어가 들어간 word 컬럼을 추가함.
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=8 AND word LIKE '%부대찌게%'
ORDER BY galleryno DESC;

-- title, content, word column search
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=8 AND (title LIKE '%부대찌게%' OR content LIKE '%부대찌게%' OR word LIKE '%부대찌게%')
ORDER BY galleryno DESC;

-- ② 검색 레코드 갯수
-- 전체 레코드 갯수, 집계 함수
SELECT COUNT(*)
FROM gallery
WHERE exhino=8;

  COUNT(*)  <- 컬럼명
----------
         5
         
SELECT COUNT(*) as cnt -- 함수 사용시는 컬럼 별명을 선언하는 것을 권장
FROM gallery
WHERE exhino=8;

       CNT <- 컬럼명
----------
         5

-- exhino 별 검색된 레코드 갯수
SELECT COUNT(*) as cnt
FROM gallery
WHERE exhino=8 AND word LIKE '%부대찌게%';

SELECT COUNT(*) as cnt
FROM gallery
WHERE exhino=8 AND (title LIKE '%부대찌게%' OR content LIKE '%부대찌게%' OR word LIKE '%부대찌게%');

-- SUBSTR(컬럼명, 시작 index(1부터 시작), 길이), 부분 문자열 추출
SELECT galleryno, SUBSTR(title, 1, 4) as title
FROM gallery
WHERE exhino=8 AND (content LIKE '%부대%');

-- SQL은 대소문자를 구분하지 않으나 WHERE문에 명시하는 값은 대소문자를 구분하여 검색
SELECT galleryno, title, word
FROM gallery
WHERE exhino=8 AND (word LIKE '%FOOD%');

SELECT galleryno, title, word
FROM gallery
WHERE exhino=8 AND (word LIKE '%food%'); 

SELECT galleryno, title, word
FROM gallery
WHERE exhino=8 AND (LOWER(word) LIKE '%food%'); -- 대소문자를 일치 시켜서 검색

SELECT galleryno, title, word
FROM gallery
WHERE exhino=8 AND (UPPER(word) LIKE '%' || UPPER('FOOD') || '%'); -- 대소문자를 일치 시켜서 검색 ★

SELECT galleryno, title, word
FROM gallery
WHERE exhino=8 AND (LOWER(word) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색

SELECT galleryno || '. ' || title || ' 태그: ' || word as title -- 컬럼의 결합, ||
FROM gallery
WHERE exhino=8 AND (LOWER(word) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색


SELECT UPPER('한글') FROM dual; -- dual: 오라클에서 SQL 형식을 맞추기위한 시스템 테이블

-- ----------------------------------------------------------------------------------------------------
-- 검색 + 페이징 + 메인 이미지
-- ----------------------------------------------------------------------------------------------------
-- step 1
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE exhino=1 AND (title LIKE '%단풍%' OR content LIKE '%단풍%' OR word LIKE '%단풍%')
ORDER BY galleryno DESC;

-- step 2
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, rownum as r
FROM (
          SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                     file1, file1saved, thumb1, size1, map, youtube
          FROM gallery
          WHERE exhino=1 AND (title LIKE '%단풍%' OR content LIKE '%단풍%' OR word LIKE '%단풍%')
          ORDER BY galleryno DESC
);

-- step 3, 1 page
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM gallery
                     WHERE exhino=1 AND (title LIKE '%단풍%' OR content LIKE '%단풍%' OR word LIKE '%단풍%')
                     ORDER BY galleryno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

-- step 3, 2 page
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM gallery
                     WHERE exhino=1 AND (title LIKE '%단풍%' OR content LIKE '%단풍%' OR word LIKE '%단풍%')
                     ORDER BY galleryno DESC
           )          
)
WHERE r >= 4 AND r <= 6;

-- 대소문자를 처리하는 페이징 쿼리
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
           file1, file1saved, thumb1, size1, map, youtube, r
FROM (
           SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                      file1, file1saved, thumb1, size1, map, youtube, rownum as r
           FROM (
                     SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
                                file1, file1saved, thumb1, size1, map, youtube
                     FROM gallery
                     WHERE exhino=1 AND (UPPER(title) LIKE '%' || UPPER('단풍') || '%' 
                                         OR UPPER(content) LIKE '%' || UPPER('단풍') || '%' 
                                         OR UPPER(word) LIKE '%' || UPPER('단풍') || '%')
                     ORDER BY galleryno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

-- ----------------------------------------------------------------------------
-- 조회
-- ----------------------------------------------------------------------------
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, passwd, word, rdate,
           file1, file1saved, thumb1, size1, map, youtube
FROM gallery
WHERE galleryno = 1;

-- ----------------------------------------------------------------------------
-- 다음 지도, MAP, 먼저 레코드가 등록되어 있어야함.
-- map                                   VARCHAR2(1000)         NULL ,
-- ----------------------------------------------------------------------------
-- MAP 등록/수정
UPDATE gallery SET map='카페산 지도 스크립트' WHERE galleryno=1;

-- MAP 삭제
UPDATE gallery SET map='' WHERE galleryno=1;

commit;

-- ----------------------------------------------------------------------------
-- Youtube, 먼저 레코드가 등록되어 있어야함.
-- youtube                                   VARCHAR2(1000)         NULL ,
-- ----------------------------------------------------------------------------
-- youtube 등록/수정
UPDATE gallery SET youtube='Youtube 스크립트' WHERE galleryno=1;

-- youtube 삭제
UPDATE gallery SET youtube='' WHERE galleryno=1;

commit;

-- 패스워드 검사, id="password_check"
SELECT COUNT(*) as cnt 
FROM gallery
WHERE galleryno=1 AND passwd='123';

-- 텍스트 수정: 예외 컬럼: 추천수, 조회수, 댓글 수
UPDATE gallery
SET title='기차를 타고', content='계획없이 여행 출발',  word='나,기차,생각' 
WHERE galleryno = 2;

-- ERROR, " 사용 에러
UPDATE gallery
SET title='기차를 타고', content="계획없이 '여행' 출발",  word='나,기차,생각'
WHERE galleryno = 1;

-- ERROR, \' 에러
UPDATE gallery
SET title='기차를 타고', content='계획없이 \'여행\' 출발',  word='나,기차,생각'
WHERE galleryno = 1;

-- SUCCESS, '' 한번 ' 출력됨.
UPDATE gallery
SET title='기차를 타고', content='계획없이 ''여행'' 출발',  word='나,기차,생각'
WHERE galleryno = 1;

-- SUCCESS
UPDATE gallery
SET title='기차를 타고', content='계획없이 "여행" 출발',  word='나,기차,생각'
WHERE galleryno = 1;

commit;

-- 파일 수정
UPDATE gallery
SET file1='train.jpg', file1saved='train.jpg', thumb1='train_t.jpg', size1=5000
WHERE galleryno = 1;

-- 삭제
DELETE FROM gallery
WHERE galleryno = 1;

commit;

DELETE FROM gallery
WHERE galleryno >= 7;

commit;

-- 추천
UPDATE gallery
SET recom = recom + 1
WHERE galleryno = 1;

-- exhino FK 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM gallery 
WHERE exhino=1;

-- adminno FK 특정 관리자에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM gallery 
WHERE adminno=1;

-- exhino FK 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM gallery
WHERE exhino=1;

-- adminno FK 특정 관리자에 속한 레코드 모두 삭제
DELETE FROM gallery
WHERE adminno=1;

commit;

-- 다수의 카테고리에 속한 레코드 갯수 산출: IN
SELECT COUNT(*) as cnt
FROM gallery
WHERE exhino IN(1,2,3);

-- 다수의 카테고리에 속한 레코드 모두 삭제: IN
SELECT galleryno, adminno, exhino, title
FROM gallery
WHERE exhino IN(1,2,3);

CONTENTSNO    ADMINNO     EXHINO TITLE                                                                                                                                                                                                                                                                                                       
---------- ---------- ---------- ------------------------
         3             1                   1           인터스텔라                                                                                                                                                                                                                                                                                                  
         4             1                   2           드라마                                                                                                                                                                                                                                                                                                      
         5             1                   3           컨저링                                                                                                                                                                                                                                                                                                      
         6             1                   1           마션       
         
SELECT galleryno, adminno, exhino, title
FROM gallery
WHERE exhino IN('1','2','3');

CONTENTSNO    ADMINNO     EXHINO TITLE                                                                                                                                                                                                                                                                                                       
---------- ---------- ---------- ------------------------
         3             1                   1           인터스텔라                                                                                                                                                                                                                                                                                                  
         4             1                   2           드라마                                                                                                                                                                                                                                                                                                      
         5             1                   3           컨저링                                                                                                                                                                                                                                                                                                      
         6             1                   1           마션       

-- ----------------------------------------------------------------------------------------------------
-- exhi + gallery INNER JOIN
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT c.name,
       t.galleryno, t.adminno, t.exhino, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube
FROM exhi c, gallery t
WHERE c.exhino = t.exhino
ORDER BY t.galleryno DESC;

-- gallery, admin INNER JOIN
SELECT t.galleryno, t.adminno, t.exhino, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube,
       a.mname
FROM admin a, gallery t
WHERE a.adminno = t.adminno
ORDER BY t.galleryno DESC;

SELECT t.galleryno, t.adminno, t.exhino, t.title, t.content, t.recom, t.cnt, t.replycnt, t.word, t.rdate,
       t.file1, t.file1saved, t.thumb1, t.size1, t.map, t.youtube,
       a.mname
FROM admin a INNER JOIN gallery t ON a.adminno = t.adminno
ORDER BY t.galleryno DESC;

-- ----------------------------------------------------------------------------------------------------
-- View + paging
-- ----------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW vgallery
AS
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, word, rdate,
        file1, file1saved, thumb1, size1, map, youtube
FROM gallery
ORDER BY galleryno DESC;
                     
-- 1 page
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
       file1, file1saved, thumb1, size1, map, youtube, r
FROM (
     SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
            file1, file1saved, thumb1, size1, map, youtube, rownum as r
     FROM vgallery -- View
     WHERE exhino=14 AND (title LIKE '%야경%' OR content LIKE '%야경%' OR word LIKE '%야경%')
)
WHERE r >= 1 AND r <= 3;

-- 2 page
SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
       file1, file1saved, thumb1, size1, map, youtube, r
FROM (
     SELECT galleryno, adminno, exhino, title, content, recom, cnt, replycnt, rdate,
            file1, file1saved, thumb1, size1, map, youtube, rownum as r
     FROM vgallery -- View
     WHERE exhino=14 AND (title LIKE '%야경%' OR content LIKE '%야경%' OR word LIKE '%야경%')
)
WHERE r >= 4 AND r <= 6;


SELECT * FROM gallery;