package dev.mvc.jjim;

import java.util.ArrayList;

public interface JjimDAOInter {
  /**
   * 찜 등록
   * @param basketVO
   * @return
   */
  public int create(JjimVO jjimVO);
  
  /**
   * memberno 회원 번호별 쇼핑카트 목록 출력
   * @return
   */
  public ArrayList<JjimVO> list_by_memberno(int memberno);
  
  /**
   * 찜 확인
   * @param jjimVO
   * @return
   */
  public int check(JjimVO jjimVO);
  
  /**
   * 찜 삭제
   * @param basketno
   * @return
   */
  public int delete(int memberno);
  
}
