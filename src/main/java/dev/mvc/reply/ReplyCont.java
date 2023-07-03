package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import dev.mvc.member.*;
import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberVO;

@Controller
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc") // 이름 지정
  private ReplyProcInter replyProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // 이름 지정
  private AdminProcInter adminProc;
  
  public ReplyCont(){
    System.out.println("--> ReplyCont created.");
  }
  
  /**
   * 댓글 등록 처리
   * @param replyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/create.do",
                            method = RequestMethod.POST,
                            produces = "text/plain;charset=UTF-8")
  public String create(ReplyVO replyVO) {
    int cnt = replyProc.create(replyVO);
    
    JSONObject obj = new JSONObject();
    obj.put("cnt",cnt);
 
    return obj.toString(); // {"cnt":1}

  }
  
//  /**
//   * 관리자만 목록 확인 가능
//   * @param session
//   * @return
//   */
//  @RequestMapping(value="/reply/list.do", method=RequestMethod.GET)
//  public ModelAndView list(HttpSession session) {
//    ModelAndView mav = new ModelAndView();
//    
//    if (adminProc.isAdmin(session)) {
//      List<ReplyVO> list = replyProc.list();
//      
//      mav.addObject("list", list);
//      mav.setViewName("/reply/list"); // /webapp/reply/list.jsp
//
//    } else {
//      mav.setViewName("redirect:/admin/login_need.jsp"); // /webapp/admin/login_need.jsp
//    }
//    
//    return mav;
//  }

  /**
   * 관리자만 목록 확인 가능
   * @param session
   * @return
   */
  @RequestMapping(value = "/reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (adminProc.isAdmin(session)) {
      List<ReplyMemberVO> list = this.replyProc.list();
      
      mav.addObject("list", list);
      mav.setViewName("/reply/list_join"); // /WEB-INF/views/reply/list_join.jsp

    } else {
      mav.setViewName("redirect:/admin/login_need.jsp"); // Redirect to the appropriate login page
    }
    
    return mav;
  }
  
  @RequestMapping(value = "/reply/member_list.do", method = RequestMethod.GET)
  public ModelAndView member_list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (session.getAttribute("memberno") != null) {
      List<ReplyMemberVO> list = replyProc.list_member_join((int)session.getAttribute("memberno"));
      
      mav.addObject("list", list);
      mav.setViewName("/reply/member_list_join"); // /WEB-INF/views/reply/list_join.jsp

    } else {
      mav.setViewName("redirect:/member/login_need.jsp"); // Redirect to the appropriate login page
    }
    
    return mav;
  }


  
  /**
   <xmp>
   http://localhost:9090/ojt/reply/list_by_galleryno.do?galleryno=1
   글이 없는 경우: {"list":[]}
   글이 있는 경우
   {"list":[
            {"memberno":1,"rdate":"2019-12-18 16:46:43","passwd":"123","replyno":3,"content":"댓글 3","galleryno":1}
            ,
            {"memberno":1,"rdate":"2019-12-18 16:46:39","passwd":"123","replyno":2,"content":"댓글 2","galleryno":1}
            ,
            {"memberno":1,"rdate":"2019-12-18 16:46:35","passwd":"123","replyno":1,"content":"댓글 1","galleryno":1}
            ] 
   }
   </xmp>  
   * @param galleryno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/list_by_galleryno.do",
                            method = RequestMethod.GET,
                            produces = "text/plain;charset=UTF-8")
  public String list_by_galleryno(int galleryno) {
    List<ReplyVO> list = replyProc.list_by_galleryno(galleryno);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString(); 

  }
  
  /**
   컨텐츠별 댓글 목록 
   {
   "list":[
            {
              "memberno":1,
              "rdate":"2019-12-18 16:46:35",
               "passwd":"123",
              "replyno":1,
              "id":"user1",
               "content":"댓글 1",
              "galleryno":1
            }
            ,
            {
              "memberno":1,
              "rdate":"2019-12-18 16:46:35",
              "passwd":"123",
              "replyno":1,
              "id":"user1",
              "content":"댓글 1",
              "galleryno":1
            }
          ]
   }
   * http://localhost:9090/resort/reply/list_by_galleryno_join.do?galleryno=53
   * @param galleryno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/list_by_galleryno_join.do",
                              method = RequestMethod.GET,
                              produces = "text/plain;charset=UTF-8")
  public String list_by_galleryno_join(int galleryno) {
    // String msg="JSON 출력";
    // return msg;
    
    List<ReplyMemberVO> list = replyProc.list_by_galleryno_join(galleryno);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString();     
  }
  
  /**
   * 패스워드를 검사한 후 삭제 
   * http://localhost:9090/resort/reply/delete.do?replyno=1&passwd=1234
   * {"delete_cnt":0,"passwd_cnt":0}
   * {"delete_cnt":1,"passwd_cnt":1}
   * @param replyno
   * @param passwd
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/delete.do", 
                              method = RequestMethod.POST,
                              produces = "text/plain;charset=UTF-8")
  public String delete(int replyno, String passwd) {
//    System.out.println("-> replyno: " + replyno);
//    System.out.println("-> passwd:" + passwd); 
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("replyno", replyno);
    map.put("passwd", passwd);
    
    int passwd_cnt = replyProc.checkPasswd(map); // 패스워드 일치 여부, 1: 일치, 0: 불일치
    int delete_cnt = 0;                                    // 삭제된 댓글
    if (passwd_cnt == 1) { // 패스워드가 일치할 경우
      delete_cnt = replyProc.delete(replyno); // 댓글 삭제
    }
    
    JSONObject obj = new JSONObject();
    obj.put("passwd_cnt", passwd_cnt); // 패스워드 일치 여부, 1: 일치, 0: 불일치
    obj.put("delete_cnt", delete_cnt); // 삭제된 댓글
    
    return obj.toString();
  }
  
  /**
   * 관리자가 댓글 삭제 
   * http://localhost:9090/resort/reply/delete.do?replyno=1&passwd=1234
   * {"delete_cnt":0,"passwd_cnt":0}
   * {"delete_cnt":1,"passwd_cnt":1}
   * @param replyno
   * @param passwd
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/delete.do", 
                              method = RequestMethod.GET,
                              produces = "text/plain;charset=UTF-8")
   public ModelAndView delete(int replyno) {
      ModelAndView mav = new ModelAndView();
//    System.out.println("-> replyno: " + replyno);
//    System.out.println("-> passwd:" + passwd);    
      int delete_cnt = 0;                                    // 삭제된 댓글      
      delete_cnt = replyProc.delete(replyno); // 댓글 삭제      
      mav.setViewName("redirect:/reply/list.do");
      
      return mav;
  }
  
  
  
  
  
  /**
   * 더보기 버튼 페이징 목록
   * http://localhost:9090/resort/reply/list_by_galleryno_join_add.do?galleryno=53&replyPage=1
   * @param galleryno 댓글 부모글 번호
   * @param replyPage 댓글 페이지
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/list_by_galleryno_join_add.do",
                              method = RequestMethod.GET,
                              produces = "text/plain;charset=UTF-8")
  public String list_by_galleryno_join(int galleryno, int replyPage) {
  //    System.out.println("galleryno: " + galleryno);
  //    System.out.println("replyPage: " + replyPage);
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("galleryno", galleryno); 
    map.put("replyPage", replyPage);    
    
    List<ReplyMemberVO> list = replyProc.list_by_galleryno_join_add(map);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString();     
  }
  
}

