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
import dev.mvc.basket.BasketVO;

@Controller
public class JjimCont {
  @Autowired
  @Qualifier("dev.mvc.jjim.JjimProc")
  private JjimProcInter jjimProc;

  public JjimCont() {
    System.out.println("-> JjimCont created.");

  }

  // http://localhost:9093/jjim/create.do?galleryno=2
  /**
   * Ajax 등록 처리 INSERT INTO jjim(jjimno, galleryno, memberno, cnt, rdate)
   * VALUES(jjim_seq.nextval, #{galleryno}, #{memberno}, #{cnt}, sysdate)
   * 
   * @param categrpVO
   * @return
   */
  @RequestMapping(value = "/jjim/create.do", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView create(HttpSession session, int galleryno) {
    ModelAndView mav = new ModelAndView();

    int memberno = (Integer) session.getAttribute("memberno");

    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("galleryno", galleryno);
    map.put("memberno", memberno);

    int duplicate_cnt = this.jjimProc.jjim_check(map);
    if (duplicate_cnt > 0) {
      // 이미 찜이 되어 있는 경우 // 기존에 찜되어 있는 레코드 삭제 //
      int delete_cnt = this.jjimProc.delete(map); //
      System.out.println("-> delete_cnt: " + delete_cnt);
    } else { // 새로운 찜의 처리 // 레코드 추가
      int crate_cnt = this.jjimProc.create(map);
      System.out.println("-> crate_cnt: " + crate_cnt);

    }

//    JjimVO jjimVO = new JjimVO();
//    jjimVO.setGalleryno(galleryno); // 상품 번호
//
//    
//    jjimVO.setMemberno(memberno); // 회원 번호
//
//    int cnt = this.jjimProc.create(jjimVO); // 등록 처리
//
//    JSONObject json = new JSONObject();
//    json.put("cnt", cnt); // 1: 정상 등록
//
//    // System.out.println("-> jjimCont create: " + json.toString());

    mav.setViewName("redirect:/gallery/read.do?galleryno=" + galleryno);

    return mav;
  }

  /**
   * 회원별 목록 http://localhost:9093/jjim/list_by_memberno.do
   * http://localhost:9093/jjim/list_by_memberno.do?cateno=
   * http://localhost:9093/jjim/list_by_memberno.do?cateno=4
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
      // http://localhost:9091/member/login.do?return_url=/basket/list_by_memberno.do

      mav.addObject("return_url", "/jjim/list_by_memberno.do"); // 로그인 후 이동할 주소 ★

      mav.setViewName("redirect:/member/login.do"); // /WEB-INF/views/member/login_ck_form.jsp

    }
    return mav;
  }

  /*  *//**
         * 상품 삭제 http://localhost:9091/basket/delete.do
         * 
         * @return
         *//*
            * @RequestMapping(value="/jjim/delete.do", method=RequestMethod.POST ) public
            * ModelAndView delete(HttpSession session, @RequestParam(value="jjimno",
            * defaultValue="0") int jjimno ) { ModelAndView mav = new ModelAndView();
            * 
            * this.jjimProc.delete(jjimno);
            * mav.setViewName("redirect:/jjim/list_by_memberno.do");
            * 
            * return mav; }
            */

}