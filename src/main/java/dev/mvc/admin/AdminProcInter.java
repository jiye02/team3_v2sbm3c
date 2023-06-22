package dev.mvc.admin;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  // 구현 클래스를 교체하기 쉬운 구조 지원

public interface AdminProcInter {
  /**
   * 로그인
   * @param AdminVO
   * @return
   */
  public int login(AdminVO adminVO);
  
  /**
   * id를 통한 회원 정보
   * @param String
   * @return
   */
  public AdminVO read_by_id(String id);

  /**
   * 관리자인지 체크
   * @param session
   * @return
   */
  public boolean isAdmin(HttpSession session);

  /**
   * adminno를 통한 회원 정보
   * @param adminno 회원 번호
   * @return
   */
  public AdminVO read(int adminno);
  
  /**
   * 회원 가입
   * @param adminVO
   * @return
   */
  public int create(AdminVO adminVO);

  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkID(String id);
  
  /**
   * 관리자 전체 목록
   * @return
   */
  public ArrayList<AdminVO> list();
  
  /**
   * 수정 처리
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  
  /**
   * 관리자 삭제 처리
   * @param adminno
   * @return
   */
  public int delete(int adminno);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<Object, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<Object, Object> map);
}
 
  
  



