package dev.mvc.jjim;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.gallery.GalleryProcInter;
import dev.mvc.gallery.GalleryVO;

@Controller
public class JjimCont {

  @Autowired
  @Qualifier("dev.mvc.jjim.JjimProc")
  private JjimProcInter jjimProc;

  @Autowired
  @Qualifier("dev.mvc.gallery.GalleryProc")
  private GalleryProcInter galleryProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  public JjimCont() {
    System.out.println("-> JjimCont created");
  }

  /**
   * 좋아요 생성 및 삭제
   * @param session
   * @param recomVO
   * @param recipeVO
   * @return
   */
  @RequestMapping(value = "/jjim/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, JjimVO jjimVO, GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();

    if (memberProc.isMember(session)) {
      int memberno = (int) (session.getAttribute("memberno"));
      jjimVO.setMemberno(memberno);

      int check = this.jjimProc.check(jjimVO);

      if (check != 1) {
        int cnt = this.jjimProc.create(jjimVO);

        if (cnt == 1) {
          mav.addObject("galleryVO", galleryVO);
          this.galleryProc.jjim_add(galleryVO.getGalleryno());
          mav.setViewName("redirect:/gallery/read.do?galleryno=" + galleryVO.getGalleryno());

        } else {
          mav.addObject("code", "create_fail");

        }
      } else {
        int delete_jjim = this.jjimProc.delete(memberno);
        
        mav.addObject("galleryVO", galleryVO);
        this.galleryProc.jjim_sub(galleryVO.getGalleryno());
        mav.setViewName("redirect:/gallery/read.do?galleryno=" + galleryVO.getGalleryno());
        
      }
    } else if (adminProc.isAdmin(session)) {
      mav.addObject("code", "admin_fail");
      mav.setViewName("redirect:/jjim/msg.do");
      
    } else {
      mav.addObject("url", "/member/login_need");
      mav.setViewName("redirect:/jjim/msg.do");

    }

    return mav;
  }

  /**
   * 오류 메시지
   * @param url
   * @return
   */
  @RequestMapping(value = "/jjim/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward

    return mav; // forward
  }
  
  
  

}