package dev.mvc.basket;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasketCont {
  @Autowired 
  @Qualifier("dev.mvc.basket.BasketProc")
  private BasketProcInter basketProc;
  
  public BasketCont() {
    System.out.println("-> BasketCont created.");
  }
  
  // http://localhost:9091/basket/create.do
  /**
   * Ajax 등록 처리
   * INSERT INTO basket(basketno, galleryno, memberno, cnt, rdate)
   * VALUES(basket_seq.nextval, #{galleryno}, #{memberno}, #{cnt}, sysdate)
   * @param categrpVO
   * @return
   */
  @RequestMapping(value="/basket/create.do", method=RequestMethod.POST )
  @ResponseBody
  public String create(HttpSession session,
                            int galleryno) {
    BasketVO basketVO = new BasketVO();
    basketVO.setGalleryno(galleryno);  // 상품 번호
    
    int memberno = (Integer)session.getAttribute("memberno");
    basketVO.setMemberno(memberno);   // 회원 번호
    
    basketVO.setCnt(1); // 최초 구매 수량 1개로 지정
    
    int cnt = this.basketProc.create(basketVO); // 등록 처리
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt); // 1: 정상 등록
    
    // System.out.println("-> basketCont create: " + json.toString());

    return json.toString();
  }
  
  /**
   * 회원별 목록
   * 할인 금액 합계 = 할인 금액 * 수량
   * 할인 금액 총 합계 = 할인 금액 총 합계 + 할인 금액 합계
   * 포인트 합계 = 포인트 합계 + (포인트 * 수량)
   * 배송비 = 3000
   * 전체 주문 금액 = 할인 금액 총 합계 + 배송비
   * http://localhost:9091/basket/list_by_memberno.do
   * http://localhost:9091/basket/list_by_memberno.do?cateno=
   * http://localhost:9091/basket/list_by_memberno.do?cateno=4
   * @return
   */
  @RequestMapping(value="/basket/list_by_memberno.do", method=RequestMethod.GET )
  public ModelAndView list_by_memberno(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    int tot = 0;               // 판매 금액 합계 = 판매 금액(단가) * 수량
    int tot_sum = 0;        // 판매 금액 총 합계 = 판매 금액 총 합계 + 판매 금액 합계
    int point_tot = 0;       // 포인트 합계 = 포인트 합계 + (포인트 * 수량)
    int baesong_tot = 0;   // 배송비 합계
    int total_order = 0;    // 전체 주문 금액
    
    if (session.getAttribute("memberno") != null) { // 회원으로 로그인을 했다면 장바구니로 이동
      int memberno = (int)session.getAttribute("memberno");
      
      // 목록
      ArrayList<BasketVO> list = this.basketProc.list_by_memberno(memberno);
      
      for (BasketVO basketVO : list) {
        tot = basketVO.getSaleprice() * basketVO.getCnt();  // 판매 금액 합계 = 판매 금액(단가) * 수량
        basketVO.setTot(tot);
        
        // 판매 금액 총 합계 = 판매 금액 총 합계 + 판매 금액 합계
        tot_sum = tot_sum + basketVO.getTot();
        
        // 포인트 합계 = 포인트 합계 + (포인트 * 수량)
        point_tot = point_tot + (basketVO.getPoint() * basketVO.getCnt());
        
      }
      
      if (tot_sum < 30000) { // 상품 주문 금액이 30,000 원 이하이면 배송비 3,000 원 부여
        if (list.size() > 0) {     // 총 주문 금액이 30,000 이하이면서 상품이 존재한다면 배송비 3,000 할당
          baesong_tot = 3000;
        }
      }
      
      // 전체 주문 금액 = 판매 금액 총 합계 + 배송비
      total_order = tot_sum + baesong_tot; 
          
      mav.addObject("list", list); // request.setAttribute("list", list);
      
      mav.addObject("tot_sum", tot_sum);     // 판매 금액 총 합계
      mav.addObject("point_tot", point_tot);  // 포인트 합계
      mav.addObject("baesong_tot", baesong_tot);   // 배송비
      mav.addObject("total_order", total_order);  // 전체 주문 금액 
      
      mav.setViewName("/basket/list_by_memberno"); // /WEB-INF/views/basket/list_by_memberno.jsp
      
    } else { // 회원으로 로그인하지 않았다면
      // http://localhost:9091/member/login.do?return_url=/basket/list_by_memberno.do
      
      // mav.addObject("return_url", "/basket/list_by_memberno.do"); // 로그인 후 이동할 주소 ★
      
      mav.setViewName("redirect:/member/login.do"); // /WEB-INF/views/member/login_ck_form.jsp

    }
    return mav;
  }
  
  /**
   * 예약일 변경, http://localhost:9093/basket/update_labeldate.do
   * @param session
   * @param basketno 장바구니 번호
   * @param cnt 수량
   * @return 변경된 레코드 갯수
   */
  @RequestMapping(value="/basket/update_labeldate.do", method=RequestMethod.GET )
  public ModelAndView update_labeldate(HttpSession session, BasketVO basketVO) {
    ModelAndView mav = new ModelAndView();
   
    System.out.println("Call");
    
    this.basketProc.update_labeldate(basketVO);
    mav.addObject("basketno",basketVO.getBasketno());
    mav.addObject("labeldate",basketVO.getLabeldate());
    System.out.println(" ->labeldate: " + basketVO.getLabeldate());
    System.out.println(" ->basketno: " + basketVO.getBasketno());
    
    mav.setViewName("redirect:/basket/list_by_memberno.do");
    
    return mav;
  }
  
  /**
   * 수량 변경, http://localhost:9091/basket/update_cnt.do
   * @param session
   * @param basketno 장바구니 번호
   * @param cnt 수량
   * @return 변경된 레코드 갯수
   */
  @RequestMapping(value="/basket/update_cnt.do", method=RequestMethod.POST )
  public ModelAndView update_cnt(HttpSession session, BasketVO basketVO) {
    ModelAndView mav = new ModelAndView();

    this.basketProc.update_cnt(basketVO);      
    mav.setViewName("redirect:/basket/list_by_memberno.do");
    
    return mav;
  }
  
  /**
   * 상품 삭제
   * http://localhost:9091/basket/delete.do
   * @return
   */
  @RequestMapping(value="/basket/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(HttpSession session, @RequestParam(value="basketno", defaultValue="0") int basketno ) {
    ModelAndView mav = new ModelAndView();
    
    this.basketProc.delete(basketno);      
    mav.setViewName("redirect:/basket/list_by_memberno.do");
    
    return mav;
  }
  
}

