package dev.mvc.basket;

import java.util.ArrayList;

import dev.mvc.member.MemberVO;

public interface BasketDAOInter {
  /**
   * 카트에 상품 등록
   * @param basketVO
   * @return
   */
  public int create(BasketVO basketVO);
  
  /**
   * memberno 회원 번호별 장바구니 목록 출력
   * @return
   */
  public ArrayList<BasketVO> list_by_memberno(int memberno);
  
  /**
   * 예약일 변경
   * @param basketno
   * @return
   */
  public int update_labeldate(BasketVO basketVO);
  
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
