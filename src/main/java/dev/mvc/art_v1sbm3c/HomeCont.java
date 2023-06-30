package dev.mvc.art_v1sbm3c;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.exhi.ExhiProcInter;
import dev.mvc.exhi.ExhiVO;
import dev.mvc.recomplace.RecomplaceProcInter;
import dev.mvc.recomplace.RecomplaceVO;

// Setvlet으로 작동함, GET/POST등의 요청을 처리함.
@Controller
public class HomeCont {
  @Autowired
  @Qualifier("dev.mvc.exhi.ExhiProc")  // @Component("dev.mvc.exhi.ExhiProc")
  private ExhiProcInter exhiProc; // ExhiProc 객체가 자동 생성되어 할당됨.
  
  @Autowired
  @Qualifier("dev.mvc.recomplace.RecomplaceProc")  // @Component("dev.mvc.exhi.ExhiProc")
  private RecomplaceProcInter recomplaceProc; // ExhiProc 객체가 자동 생성되어 할당됨
  
  public HomeCont() {
    System.out.println("-> HomeCont created.");
  }
  
  // http://localhost:9093/
  // http://localhost:9093/index.do
  @RequestMapping(value= {"/", "/index.do"}, method=RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView();
    // spring.mvc.view.prefix=/WEB-INF/views/
    // spring.mvc.view.suffix=.jsp
    mav.setViewName("/index"); // /WEB-INF/views/index.jsp
    
    return mav;
  }
  
  // http://localhost:9093/menu/top.do
  @RequestMapping(value= {"/menu/top.do"}, method=RequestMethod.GET)
  public ModelAndView top() {
    ModelAndView mav = new ModelAndView();

    ArrayList<ExhiVO> list = this.exhiProc.list_all_y();
    mav.addObject("list", list);
    
    ArrayList<RecomplaceVO> reclist = this.recomplaceProc.list_all_y();
    mav.addObject("reclist", reclist);
    
    mav.setViewName("/menu/top"); // /WEB-INF/views/menu/top.jsp
    
    return mav;
  }
  
  
}

