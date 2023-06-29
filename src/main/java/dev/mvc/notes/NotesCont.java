package dev.mvc.notes;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.admin.AdminVO;
import dev.mvc.notes.NotesVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class NotesCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") 
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.notes.NotesProc") 
  private NotesProcInter notesProc;
  
  public NotesCont () {
    System.out.println("-> NotesCont created.");
  }
  
  //등록품  http://localhost:9093/notes/create.do
  @RequestMapping(value = "/notes/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session, NotesVO notesVO) {

    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session) == true) {
      mav.setViewName("/notes/create");
      
    } else {
      mav.setViewName("/admin/login_need");
    }

    return mav;
  }
 
  //등록 처리
  @RequestMapping(value = "/notes/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, NotesVO notesVO) {
    ModelAndView mav = new ModelAndView();

    if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      
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
      int adminno = (int)(session.getAttribute("adminno"));
      notesVO.setAdminno(adminno);
      
      notesVO.setFile1(file1);   // 순수 원본 파일명
      notesVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      notesVO.setThumb1(thumb1);      // 원본이미지 축소판
      notesVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int cnt = this.notesProc.create(notesVO); 
     
      if (cnt == 1) {
        mav.addObject("code", "create_success");
          // notesProc.increaseCnt(goodsVO.getNotesno()); // 글수 증가
      } else {
          mav.addObject("code", "create_fail");
      }
      
      mav.addObject("url", "/notes/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/notes/msg.do"); 

    } else {
      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    

    return mav;
  }
    /**
     * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
     * @return
     */
    @RequestMapping(value="/notes/msg.do", method=RequestMethod.GET)
    public ModelAndView msg(String url){
      ModelAndView mav = new ModelAndView();

      mav.setViewName(url); // forward
      
      return mav; // forward
    }
  
    // 공지사항 목록
    @RequestMapping(value="/notes/list_all.do", method=RequestMethod.GET)
    public ModelAndView list_all(NotesVO notesVO) {
      ModelAndView mav = new ModelAndView();
      
      ArrayList<NotesVO> list = this.notesProc.list_all();
      mav.addObject("list", list);
      
      
      mav.setViewName("/notes/list_all"); // /webapp/WEB-INF/views/notes/list_all.jsp
      
      return mav;
    }
    
    // 조회
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
      
      String mname = this.adminProc.read(notesVO.getAdminno()).getMname();
      mav.addObject("mname", mname);
      
      mav.addObject("notesVO", notesVO); // request.setAttribute("notesVO", notesVO);
    
      int cnt = this.notesProc.cnt_add(notesno);
      mav.addObject("cnt", cnt);
      
      return mav;
    }
    
    /**
     * 목록 + 검색 + 페이징 지원
     * http://localhost:9091/notes/list_all.do
     * 
     * @param notes_no
     * @param word
     * @param now_page
     * @return
     */
    @RequestMapping(value = "/notes/list_by_search.do", method = RequestMethod.GET)
    public ModelAndView list_by_search_paging(NotesVO notesVO) {

      ModelAndView mav = new ModelAndView();
      
      //검색된 전체 글 수
      int search_count = this.notesProc.search_count(notesVO);
      mav.addObject("search_count", search_count);
      
      // 검색 목록
      ArrayList<NotesVO> list = notesProc.list_by_search_paging(notesVO);
      mav.addObject("list", list);
      
      mav.addObject("notesVO", notesVO);

      /*
       * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
       * 18 19 20 [다음]
       * @param auction_no 카테고리번호
       * @param search_count 검색(전체) 레코드수
       * @param now_page 현재 페이지
       * @param word 검색어
       * @return 페이징용으로 생성된 HTML/CSS tag 문자열
       */
      String paging = notesProc.pagingBox(notesVO.getNow_page(), notesVO.getWord(), "list_by_search.do");
      mav.addObject("paging", paging);

      // mav.addObject("now_page", now_page);
      
      mav.setViewName("/notes/list_all");  

      return mav;
    }
    
    // 수정폼
    @RequestMapping(value = "/notes/update_text.do", method = RequestMethod.GET)
    public ModelAndView update_text(int notesno) {
      ModelAndView mav = new ModelAndView();
      
      NotesVO notesVO = this.notesProc.read(notesno);
      mav.addObject("notesVO", notesVO);

      
      mav.setViewName("/notes/update_text"); // /WEB-INF/views/notes/update_text.jsp
      // String article = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
      // mav.addObject("article", article);

      return mav; // forward
    }
    
    //수정처리
    @RequestMapping(value = "/notes/update_text.do", method = RequestMethod.POST)
    public ModelAndView update_text(HttpSession session, NotesVO notesVO) {
      ModelAndView mav = new ModelAndView();
      
      if(this.adminProc.isAdmin(session)) {
        this.notesProc.update_text(notesVO);
        
        mav.addObject("notesno", notesVO.getNotesno());
        mav.setViewName("redirect:/notes/read.do");
      }else {
        if(this.notesProc.password_check(notesVO) == 1) {
          this.notesProc.update_text(notesVO);

        // mav 객체 이용
        mav.addObject("notesno", notesVO.getNotesno());
        mav.setViewName("redirect:/notes/read.do");
        } else {
          mav.addObject("url", "notes/passwd_check"); 
          mav.setViewName("redirect:/notes/msg.do"); 
        }
      }
      
      mav.addObject("now_page", notesVO.getNow_page());
      
      return mav; // forward
    }
    
    // 패스워드 확인
    @RequestMapping(value="/notes/password_check.do", method=RequestMethod.GET )
    public ModelAndView password_check(NotesVO notesVO) {
      ModelAndView mav = new ModelAndView();
      
      int cnt = this.notesProc.password_check(notesVO);
      System.out.println("-> cnt:" + cnt);
      
      if(cnt == 0) {
      mav.setViewName("notes/passwd_check"); // /WEB-INF/views/notes/read.jsp
          
      }
      return mav;
    }
    
    
    //파일 수정폼
    @RequestMapping(value = "/notes/update_file.do", method = RequestMethod.GET)
    public ModelAndView update_file(int notesno) {
      ModelAndView mav = new ModelAndView();
      
      NotesVO notesVO = this.notesProc.read(notesno);
      mav.addObject("notesVO", notesVO);
      
      mav.setViewName("/notes/update_file"); // /WEB-INF/views/notes/update_file.jsp

      return mav; // forward
    }
    
    //파일 수정처리
    @RequestMapping(value = "/notes/update_file.do", method = RequestMethod.POST)
    public ModelAndView update_file(HttpSession session, NotesVO notesVO) {
      ModelAndView mav = new ModelAndView();
      
      if (this.adminProc.isAdmin(session)) {
        // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
        NotesVO notesVO_old = notesProc.read(notesVO.getNotesno());
        
        // -------------------------------------------------------------------
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        String file1saved = notesVO_old.getFile1saved();  // 실제 저장된 파일명
        String thumb1 = notesVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
        long size1 = 0;
           
        // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/notes/storage/
        // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notes/storage/"; // 절대 경로
        String upDir = Notes.getUploadDir(); // C:\\kd\\deploy\\resort_v2sbm3c\\notes\\storage\\
        
        Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
        Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료 시작
        // -------------------------------------------------------------------
            
        // -------------------------------------------------------------------
        // 파일 전송 코드 시작
        // -------------------------------------------------------------------
        String file1 = "";          // 원본 파일명 image

        // 완성된 경로 C:/kd/ws_java/resort_v1sbm3c/src/main/resources/static/notes/storage/
        // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/notes/storage/"; // 절대 경로
            
        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="파일 선택">
        MultipartFile mf = notesVO.getFile1MF();
            
        file1 = mf.getOriginalFilename(); // 원본 파일명
        size1 = mf.getSize();  // 파일 크기
            
        if (size1 > 0) { // 파일 크기 체크
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
        mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
        mav.setViewName("redirect:/notes/msg.do"); // GET
      }
      
      // redirect 하게 되면 데이터가 삭제됨
      mav.addObject("now_page", notesVO.getNow_page());

      return mav; // forward
    }   
    
    /**
     * 삭제 폼
     * @param notesno
     * @return
     */
    @RequestMapping(value="/notes/delete.do", method=RequestMethod.GET )
    public ModelAndView delete(int notesno) { 
      ModelAndView mav = new  ModelAndView();
      
      // 삭제할 정보를 조회하여 확인
      NotesVO notesVO = this.notesProc.read(notesno);
      mav.addObject("notesVO", notesVO);

      
      mav.setViewName("/notes/delete");  // /webapp/WEB-INF/views/notes/delete.jsp
      
      return mav; 
    }
    
    /**
     * 삭제 처리 http://localhost:9091/notes/delete.do
     * 
     * @return
     */
    @RequestMapping(value = "/notes/delete.do", method = RequestMethod.POST)
    public ModelAndView delete(NotesVO notesVO) {
      ModelAndView mav = new ModelAndView();
      
      // -------------------------------------------------------------------
      // 파일 삭제 코드 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      NotesVO notesVO_read = notesProc.read(notesVO.getNotesno());
          
      String file1saved = notesVO.getFile1saved();
      String thumb1 = notesVO.getThumb1();
         
      String uploadDir = Notes.getUploadDir();
      Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
      Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료 시작
      // -------------------------------------------------------------------
          
      this.notesProc.delete(notesVO.getNotesno()); // DBMS 삭제

      mav.setViewName("redirect:/notes/list_all.do"); 


      
      return mav;
    }   
    

     //조회수
    
     @RequestMapping(value = "/notes/cnt_add.do", method = RequestMethod.GET)
     public ModelAndView cnt_add(int notesno) {
    
       ModelAndView mav = new ModelAndView();
    
       int cnt = this.notesProc.cnt_add(notesno);
    
       if (cnt == 1) {
    
         mav.setViewName("redirect:/index.do");
       } else {
    
         mav.addObject("code", "cnt_add_fail");
         mav.setViewName("/notes/msg");
       }
    
       mav.addObject("cnt", cnt);
    
       return mav;
   }
     
    
  
}
  