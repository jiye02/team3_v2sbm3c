/**********************************/
/* Table Name: 관리자 */
/**********************************/
DROP TABLE admin CASCADE CONSTRAINTS; 
DROP TABLE admin;

CREATE TABLE admin(
    adminno    NUMBER(10)    NOT NULL,
    id         VARCHAR(20)   NOT NULL UNIQUE, -- 아이디, 중복 안됨, 레코드를 구분 
    passwd     VARCHAR(15)   NOT NULL, -- 패스워드, 영숫자 조합
    mname      VARCHAR(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
    mdate      DATE          NOT NULL, -- 가입일    
    grade      NUMBER(2)     NOT NULL, -- 등급(1~10: 관리자, 11~20: 회원, 비회원: 30~39, 정지 회원: 40~49, 탈퇴 회원: 99)    
    PRIMARY KEY (adminno)              -- 한번 등록된 값은 중복 안됨
);

COMMENT ON TABLE admin is '관리자';
COMMENT ON COLUMN admin.adminno is '관리자 번호';
COMMENT ON COLUMN admin.id is '아이디';
COMMENT ON COLUMN admin.PASSWD is '패스워드';
COMMENT ON COLUMN admin.MNAME is '성명';
COMMENT ON COLUMN admin.MDATE is '가입일';
COMMENT ON COLUMN admin.GRADE is '등급';

DROP SEQUENCE admin_seq;

CREATE SEQUENCE admin_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

INSERT INTO admin(adminno, id, passwd, mname, mdate, grade)
VALUES(admin_seq.nextval, 'admin_jy', '021027', '관리자(JY)', sysdate, 1);

INSERT INTO admin(adminno, id, passwd, mname, mdate, grade)
VALUES(admin_seq.nextval, 'admin_js', '000814', '관리자(JS)', sysdate, 1);

INSERT INTO admin(adminno, id, passwd, mname, mdate, grade)
VALUES(admin_seq.nextval, 'admin_mk', '000605', '관리자(MK)', sysdate, 1);

INSERT INTO admin(adminno, id, passwd, mname, mdate, grade)
VALUES(admin_seq.nextval, 'admin_bh', '000829', '관리자(BH)', sysdate, 1);

INSERT INTO admin(adminno, id, passwd, mname, mdate, grade)
VALUES(admin_seq.nextval, 'admin_jh', '000412', '관리자(JH)', sysdate, 1);

commit;

SELECT adminno, id, passwd, mname, mdate, grade FROM admin ORDER BY adminno ASC;
   ADMINNO ID                   PASSWD          MNAME                MDATE                    GRADE
---------- -------------------- --------------- -------------------- ------------------- ----------
         1 admin_jy             021027          관리자(JY)           2023-05-18 11:36:13          1
         2 admin_js             000814          관리자(JS)           2023-05-18 11:36:13          1
         3 admin_mk             000605          관리자(MK)           2023-05-18 11:36:13          1
         4 admin_bh             000829          관리자(BH)           2023-05-18 11:36:13          1
         5 admin_jh             000412          관리자(JH)           2023-05-18 11:36:13          1


1) id 중복 확인(null 값을 가지고 있으면 count에서 제외됨)
SELECT COUNT(id)
FROM member
WHERE id='user1';

SELECT COUNT(id) as cnt
FROM member
WHERE id='user1';

-- 삭제
DELETE FROM admin
WHERE adminno >= 6;

SELECT * FROM admin;