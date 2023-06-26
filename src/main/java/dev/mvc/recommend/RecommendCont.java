package dev.mvc.recommend;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.exhi.ExhiProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.gallery.GalleryProcInter;
import dev.mvc.gallery.GalleryVO;

@Controller
public class RecommendCont {
    
    @Autowired
    @Qualifier("dev.mvc.recommend.RecommendProc")
    private RecommendProcInter recommendProc;
    
    @Autowired
    @Qualifier("dev.mvc.exhi.ExhiProc")
    private ExhiProcInter exhiProc;

    @Autowired
    @Qualifier("dev.mvc.gallery.GalleryProc")
    private GalleryProcInter galleryProc;
    
    @RequestMapping(value = "/recommend.do", method = RequestMethod.GET)
    public ModelAndView recommend(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        int memberno = (int) session.getAttribute("memberno");
        int exhino = this.recommendProc.recommend_read(memberno);
        if( exhino != 0) {          
            mav.addObject("exhino", exhino);
            ArrayList<RecommendVO> list = this.recommendProc.recommend(exhino);
            mav.addObject("list", list);
            mav.setViewName("/index"); // JSP 페이지 설정
        } else {
            mav.setViewName("/index");
        }
        return mav;
    }

}