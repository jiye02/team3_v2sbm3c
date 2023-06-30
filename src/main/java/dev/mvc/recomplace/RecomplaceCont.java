package dev.mvc.recomplace;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.recomcontents.RecomcontentsProcInter;




@Controller
public class RecomplaceCont {
  @Autowired
  @Qualifier("dev.mvc.recomplace.RecomplaceProc")  // @Component("dev.mvc.cate.CateProc")
  private RecomplaceProcInter recomplaceProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") 
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.recomcontents.RecomcontentsProc") 
  private RecomcontentsProcInter recomcontentsProc;
  
  public RecomplaceCont() {
    System.out.println("-> RecomplaceCont created.");
  }
  
  
  //등록폼
   // http://localhost:9093/recomplace/create.do
   @RequestMapping(value="/recomplace/create.do", method=RequestMethod.GET)
   public ModelAndView create(HttpSession session) {
     // System.out.println("-> CateCont create()");
     
     ModelAndView mav = new ModelAndView();
     
     if (this.adminProc.isAdmin(session) == true) {
       // spring.mvc.view.prefix=/WEB-INF/views/
       // spring.mvc.view.suffix=.jsp
       mav.setViewName("/recomplace/create"); // /WEB-INF/views/cate/create.jsp      
     } else {
       mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
     }
  
     return mav;
   }
  
   // 등록 처리
   // http://localhost:9093/recomplace/create.do
   @RequestMapping(value="/recomplace/create.do", method=RequestMethod.POST)
   public ModelAndView create(HttpSession session, RecomplaceVO recomplaceVO) { // <form> 태그의 값이 자동으로 저장됨
     // request.getParameter("name"); 자동으로 실행
     // System.out.println("-> name: " + cateVO.getName());
     
     ModelAndView mav = new ModelAndView();
     
     if (session.getAttribute("admin_id") != null) {
       int cnt = this.recomplaceProc.create(recomplaceVO);
       
       if (cnt == 1) {
         // request.setAttribute("code", "create_success"); // 고전적인 jsp 방법 
         // mav.addObject("code", "create_success");
         mav.setViewName("redirect:/recomplace/list_all.do");     // 목록으로 자동 이동
         
       } else {
         // request.setAttribute("code", "create_fail");
         mav.addObject("code", "create_fail");
         mav.setViewName("/recomplace/msg"); // /WEB-INF/views/cate/msg.jsp // 등록 실패 메시지 출력
  
       }
       
       // request.setAttribute("cnt", cnt);
       mav.addObject("cnt", cnt);
     } else {
       mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
     }
     
     return mav;
   }
   
   
   /**
    * 모든 레코드 목록, http://localhost:9093/recomplace/list_all.do
    * @return
    */
   @RequestMapping(value="/recomplace/list_all.do", method=RequestMethod.GET)
   public ModelAndView list_all() {
     ModelAndView mav = new ModelAndView();
     
     ArrayList<RecomplaceVO> list = this.recomplaceProc.list_all();
     mav.addObject("list", list);
     // request.setAttribute("list", list);
     
     // System.out.println("-> list size: " + list.size());
     
     // mav.setViewName("/cate/list_all"); // /webapp/WEB-INF/views/cate/list_all.jsp
     mav.setViewName("/recomplace/list_all_ajax"); // /webapp/WEB-INF/views/cate/list_all_ajax.jsp
     
     return mav;
   }
   
   /**
    * Ajax, JSON 지원 읽기, http://localhost:9091/cate/read_ajax_json.do?cateno=1
    * {"visible":"Y","seqno":1,"name":"고전","cnt":100,"cateno":1}
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/recomplace/read_ajax_json.do", method=RequestMethod.GET)
   public String read_ajax_json(int recno) {
     
     try {
       Thread.sleep(2000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     
     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recno);
     // cateno, name, cnt, rdate, udate, seqno, visible
     
     JSONObject json = new JSONObject();
     json.put("recno", recomplaceVO.getRecno());
     json.put("recname", recomplaceVO.getRecname());
     json.put("cnt", recomplaceVO.getCnt());
     json.put("seqno", recomplaceVO.getSeqno());
     json.put("visible", recomplaceVO.getVisible());
     
     return json.toString();
   }
 
 
 
   /**
    * cateno를 FK로 사용하는 레코드 갯수 읽기, http://localhost:9091/cate/read_ajax_json_fk.do?cateno=1
    * {"visible":"Y","seqno":1,"name":"고전","cnt":100,"cateno":1}
    * @return
    */
   @ResponseBody
   @RequestMapping(value="/recomplace/read_ajax_json_fk.do", method=RequestMethod.GET)
   public String read_ajax_json_fk(int recno) {
     
     try {
       Thread.sleep(2000);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     
     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recno);
     // cateno, name, cnt, rdate, udate, seqno, visible
     
     JSONObject json = new JSONObject();
     json.put("recno", recomplaceVO.getRecno());
     json.put("recname", recomplaceVO.getRecname());
     json.put("cnt", recomplaceVO.getCnt());
     json.put("seqno", recomplaceVO.getSeqno());
     json.put("visible", recomplaceVO.getVisible());
     
     int count_by_recno = this.recomcontentsProc.count_by_recno(recno); // cateno가 사용되는 레코드 갯수 파악
     json.put("count_by_recno", count_by_recno);
     
     return json.toString();
   }
   
 
 
 
 
  //수정 처리
  // <FORM name='frm' method='POST' action='./read_update.do'>
  // http://localhost:9093/recomplace/read_update.do
  @RequestMapping(value="/recomplace/read_update.do", method = RequestMethod.POST)
  public ModelAndView read_update(RecomplaceVO recomplaceVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.recomplaceProc.update(recomplaceVO);
    
    if (cnt == 0) {
      mav.addObject("code", "update_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 등록
      mav.setViewName("redirect:/recomplace/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/cate/list_all"); // /webapp/WEB-INF/views/cate/list_all.jsp X
    } else { // 등록 실패
      mav.setViewName("/recomplace/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }
  
  // 삭제 처리
  // <FORM name='frm' method='POST' action='./read_delete.do'>
  // http://localhost:9093/recomplace/read_delete.do
  @RequestMapping(value="/recomplace/read_delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int recno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.recomplaceProc.delete(recno);
    
    if (cnt == 0) {
      mav.addObject("code", "delete_fail");
    }
    
    mav.addObject("cnt", cnt);
    
    if (cnt > 0) { // 정상 삭제
      mav.setViewName("redirect:/recomplace/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
      // mav.setViewName("/cate/list_all"); // /webapp/WEB-INF/views/cate/list_all.jsp X
    } else { // 등록 실패
      mav.setViewName("/recomplace/msg"); // /webapp/WEB-INF/views/cate/msg.jsp      
    }
    
    return mav;
  }
  
  // 출력 순서 올림(상향, 10 등 -> 1 등), seqno: 10 -> 1
  // http://localhost:9093/recomplace/update_seqno_up.do?cateno=1
  @RequestMapping(value="/recomplace/update_seqno_up.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_up(int recno) {
    ModelAndView mav = new ModelAndView();
  
    //System.out.println("-> update_seqno_up: " + recno);
    int cnt = this.recomplaceProc.update_seqno_increase(recno);
    //System.out.println("-> cnt: " + cnt);
    
    mav.setViewName("redirect:/recomplace/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 출력 순서 내림(상향, 1 등 -> 10 등), seqno: 1 -> 10
  // http://localhost:9093/recomplace/update_seqno_down.do?cateno=2
  @RequestMapping(value="/recomplace/update_seqno_down.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_down(int recno) {
    ModelAndView mav = new ModelAndView();
  
    //System.out.println("-> update_seqno_down: " + recno);
    int cnt = this.recomplaceProc.update_seqno_decrease(recno);
    //System.out.println("-> cnt: " + cnt);
    
    mav.setViewName("redirect:/recomplace/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 출력 모드 Y로 변경
  // http://localhost:9091/cate/update_visible_y.do?cateno=1
  @RequestMapping(value="/recomplace/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int recno) {
    ModelAndView mav = new ModelAndView();
  
  //  System.out.println("-> update_visible_y: " + cateno);
    int cnt = this.recomplaceProc.update_visible_y(recno);
    
    mav.setViewName("redirect:/recomplace/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 출력 모드 N로 변경
  // http://localhost:9091/cate/update_visible_n.do?cateno=1
  @RequestMapping(value="/recomplace/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int recno) {
    ModelAndView mav = new ModelAndView();
  
    int cnt = this.recomplaceProc.update_visible_n(recno);
    
    mav.setViewName("redirect:/recomplace/list_all.do"); // 콘트롤러의 주소 요청, 자동 이동
    
    return mav;
  }
  
  // 글수 증가
  // http://localhost:9091/cate/update_cnt_add.do?cateno=1
  @RequestMapping(value="/recomplace/update_cnt_add.do", method = RequestMethod.GET)
  public String update_cnt_add(int recno) {
    int cnt = this.recomplaceProc.update_cnt_add(recno);
    return "변경된 글수: " + cnt;
  }
  
  // 글수 감소
  // http://localhost:9091/cate/update_cnt_sub.do?cateno=1
  @RequestMapping(value="/cate/update_cnt_sub.do", method = RequestMethod.GET)
  public String update_cnt_sub(int recno) {
    int cnt = this.recomplaceProc.update_cnt_sub(recno);
    return "변경된 글수: " + cnt;
  }
  
  

}

 
 


 
 


