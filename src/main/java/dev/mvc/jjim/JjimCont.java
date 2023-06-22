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
import dev.mvc.exhi.ExhiVO;
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

  
  /**
   * 찜 리스트
   */
  @RequestMapping(value = "/gallery/list_by_exhino.do", method = RequestMethod.GET)
  public ModelAndView list_by_memberno_search_paging(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();

    // 검색된 전체 글 수
    int search_count = this.galleryProc.search_count(galleryVO);
    mav.addObject("search_count", search_count);
    
    // 검색 목록: 검색된 레코드를 페이지 단위로 분할하여 가져옴
    ArrayList<GalleryVO> list = galleryProc.list_by_exhino_search_paging(galleryVO);
    mav.addObject("list", list);
    // 에러 ******************
    // ExhiVO exhiVO = exhiProc.read(galleryVO.getExhino());
    //mav.addObject("exhiVO", exhiVO);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param exhino 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    String paging = galleryProc.pagingBox(galleryVO.getExhino(), galleryVO.getNow_page(), galleryVO.getWord(), "list_by_exhino.do");
    mav.addObject("paging", paging);

    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/gallery/list_by_exhino_search_paging");  // /gallery/list_by_exhino_search_paging.jsp

    return mav;
  }
  
  

}