package dev.mvc.jjim;

import java.util.ArrayList;

public interface JjimProcInter {
  /**
   * 좋아요 등록
   * @param basketVO
   * @return
   */
  public int create(JjimVO jjimVO);
  
  /**
   * jjimno 회원 번호별 장바구니 목록 출력
   * @return
   */
  public ArrayList<JjimVO> list_by_memberno(int memberno);
  
  /**
   * 좋아요 확인
   * @param recomVO
   * @return
   */
  public int check(JjimVO jjimVO);
 
  /**
   * 상품 삭제
   * @param basketno
   * @return
   */
  public int delete(int memberno);
  
}

