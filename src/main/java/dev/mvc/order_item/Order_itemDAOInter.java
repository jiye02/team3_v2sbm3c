package dev.mvc.order_item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.order_pay.Order_payVO;

public interface Order_itemDAOInter {
  /**
   * 등록
   * @param order_itemVO
   * @return
   */
  public int create(Order_itemVO order_itemVO);
  
  /**
   * 회원별 주문 결재 목록
   * @param order_payno
   * @return
   */
  public List<Order_itemVO> list_by_memberno(HashMap<String, Object> map);
  
  /**
   * 주문 전체 목록
   * @return
   */
  public ArrayList<Order_itemVO> list(int adminno);
  
  /**
   * 주문 삭제 처리
   * @param order_itemno
   * @return
   */
  public int delete(int order_itemno);
  
} 