/**********************************/
/* Table Name: 공지사항 */
/**********************************/
DROP TABLE notes CASCADE CONSTRAINTS;
DROP TABLE notes;

CREATE TABLE notes(
        notesno            NUMBER(10)      NOT NULL         PRIMARY KEY,
        adminno             NUMBER(10)      NOT NULL , 
        title               VARCHAR2(50)    NOT NULL,
        content             CLOB            NOT NULL,
        cnt                 NUMBER(7)       DEFAULT 0         NOT NULL,
        passwd              VARCHAR2(15)    NOT NULL,
        word                VARCHAR2(100)   NULL ,
        rdate               DATE            NOT NULL,
        file1               VARCHAR(100)    NULL,  -- 원본 파일명 image
        file1saved          VARCHAR(100)    NULL,  -- 저장된 파일명, image
        thumb1              VARCHAR(100)    NULL,   -- preview image
        size1               NUMBER(10)      DEFAULT 0 NULL  -- 파일 사이즈
);
COMMENT ON TABLE notes is '공지사항';
COMMENT ON COLUMN notes.notesno is '공지사항 번호';
COMMENT ON COLUMN notes.adminno is '관리자 번호';
COMMENT ON COLUMN notes.title is '제목';
COMMENT ON COLUMN notes.content is '내용';
COMMENT ON COLUMN notes.cnt is '조회수';
COMMENT ON COLUMN notes.passwd is '패스워드';
COMMENT ON COLUMN notes.word is '검색어';
COMMENT ON COLUMN notes.rdate is '등록일';
COMMENT ON COLUMN notes.file1 is '이미지';
COMMENT ON COLUMN notes.file1saved is '실제 저장된 이미지';
COMMENT ON COLUMN notes.thumb1 is '메인 이미지 Preview';
COMMENT ON COLUMN notes.size1 is '메인 이미지 크기';


DROP SEQUENCE notes_seq;

CREATE SEQUENCE notes_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

commit;