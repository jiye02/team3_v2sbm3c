package dev.mvc.jjim;

import java.util.ArrayList;
import java.util.HashMap;

public interface JjimDAOInter {
  /**
   * 카트에 상품 등록
   * @param jjimVO
   * @return
   */
  public int create(JjimVO jjimVO);
  
  /**
   * memberno 회원 번호별 장바구니 목록 출력
   * @return
   */
  public ArrayList<JjimVO> list_by_memberno(int memberno);
  
  /**
   * 수량 변경
   * @param jjimno
   * @return
   */
  public int update_cnt(JjimVO jjimVO);
  
  /**
   * 상품 삭제
   * @param jjimno
   * @return
   */
  public int delete(int jjimno);
  
  /**
   * 찜 확인
   * @param jjimVO
   * @return
   */
  public int check(HashMap<Object, Object> map);

  
}
