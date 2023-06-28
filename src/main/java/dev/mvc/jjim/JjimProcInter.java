package dev.mvc.jjim;

import java.util.ArrayList;
import java.util.HashMap;

public interface JjimProcInter {
  /**
   * 카트에 상품 등록
   * @param jjimVO
   * @return
   */
  public int create(JjimVO jjimVO);
  
  /**
   *  jjim 테이블에서 galleryno가 같은 레코드의 갯수를 산출하는 기능
   * @param jjimVO
   * @return
   */
  public int count(int galleryno);
  
  /**
   * memberno 회원 번호별 장바구니 목록 출력
   * @return
   */
  public ArrayList<JjimVO> list_by_memberno(int memberno);
  
  /**
   * jjim 테이블 jjimno 삭제
   * @return
   */
  public int delete(int jjimno);
  
}