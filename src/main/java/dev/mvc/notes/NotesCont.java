package dev.mvc.notes;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.function.Function;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
import dev.mvc.gallery.*;
import dev.mvc.member.*;
import dev.mvc.jjim.JjimProcInter;
import dev.mvc.jjim.JjimVO;
import dev.mvc.exhi.ExhiProcInter;
import dev.mvc.exhi.ExhiVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class NotesCont {

  @Autowired
  @Qualifier("dev.mvc.notes.NotesProc")
  private NotesProcInter notesProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public NotesCont() {
    System.out.println("NotesCont created");
  }
  // 공지사항 등록 폼
  // http://localhost:9093/notes/create.do
  @RequestMapping(value = "/notes/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    // public ModelAndView create(HttpServletRequest request, int cateno) {
    ModelAndView mav = new ModelAndView();

    if (this.memberProc.isAdmin(session)) {
      mav.setViewName("/notes/create"); // /webapp/WEB-INF/views/notes/create.jsp
    } else {
      mav.setViewName("/member/admin_login_need");
    }
    
    return mav;
  }

  // 공지사항 등록 처리
  @RequestMapping(value = "/notes/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, NotesVO notesVO) {

    ModelAndView mav = new ModelAndView();

    if (this.memberProc.isAdmin(session)) { //관리자일경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // preview image

      String upDir =  Notes.getUploadDir();
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = notesVO.getFile1MF();
      
      file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
      System.out.println("-> file1: " + file1);
      
      long size1 = mf.getSize();  // 파일 크기
      
      if (size1 > 0) { // 파일 크기 체크
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
        }
        
      }    
      
      notesVO.setFile1(file1);   // 순수 원본 파일명
      notesVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      notesVO.setThumb1(thumb1);      // 원본이미지 축소판
      notesVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int memberno = (int)session.getAttribute("memberno"); // memberno FK
      notesVO.setMemberno(memberno);
      int cnt = this.notesProc.create(notesVO); 
      
      // ------------------------------------------------------------------------------
      // PK의 return
      // ------------------------------------------------------------------------------
      // System.out.println("--> galleryno: " + galleryVO.getContentsno());
      // mav.addObject("galleryno", galleryVO.getContentsno()); // redirect parameter 적용
      // ------------------------------------------------------------------------------

      mav.addObject("now_page", notesVO.getNow_page());
      mav.setViewName("redirect:/notes/list_all_search_paging.do");
      
    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/member/admin_login_need.jsp
    }

    return mav;
  }

   // 공지사항 topview 리스트 조회
   //http://localhost:9093/notes/list_all.do
   @RequestMapping(value="/notes/list_all.do", method=RequestMethod.GET)
   public ModelAndView list_all() {
     ModelAndView mav = new ModelAndView();

     ArrayList<NotesVO> list = this.notesProc.list_all();
     mav.addObject("list", list);
     
     mav.setViewName("/notes/list_all"); // /WEB-INF/views/notes/list_all.jsp
     return mav;
   }
   
   
   /**
    * 목록 + 검색 + 페이징 지원
    * http://localhost:9093/notes/list_all_search_paging.do?word=스위스&now_page=1
    * 
    * @param word
    * @param now_page
    * @return
    */
   @RequestMapping(value = "/notes/list_all_search_paging.do", method = RequestMethod.GET)
   public ModelAndView list_all_search_paging(NotesVO notesVO) {
     ModelAndView mav = new ModelAndView();
     
     // 검색된 전체 글 수
     int search_count = this.notesProc.search_count(notesVO);
     mav.addObject("search_count", search_count);
     
     // 검색 목록: 검색된 레코드를 페이지 단위로 분할하여 가져옴
     ArrayList<NotesVO> list = notesProc.list_all_search_paging(notesVO); 
     mav.addObject("list", list);
     
     if (notesVO.getNow_page() == 1) {
     // 목록 : topview = 'Y'인 리스트 가져옴
     ArrayList<NotesVO> list_t = notesProc.list_all(); 
     mav.addObject("list_t", list_t);
     }
     /*
      * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
      * 18 19 20 [다음]
      * @param cateno 카테고리번호
      * @param now_page 현재 페이지
      * @param word 검색어
      * @return 페이징용으로 생성된 HTML/CSS tag 문자열
      */
     String paging = notesProc.pagingBox(notesVO.getNow_page(), notesVO.getWord(), "list_all_search_paging.do");
     mav.addObject("paging", paging);

     // mav.addObject("now_page", now_page);
     
     mav.setViewName("/notes/list_all_search_paging");  // /gallery/list_by_cateno_search_paging.jsp

     return mav;
   }
   
   // 공지사항 조회
   @RequestMapping(value="/notes/read.do", method=RequestMethod.GET )
   public ModelAndView read(int notesno) {
     ModelAndView mav = new ModelAndView();

     NotesVO notesVO = this.notesProc.read(notesno);
     
     String title = notesVO.getTitle();
     String content = notesVO.getContent();
     
     title = Tool.convertChar(title);  // 특수 문자 처리
     content = Tool.convertChar(content); 
     
     notesVO.setTitle(title);
     notesVO.setContent(content);  
     
     long size1 = notesVO.getSize1();
     notesVO.setSize1_label(Tool.unit(size1));    
     
     mav.addObject("notesVO", notesVO); // request.setAttribute("notesVO", notesVO);
     
     // 회원 번호: admino -> AdminVO -> name
     String name = this.memberProc.read(notesVO.getMemberno()).getMname();
     mav.addObject("name", name);

     mav.setViewName("/notes/read"); // /WEB-INF/views/notes/read.jsp
         
     return mav;
   }
   
   // 공지사항 글 수정 폼
   @RequestMapping(value = "/notes/update_text.do", method = RequestMethod.GET)
   public ModelAndView update_text(HttpSession session, int notesno) {
     ModelAndView mav = new ModelAndView();
     
     NotesVO notesVO = this.notesProc.read(notesno);
     mav.addObject("notesVO", notesVO);
     
     if (this.memberProc.isAdmin(session)) { //관리자일 경우에
     mav.setViewName("/notes/update_text"); // /WEB-INF/views/gallery/update_text.jsp
     // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
     // mav.addObject("content", content);
     } else {
       mav.setViewName("/member/admin_login_need"); // /WEB-INF/views/member/admin_login_need.jsp
     }

     return mav; // forward
   }

   // 수정 처리
   @RequestMapping(value = "/notes/update_text.do", method = RequestMethod.POST)
   public ModelAndView update_text(HttpSession session, NotesVO notesVO) {
     ModelAndView mav = new ModelAndView();
     
     if (this.memberProc.isAdmin(session)) { //관리자일 경우에
       int cnt = this.notesProc.update_text(notesVO);  
       
       mav.addObject("notesno", notesVO.getNotesno());
       mav.setViewName("redirect:/notes/read.do");
     } else { // 정상적인 로그인이 아닌 경우
       mav.setViewName("/member/admin_login_need"); // /WEB-INF/views/member/admin_login_need.jsp
     }
     
     mav.addObject("now_page", notesVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시한번 데이터 저장 ★
     
     // URL에 파라미터의 전송
     // mav.setViewName("redirect:/notes/read.do?notesno=" + notesVO.getContentsno());             
     
     return mav; // forward
   }
   
   // 파일 수정 폼
   @RequestMapping(value = "/notes/update_file.do", method = RequestMethod.GET)
   public ModelAndView update_file(HttpSession session, int notesno) {
     ModelAndView mav = new ModelAndView();
     
     if (this.memberProc.isAdmin(session)) { //관리자일 경우에
     NotesVO notesVO = this.notesProc.read(notesno);
     mav.addObject("notesVO", notesVO);
     
     mav.setViewName("/notes/update_file"); // /WEB-INF/views/gallery/update_file.jsp
     } else {
       mav.setViewName("/member/admin_login_need"); // /WEB-INF/views/member/admin_login_need.jsp
     }
     
     return mav; // forward
   }
   
   // 파일 수정 처리 
   @RequestMapping(value = "/notes/update_file.do", method = RequestMethod.POST)
   public ModelAndView update_file(HttpSession session, NotesVO notesVO) {
     ModelAndView mav = new ModelAndView();
     
     if (this.memberProc.isAdmin(session)) { //관리자일 경우에
       // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
       NotesVO notesVO_old = notesProc.read(notesVO.getNotesno());
       
       // -------------------------------------------------------------------
       // 파일 삭제 시작
       // -------------------------------------------------------------------
       String file1saved = notesVO_old.getFile1saved();  // 실제 저장된 파일명
       String thumb1 = notesVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
       long size1 = 0;
          
       String upDir =  Notes.getUploadDir(); // C:/kd/deploy/team4_v2sbm3c/notes/storage/
       
       Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
       Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 종료
       // -------------------------------------------------------------------
           
       // -------------------------------------------------------------------
       // 파일 전송 시작
       // -------------------------------------------------------------------
       String file1 = "";          // 원본 파일명 image

       // 전송 파일이 없어도 file1MF 객체가 생성됨.
       // <input type='file' class="form-control" name='file1MF' id='file1MF' 
       //           value='' placeholder="파일 선택">
       MultipartFile mf = notesVO.getFile1MF();
           
       file1 = mf.getOriginalFilename(); // 원본 파일명
       size1 = mf.getSize();  // 파일 크기
           
       if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
         // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
         file1saved = Upload.saveFileSpring(mf, upDir); 
         
         if (Tool.isImage(file1saved)) { // 이미지인지 검사
           // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
           thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
         }
         
       } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
         file1="";
         file1saved="";
         thumb1="";
         size1=0;
       }
           
       notesVO.setFile1(file1);
       notesVO.setFile1saved(file1saved);
       notesVO.setThumb1(thumb1);
       notesVO.setSize1(size1);
       // -------------------------------------------------------------------
       // 파일 전송 코드 종료
       // -------------------------------------------------------------------
           
       this.notesProc.update_file(notesVO); // Oracle 처리

       mav.addObject("notesno", notesVO.getNotesno());
       mav.setViewName("redirect:/notes/read.do"); // request -> param으로 접근 전환
                 
     } else {
       
       mav.setViewName("/member/admin_login_need"); // GET
     }

     // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
     mav.addObject("now_page", notesVO.getNow_page());
     
     return mav; // forward
   }   

   // 삭제 폼
   @RequestMapping(value="/notes/delete.do", method=RequestMethod.GET )
   public ModelAndView delete(HttpSession session, int notesno) { 
     ModelAndView mav = new  ModelAndView();
     
     if (this.memberProc.isAdmin(session)) { //관리자일 경우에
     // 삭제할 정보를 조회하여 확인
     NotesVO notesVO = this.notesProc.read(notesno);
     mav.addObject("notesVO", notesVO);
     
     mav.setViewName("/notes/delete");  // /webapp/WEB-INF/views/notes/delete.jsp
     } else {
       mav.setViewName("/member/admin_login_need");
     }
     
     return mav; 
   }

   // 삭제 처리
   @RequestMapping(value = "/notes/delete.do", method = RequestMethod.POST)
   public ModelAndView delete(HttpSession session, NotesVO notesVO) {
     ModelAndView mav = new ModelAndView();
     
     if (this.memberProc.isAdmin(session)) { //관리자일 경우에
     // -------------------------------------------------------------------
     // 파일 삭제 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     NotesVO notesVO_read = notesProc.read(notesVO.getNotesno());
         
     String file1saved = notesVO.getFile1saved();
     String thumb1 = notesVO.getThumb1();
     
     String uploadDir = Notes.getUploadDir();
     Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
     Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
     // -------------------------------------------------------------------
     // 파일 삭제 종료
     // -------------------------------------------------------------------
         
     this.notesProc.delete(notesVO.getNotesno()); // DBMS 삭제
     
    
     mav.setViewName("redirect:/notes/list_all.do"); 
     } else {
       mav.setViewName("/admin/login_need");
     }
     
     return mav;
   }   



}



