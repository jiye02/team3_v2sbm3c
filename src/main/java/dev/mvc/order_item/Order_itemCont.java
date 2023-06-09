package dev.mvc.order_item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
import dev.mvc.basket.BasketProcInter;
import dev.mvc.basket.BasketVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.order_pay.Order_payProcInter;
import dev.mvc.order_pay.Order_payVO;
import dev.mvc.order_item.Order_itemVO;

@Controller
public class Order_itemCont {
  @Autowired
  @Qualifier("dev.mvc.order_item.Order_itemProc")
  private Order_itemProcInter order_itemProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.basket.BasketProc")
  private BasketProcInter basketProc;

  @Autowired
  @Qualifier("dev.mvc.order_pay.Order_payProc")
  private Order_payProcInter order_payProc;


  public Order_itemCont() {
    System.out.println("-> Order_itemCont created.");
  }

  /**
   * 예약 결제/회원별 목록 http://localhost:9093/order_item/list_by_memberno.do
   * 
   * @return
   */
  @RequestMapping(value = "/order_item/list_by_memberno.do", method = RequestMethod.GET)
  public ModelAndView list_by_memberno(HttpSession session, int order_payno, BasketVO basketVO) {
    ModelAndView mav = new ModelAndView();

    int baesong_tot = 0; // 배송비 합계
    int tot_sum = 0; // 할인 금액 총 합계(금액)
    int total_order = 0; // 전체 주문 금액

    if (session.getAttribute("memberno") != null) { // 회원으로 로그인을 했다면 쇼핑카트로 이동
      int memberno = (int) session.getAttribute("memberno");

      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("order_payno", order_payno);
      map.put("memberno", memberno);

      List<Order_itemVO> list = this.order_itemProc.list_by_memberno(map);

      for (Order_itemVO order_itemVO : list) {
        tot_sum += order_itemVO.getTot();
      }

      if (tot_sum < 30000) { // 상품 주문 금액이 30,000 원 이하이면 배송비 3,000 원 부여
        baesong_tot = 3000;
      }

      total_order = tot_sum + baesong_tot; // 전체 주문 금액

      mav.addObject("baesong_tot", baesong_tot); // 배송비
      mav.addObject("total_order", total_order); // 할인 금액 총 합계(금액)
      mav.addObject("list", list); // request.setAttribute("list", list);

      mav.setViewName("/order_item/list_by_memberno"); // /views/order_item/list_by_memberno.jsp
    } else { // 회원으로 로그인하지 않았다면
      mav.addObject("return_url", "/order_item/list_by_memberno.do"); // 로그인 후 이동할 주소 ★

      mav.setViewName("redirect:/member/login.do"); // /WEB-INF/views/member/login_ck_form.jsp
    }

    return mav;
  }

  /**
   * 예약 상세 전체 목록 http://localhost:9093/order_item/list.do
   * 
   * @param order_payno
   * @return
   */
  @RequestMapping(value = "/order_item/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session,
      @RequestParam(value = "order_payno", required = false) Integer order_payno) {
    ModelAndView mav = new ModelAndView();

    int baesong_tot = 0;
    int tot_sum = 0;
    int total_order = 0;

    if (session.getAttribute("adminno") != null) {
      List<Order_itemVO> list;

      if (order_payno != null) {
        list = this.order_itemProc.list(order_payno);
      } else {
        list = new ArrayList<>(); // 빈 목록 생성
      }

      for (Order_itemVO order_itemVO : list) {
        tot_sum += order_itemVO.getTot();
      }

      if (tot_sum < 30000) {
        baesong_tot = 3000;
      }

      total_order = tot_sum + baesong_tot;

      mav.addObject("baesong_tot", baesong_tot);
      mav.addObject("total_order", total_order);
      mav.addObject("list", list);

      mav.setViewName("/order_item/list");
    } else {
      mav.addObject("return_url", "/order_item/list.do");

      mav.setViewName("redirect:/admin/login.do");
    }

    return mav;
  }

  /**
   * 주문 삭제 http://localhost:9093/order_item/delete.do
   * 
   * @param order_payVO
   * @return
   */
  @RequestMapping(value = "/order_item/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpSession session,
      @RequestParam(value = "order_itemno", defaultValue = "0") int order_itemno, Order_payVO order_payVO) {
    ModelAndView mav = new ModelAndView();
    System.out.println("-> order_payVO.getOrder_payno(): " + order_payVO.getOrder_payno());
    
    this.order_itemProc.delete(order_itemno);
    mav.setViewName("redirect:/order_item/list.do?order_payno=" + order_payVO.getOrder_payno());

    return mav;
  }

}
