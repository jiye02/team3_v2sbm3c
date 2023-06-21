package dev.mvc.basket;

import java.util.ArrayList;

public interface BasketDAOInter {
  /**
   * 카트에 상품 등록
   * @param basketVO
   * @return
   */
  public int create(BasketVO basketVO);
  
  /**
   * memberno 회원 번호별 쇼핑카트 목록 출력
   * @return
   */
  public ArrayList<BasketVO> list_by_memberno(int memberno);
  
  /**
   * 수량 변경
   * @param basketno
   * @return
   */
  public int update_cnt(BasketVO basketVO);
  
  /**
   * 상품 삭제
   * @param basketno
   * @return
   */
  public int delete(int basketno);
  
}
