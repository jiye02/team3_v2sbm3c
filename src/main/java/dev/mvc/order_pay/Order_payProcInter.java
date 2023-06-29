package dev.mvc.order_pay;

import java.util.ArrayList;
import java.util.List;

public interface Order_payProcInter {

  /**
   * 
   * @param order_payVO
   * @return
   */
  public int create(Order_payVO order_payVO);
  
  /**
   * 회원별 주문 결재 목록
   * @param memberno
   * @return
   */
  public List<Order_payVO> list_by_memberno(int memberno);
  
  /**
   * 주문 전체 목록
   * @return
   */
  public ArrayList<Order_payVO> list(int adminno);
  
  /**
   * 주문 삭제 처리
   * @param order_payno
   * @return
   */
  public int delete(int order_payno);
}