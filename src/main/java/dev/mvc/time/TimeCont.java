package dev.mvc.time;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.exhi.ExhiVO;
import dev.mvc.gallery.GalleryProcInter;
import dev.mvc.member.MemberProcInter;



@Controller
public class TimeCont {

  @Autowired
  @Qualifier("dev.mvc.time.TimeProc") 
  private TimeProcInter timeProc;
  
  @Autowired
  @Qualifier("dev.mvc.gallery.GalleryProc") 
  private GalleryProcInter galleryProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public TimeCont () {
    System.out.println("-> TimeCont created.");
  }
  
//등록 처리
 // http://localhost:9093/time/create.do
 @RequestMapping(value="/time/create.do", method=RequestMethod.POST)
 public ModelAndView create(HttpSession session, TimeVO timeVO) { // <form> 태그의 값이 자동으로 저장됨
   // request.getParameter("name"); 자동으로 실행
   // System.out.println("-> name: " + exhiVO.getName());
   
     ModelAndView mav = new ModelAndView();
     int cnt = this.timeProc.create(timeVO);
     System.out.println("-->"+cnt);
     
     if (cnt == 1) {
       // request.setAttribute("code", "create_success"); // 고전적인 jsp 방법 
       // mav.addObject("code", "create_success");
       mav.setViewName("redirect:/gallery/list_all.do");     // 목록으로 자동 이동
       
     } else {
       // request.setAttribute("code", "create_fail");
       mav.addObject("code", "create_fail");
       mav.setViewName("/gallery/msg"); // /WEB-INF/views/exhi/msg.jsp // 등록 실패 메시지 출력

     }
     
     // request.setAttribute("cnt", cnt);
     mav.addObject("cnt", cnt);

   
   return mav;
   }
  
  

}
