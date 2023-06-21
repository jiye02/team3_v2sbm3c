package dev.mvc.exhi;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.gallery.Gallery;
import dev.mvc.gallery.GalleryProcInter;
import dev.mvc.gallery.GalleryVO;
import dev.mvc.tool.Tool;

@Controller
public class ExhiCont {
  @Autowired
  @Qualifier("dev.mvc.exhi.ExhiProc")  // @Component("dev.mvc.exhi.ExhiProc")
  private ExhiProcInter exhiProc; // ExhiProc 객체가 자동 생성되어 할당됨.
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") 
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.gallery.GalleryProc") 
  private GalleryProcInter galleryProc;
  
  public ExhiCont() {
    System.out.println("-> ExhiCont created.");
  }
  
  // 등록폼
  // http://localhost:/exhi/create.do
  @RequestMapping(value="/exhi/create.do", method=RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    // System.out.println("-> ExhiCont create()");
    
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      // spring.mvc.view.prefix=/WEB-INF/views/
      // spring.mvc.view.suffix=.jsp
      mav.setViewName("/exhi/create"); // /WEB-INF/views/exhi/create.jsp      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }

    return mav;
  }

  // 등록 처리
  // http://localhost:/exhi/create.do
  @RequestMapping(value="/exhi/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpSession session, ExhiVO exhiVO) { // <form> 태그의 값이 자동으로 저장됨
    // request.getParameter("name"); 자동으로 실행
    // System.out.println("-> name: " + exhiVO.getName());
    
    ModelAndView mav = new ModelAndView();
    
    if (session.getAttribute("admin_id") != null) {
      int cnt = this.exhiProc.create(exhiVO);
      
      if (cnt == 1) {
        // request.setAttribute("code", "create_success"); // 고전적인 jsp 방법 
        // mav.addObject("code", "create_success");
        mav.setViewName("redirect:/exhi/list_all.do");     // 목록으로 자동 이동
        
      } else {
        // request.setAttribute("code", "create_fail");
        mav.addObject("code", "create_fail");
        mav.setViewName("/exhi/msg"); // /WEB-INF/views/exhi/msg.jsp // 등록 실패 메시지 출력

      }
      
      // request.setAttribute("cnt", cnt);
      mav.addObject("cnt", cnt);
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
  
  // http://localhost:/exhi/list_all.do
  @RequestMapping(value="/exhi/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      mav.setViewName("/exhi/list_all"); // /WEB-INF/views/exhi/list_all.jsp
      
      ArrayList<ExhiVO> list = this.exhiProc.list_all();
      mav.addObject("list", list);      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
  
  // http://localhost:/exhi/read.do?exhino=1
  @RequestMapping(value="/exhi/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session, int exhino) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      mav.setViewName("/exhi/read"); // /WEB-INF/views/exhi/read.jsp
      
      ExhiVO exhiVO = this.exhiProc.read(exhino);
      mav.addObject("exhiVO", exhiVO);
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
  
  // 수정폼
  // http://localhost:/exhi/read_update.do?exhino=1
  // http://localhost:/exhi/read_update.do?exhino=2
  // http://localhost:/exhi/read_update.do?exhino=3
  @RequestMapping(value="/exhi/read_update.do", method=RequestMethod.GET)
  public ModelAndView read_update(HttpSession session, int exhino) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      mav.setViewName("/exhi/read_update"); // /WEB-INF/views/exhi/read_update.jsp
      
      ExhiVO exhiVO = this.exhiProc.read(exhino); // 수정용 데이터
      mav.addObject("exhiVO", exhiVO);
      
      ArrayList<ExhiVO> list = this.exhiProc.list_all(); // 목록 출력용 데이터
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      
    }
    
    return mav;
  }
  
  // 수정 처리
  @RequestMapping(value="/exhi/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpSession session, ExhiVO exhiVO) { // <form> 태그의 값이 자동으로 저장됨
//    System.out.println("-> exhino: " + exhiVO.getExhino());
//    System.out.println("-> name: " + exhiVO.getName());
    
    ModelAndView mav = new ModelAndView();

    if (this.adminProc.isAdmin(session) == true) {
      int cnt = this.exhiProc.update(exhiVO);
      
      if (cnt == 1) {
        // request.setAttribute("code", "update_success"); // 고전적인 jsp 방법 
        // mav.addObject("code", "update_success");
        mav.setViewName("redirect:/exhi/list_all.do");       // 자동 주소 이동, Spring 재호출
        
      } else {
        // request.setAttribute("code", "update_fail");
        mav.addObject("code", "update_fail");
        mav.setViewName("/exhi/msg"); // /WEB-INF/views/exhi/msg.jsp
      }
      
      // request.setAttribute("cnt", cnt);
      mav.addObject("cnt", cnt);
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
 
  // 삭제폼, 수정폼을 복사하여 개발 
  // http://localhost:/exhi/read_delete.do?exhino=1
  @RequestMapping(value="/exhi/read_delete.do", method=RequestMethod.GET)
  public ModelAndView read_delete(HttpSession session, int exhino) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      ExhiVO exhiVO = this.exhiProc.read(exhino); // 수정용 데이터
      mav.addObject("exhiVO", exhiVO);
      
      ArrayList<ExhiVO> list = this.exhiProc.list_all(); // 목록 출력용 데이터
      mav.addObject("list", list);
      
      // 특정 카테고리에 속한 레코드 갯수를 리턴
      int count_by_exhino = this.galleryProc.count_by_exhino(exhino);
      mav.addObject("count_by_exhino", count_by_exhino);
      
      mav.setViewName("/exhi/read_delete"); // /WEB-INF/views/exhi/read_delete.jsp
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
  
  // 삭제 처리, 수정 처리를 복사하여 개발
  // 자식 테이블 레코드 삭제 -> 부모 테이블 레코드 삭제
  /**
   * 카테고리 삭제
   * @param session
   * @param exhino 삭제할 카테고리 번호
   * @return
   */
  @RequestMapping(value="/exhi/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(HttpSession session, int exhino) { // <form> 태그의 값이 자동으로 저장됨
//    System.out.println("-> exhino: " + exhiVO.getExhino());
//    System.out.println("-> name: " + exhiVO.getName());
    
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      ArrayList<GalleryVO> list = this.galleryProc.list_by_exhino(exhino); // 자식 레코드 목록 읽기
      
      for(GalleryVO galleryVO : list) { // 자식 레코드 관련 파일 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 시작
        // -------------------------------------------------------------------
        String file1saved = galleryVO.getFile1saved();
        String thumb1 = galleryVO.getThumb1();
        
        String uploadDir = Gallery.getUploadDir();
        Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
        Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료
        // -------------------------------------------------------------------
      }
      
      this.galleryProc.delete_by_exhino(exhino); // 자식 레코드 삭제     
            
      int cnt = this.exhiProc.delete(exhino); // 카테고리 삭제
      
      if (cnt == 1) {
        mav.setViewName("redirect:/exhi/list_all.do");       // 자동 주소 이동, Spring 재호출
        
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/exhi/msg"); // /WEB-INF/views/exhi/msg.jsp
      }
      
      mav.addObject("cnt", cnt);
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
  
  /**
   * 출력 순서 올림(상향, 10 등 -> 1 등), seqno: 10 -> 1
   * http://localhost:/exhi/update_seqno_decrease.do?exhino=1
   * http://localhost:/exhi/update_seqno_decrease.do?exhino=2
   * @param exhino
   * @return
   */
  @RequestMapping(value = "/exhi/update_seqno_decrease.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_decrease(int exhino) {
    ModelAndView mav = new ModelAndView();
    
    // seqno 컬럼의 값이 1 초과(1<)일때만 감소를 할 수 있다.
    // 1) 특정 exhino에 해당하는 seqno 컬럼의 값을 알고 싶다. -> 출력하세요.
    ExhiVO exhiVO = this.exhiProc.read(exhino);
    int seqno = exhiVO.getSeqno();
    System.out.println("-> exhino: " + exhino + " seqno: " + seqno);

    // 2) if 문을 이용한 분기
    if (seqno > 1) {
      int cnt = this.exhiProc.update_seqno_decrease(exhino); 
      mav.addObject("cnt", cnt);
      
      if (cnt == 1) {
        mav.setViewName("redirect:/exhi/list_all.do");

      } else {
        mav.addObject("code", "update_seqno_decrease_fail");
        mav.setViewName("/exhi/msg"); 
      }
      
    } else {
      mav.setViewName("redirect:/exhi/list_all.do");
    }
    
    return mav;
  }
  
  /**
   * 출력 순서 내림(상향, 1 등 -> 10 등), seqno: 1 -> 10
   * http://localhost:/exhi/update_seqno_increase.do?exhino=1
   * http://localhost:/exhi/update_seqno_increase.do?exhino=2
   * @param exhino
   * @return
   */
  @RequestMapping(value = "/exhi/update_seqno_increase.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_increase(int exhino) {
    ModelAndView mav = new ModelAndView();
    int cnt = this.exhiProc.update_seqno_increase(exhino); 
    mav.addObject("cnt", cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/exhi/list_all.do");

    } else {
      mav.addObject("code", "update_seqno_increase_fail");
      mav.setViewName("/exhi/msg"); 
    }
    
    return mav;
  }

  /**
   * 공개
   * http://localhost:/exhi/update_visible_y.do?exhino=1
   * @param exhino
   * @return
   */
  @RequestMapping(value = "/exhi/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int exhino) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/exhi/list_all.do");
    
    this.exhiProc.update_visible_y(exhino);
    
    return mav;
  }
  
  /**
   * 비공개
   * http://localhost:/exhi/update_visible_n.do?exhino=1
   * @param exhino
   * @return
   */
  @RequestMapping(value = "/exhi/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int exhino) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/exhi/list_all.do");
    
    this.exhiProc.update_visible_n(exhino);
    
    return mav;
  }
  
  /**
   * 글수 증가
   * http://localhost:/exhi/update_cnt_add.do?exhino=1
   * @param exhino
   * @return 변경된 레코드 수
   */
  @RequestMapping(value = "/exhi/update_cnt_add.do", method = RequestMethod.GET)
  public ModelAndView update_cnt_add(int exhino) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/exhi/list_all.do");
    
    this.exhiProc.update_cnt_add(exhino);
    
    return mav;
  }
  
  /**
   * 글수 감소
   * http://localhost:/exhi/update_cnt_sub.do?exhino=1
   * @param exhino
   * @return
   */  
  @RequestMapping(value = "/exhi/update_cnt_sub.do", method = RequestMethod.GET)
  public ModelAndView update_cnt_sub(int exhino) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/exhi/list_all.do");
    
    this.exhiProc.update_cnt_sub(exhino);
    
    return mav;
  }
  
}





