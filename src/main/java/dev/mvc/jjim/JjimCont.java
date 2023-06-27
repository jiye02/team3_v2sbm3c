package dev.mvc.jjim;

import java.util.ArrayList;
import java.util.HashMap;

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

import dev.mvc.admin.AdminVO;

@Controller
public class JjimCont {
  @Autowired
  @Qualifier("dev.mvc.jjim.JjimProc")
  private JjimProcInter jjimProc;

  public JjimCont() {
    System.out.println("-> JjimCont created.");
  }

  // http://localhost:9091/jjim/create.do
  /**
   * Ajax 등록 처리 INSERT INTO jjim(jjimno, galleryno, memberno, cnt, rdate)
   * VALUES(jjim_seq.nextval, #{galleryno}, #{memberno}, #{cnt}, sysdate)
   * 
   * @param categrpVO
   * @return
   */
  @RequestMapping(value = "/jjim/create.do", method = RequestMethod.POST)
  @ResponseBody
  public String create(HttpSession session, int galleryno) {

    JjimVO jjimVO = new JjimVO();
    jjimVO.setGalleryno(galleryno); // 상품 번호

    int memberno = (Integer) session.getAttribute("memberno");
    jjimVO.setMemberno(memberno); // 회원 번호

    int cnt = this.jjimProc.create(jjimVO); // 등록 처리

    JSONObject json = new JSONObject();
    json.put("cnt", cnt); // 1: 정상 등록

    // System.out.println("-> jjimCont create: " + json.toString());

    return json.toString();
  }

  /**
   * 회원별 목록 할인 금액 합계 = 할인 금액 * 수량 할인 금액 총 합계 = 할인 금액 총 합계 + 할인 금액 합계 포인트 합계 = 포인트
   * 합계 + (포인트 * 수량) 배송비 = 3000 전체 주문 금액 = 할인 금액 총 합계 + 배송비
   * http://localhost:9091/jjim/list_by_memberno.do
   * http://localhost:9091/jjim/list_by_memberno.do?cateno=
   * http://localhost:9091/jjim/list_by_memberno.do?cateno=4
   * 
   * @return
   */
  @RequestMapping(value = "/jjim/list_by_memberno.do", method = RequestMethod.GET)
  public ModelAndView list_by_memberno(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    if (session.getAttribute("memberno") != null) { // 회원으로 로그인을 했다면 장바구니로 이동
      int memberno = (int) session.getAttribute("memberno");

      // 목록
      ArrayList<JjimVO> list = this.jjimProc.list_by_memberno(memberno);

      mav.addObject("list", list); // request.setAttribute("list", list);

      mav.setViewName("/jjim/list_by_memberno"); // /WEB-INF/views/categrp/list_by_memberno.jsp

    } else { // 회원으로 로그인하지 않았다면
      // http://localhost:9091/member/login.do?return_url=/jjim/list_by_memberno.do

      mav.addObject("return_url", "/jjim/list_by_memberno.do"); // 로그인 후 이동할 주소 ★

      mav.setViewName("redirect:/member/login.do"); // /WEB-INF/views/member/login_ck_form.jsp

    }
    return mav;
  }

  /**
   * 수량 변경, http://localhost:9091/jjim/delete.do
   * 
   * @param session
   * @param jjimno  장바구니 번호
   * @param cnt     수량
   * @return 변경된 레코드 갯수
   */
  @RequestMapping(value = "/jjim/update_cnt.do", method = RequestMethod.POST)
  public ModelAndView update_cnt(HttpSession session, JjimVO jjimVO) {
    ModelAndView mav = new ModelAndView();

    this.jjimProc.update_cnt(jjimVO);
    mav.setViewName("redirect:/jjim/list_by_memberno.do");

    return mav;
  }

  /**
   * 상품 삭제 http://localhost:9091/jjim/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/jjim/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpSession session, @RequestParam(value = "jjimno", defaultValue = "0") int jjimno) {
    ModelAndView mav = new ModelAndView();

    this.jjimProc.delete(jjimno);
    mav.setViewName("redirect:/jjim/list_by_memberno.do");

    return mav;
  }

  /* *//**
        * 찜 체크 http://localhost:9093/jjim/check.do?memberno=6&galleryno=4
        * 
        * @param memberVO
        * @param galleryVO
        * @return
        *//*
           * @RequestMapping(value="/jjim/check.do", method=RequestMethod.GET ) public
           * ModelAndView check(int memberno, int galleryno, JjimVO memberVO, JjimVO
           * galleryVO) { ModelAndView mav = new ModelAndView();
           * 
           * HashMap<Object, Object> map = new HashMap<Object, Object>();
           * map.put("memberno", memberno); // 키, 값 map.put("galleryno", galleryno);
           * 
           * return mav; }
           */
  
  

}
