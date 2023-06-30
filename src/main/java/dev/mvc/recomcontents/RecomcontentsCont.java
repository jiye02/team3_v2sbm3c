package dev.mvc.recomcontents;

import java.util.ArrayList;

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
import dev.mvc.recomplace.RecomplaceProcInter;
import dev.mvc.recomplace.RecomplaceVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class RecomcontentsCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") 
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.recomplace.RecomplaceProc") 
  private RecomplaceProcInter recomplaceProc;
  
  @Autowired
  @Qualifier("dev.mvc.recomcontents.RecomcontentsProc") 
  private RecomcontentsProcInter recomcontentsProc;
  
  
  public RecomcontentsCont () {
    System.out.println("-> RecomcontentsCont created.");
  }
  
//등록 폼, contents 테이블은 FK로 cateno를 사용함.
 // http://localhost:9091/contents/create.do  X
 // http://localhost:9091/contents/create.do?cateno=1
 // http://localhost:9091/contents/create.do?cateno=2
 // http://localhost:9091/contents/create.do?cateno=3
 @RequestMapping(value="/recomcontents/create.do", method = RequestMethod.GET)
 public ModelAndView create(int recno) {
// public ModelAndView create(HttpServletRequest request,  int cateno) {
   ModelAndView mav = new ModelAndView();

   RecomplaceVO recomplaceVO = this.recomplaceProc.read(recno); // create.jsp에 카테고리 정보를 출력하기위한 목적
   mav.addObject("recomplaceVO", recomplaceVO);
//   request.setAttribute("cateVO", cateVO);
   
   mav.setViewName("/recomcontents/create"); // /webapp/WEB-INF/views/contents/create.jsp
   
   return mav;
 }
 
 /**
  * 등록 처리 http://localhost:9093/recomcontents/create.do
  * 
  * @return
  */
 @RequestMapping(value = "/recomcontents/create.do", method = RequestMethod.POST)
 public ModelAndView create(HttpServletRequest request, HttpSession session, RecomcontentsVO recomcontentsVO) {
   ModelAndView mav = new ModelAndView();
   
   if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
     // ------------------------------------------------------------------------------
     // 파일 전송 코드 시작
     // ------------------------------------------------------------------------------
     String file1 = "";          // 원본 파일명 image
     String file1saved = "";   // 저장된 파일명, image
     String thumb1 = "";     // preview image

     String upDir =  Recomcontents.getUploadDir();
     System.out.println("-> upDir: " + upDir);
     
     // 전송 파일이 없어도 file1MF 객체가 생성됨.
     // <input type='file' class="form-control" name='file1MF' id='file1MF' 
     //           value='' placeholder="파일 선택">
     MultipartFile mf = recomcontentsVO.getFile1MF();
     
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
     
     recomcontentsVO.setFile1(file1);   // 순수 원본 파일명
     recomcontentsVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
     recomcontentsVO.setThumb1(thumb1);      // 원본이미지 축소판
     recomcontentsVO.setSize1(size1);  // 파일 크기
     // ------------------------------------------------------------------------------
     // 파일 전송 코드 종료
     // ------------------------------------------------------------------------------
     
     // Call By Reference: 메모리 공유, Hashcode 전달
     int adminno = (int)session.getAttribute("adminno"); // adminno FK
     recomcontentsVO.setAdminno(adminno);
     int cnt = this.recomcontentsProc.create(recomcontentsVO); 
     
     // ------------------------------------------------------------------------------
     // PK의 return
     // ------------------------------------------------------------------------------
     // System.out.println("--> contentsno: " + contentsVO.getContentsno());
     // mav.addObject("contentsno", contentsVO.getContentsno()); // redirect parameter 적용
     // ------------------------------------------------------------------------------
     
     if (cnt == 1) {
       this.recomplaceProc.update_cnt_add(recomcontentsVO.getRecno()); // cate 테이블 글 수 증가
       mav.addObject("code", "create_success");
       // cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
     } else {
       mav.addObject("code", "create_fail");
     }
     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     
     // System.out.println("--> cateno: " + contentsVO.getCateno());
     // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
     mav.addObject("recno", recomcontentsVO.getRecno()); // redirect parameter 적용
     
     mav.addObject("url", "/recomcontents/msg"); // msg.jsp, redirect parameter 적용
     mav.setViewName("redirect:/recomcontents/msg.do"); 

   } else {
     mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
     mav.setViewName("redirect:/recomcontents/msg.do"); 
   }
   
     return mav; // forward
   }
 
 
   /**
    * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
    * @return
    */
   @RequestMapping(value="/recomcontents/msg.do", method=RequestMethod.GET)
   public ModelAndView msg(String url){
     ModelAndView mav = new ModelAndView();
  
     mav.setViewName(url); // forward
     
     return mav; // forward
   }
   
   /**
    * 모든 카테고리의 등록된 글목록, http://localhost:9093/recomcontents/list_all.do
    * @return
    */
   @RequestMapping(value="/recomcontents/list_all.do", method=RequestMethod.GET)
   public ModelAndView list_all() {
     ModelAndView mav = new ModelAndView();
     
     ArrayList<RecomcontentsVO> list = this.recomcontentsProc.list_all();
     mav.addObject("list", list);
     
     mav.setViewName("/recomcontents/list_all"); // /webapp/WEB-INF/views/contents/list_all.jsp
     
     return mav;
   }
   
   /**
    * 조회
    * @return
    */
   @RequestMapping(value="/recomcontents/read.do", method=RequestMethod.GET )
   public ModelAndView read(int recontno) {
     ModelAndView mav = new ModelAndView();
  
     RecomcontentsVO recomcontentsVO = this.recomcontentsProc.read(recontno);
     
     String title = recomcontentsVO.getTitle();
     String content = recomcontentsVO.getContent();
     
     title = Tool.convertChar(title);  // 특수 문자 처리
     content = Tool.convertChar(content); 
     
     recomcontentsVO.setTitle(title);
     recomcontentsVO.setContent(content);  
     
     long size1 = recomcontentsVO.getSize1();
     recomcontentsVO.setSize1_label(Tool.unit(size1));    
     
     mav.addObject("recomcontentsVO", recomcontentsVO); // request.setAttribute("contentsVO", contentsVO);
  
     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recomcontentsVO.getRecno()); // 그룹 정보 읽기
     mav.addObject("recomplaceVO", recomplaceVO);
     
     // 회원 번호: admino -> AdminVO -> mname
     String mname = this.adminProc.read(recomcontentsVO.getAdminno()).getMname();
     mav.addObject("mname", mname);
  
     mav.setViewName("/recomcontents/read"); // /WEB-INF/views/contents/read.jsp
         
     return mav;
   }
   
   /**
    * 맵 등록/수정/삭제 폼
    * http://localhost:9091/contents/map.do?contentsno=1
    * @return
    */
   @RequestMapping(value="/recomcontents/map.do", method=RequestMethod.GET )
   public ModelAndView map(int recontno) {
     ModelAndView mav = new ModelAndView();

     RecomcontentsVO recomcontentsVO = this.recomcontentsProc.read(recontno); // map 정보 읽어 오기
     mav.addObject("recomcontentsVO", recomcontentsVO); // request.setAttribute("contentsVO", contentsVO);

     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recomcontentsVO.getRecno()); // 그룹 정보 읽기
     mav.addObject("recomplaceVO", recomplaceVO); 

     mav.setViewName("/recomcontents/map"); // /WEB-INF/views/contents/map.jsp
         
     return mav;
   }
   
   /**
    * MAP 등록/수정/삭제 처리
    * http://localhost:9091/contents/map.do
    * @param contentsVO
    * @return
    */
   @RequestMapping(value="/recomcontents/map.do", method = RequestMethod.POST)
   public ModelAndView map_update(RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();
     
     this.recomcontentsProc.map(recomcontentsVO);
     
     mav.setViewName("redirect:/recomcontents/read.do?recontno=" + recomcontentsVO.getRecontno()); 
     // /webapp/WEB-INF/views/contents/read.jsp
     
     return mav;
   }
   
   /**
    * Youtube 등록/수정/삭제 폼
    * http://localhost:9091/contents/youtube.do?contentsno=1
    * @return
    */
   @RequestMapping(value="/recomcontents/youtube.do", method=RequestMethod.GET )
   public ModelAndView youtube(int recontno) {
     ModelAndView mav = new ModelAndView();

     RecomcontentsVO recomcontentsVO = this.recomcontentsProc.read(recontno); // map 정보 읽어 오기
     mav.addObject("recomcontentsVO", recomcontentsVO); // request.setAttribute("contentsVO", contentsVO);

     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recomcontentsVO.getRecno()); // 그룹 정보 읽기
     mav.addObject("recomplaceVO", recomplaceVO); 

     mav.setViewName("/recomcontents/youtube"); // /WEB-INF/views/contents/youtube.jsp
         
     return mav;
   }
   
   /**
    * Youtube 등록/수정/삭제 처리
    * http://localhost:9091/contents/map.do
    * @param contentsVO
    * @return
    */
   @RequestMapping(value="/recomcontents/youtube.do", method = RequestMethod.POST)
   public ModelAndView youtube_update(RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();
     
     if (recomcontentsVO.getYoutube().trim().length() > 0) { // 삭제 중인지 확인, 삭제가 아니면 youtube 크기 변경
      
       String youtube = Tool.youtubeResize(recomcontentsVO.getYoutube());
       recomcontentsVO.setYoutube(youtube);
     }
     
     this.recomcontentsProc.youtube(recomcontentsVO);

     // youtube 크기 조절
     // <iframe width="1019" height="573" src="https://www.youtube.com/embed/Aubh5KOpz-4" title="교보문고에서 가장 잘나가는 일본 추리소설 베스트셀러 10위부터 1위까지 소개해드려요📚" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
     
     
     mav.setViewName("redirect:/recomcontents/read.do?recontno=" + recomcontentsVO.getRecontno()); 
     // /webapp/WEB-INF/views/contents/read.jsp
     
     return mav;
   }
   
   /**
    * 목록 + 검색 + 페이징 지원
    * http://localhost:9091/contents/list_by_cateno.do?cateno=1&word=스위스&now_page=1
    * 
    * @param cateno
    * @param word
    * @param now_page
    * @return
    */
   @RequestMapping(value = "/recomcontents/list_by_recno.do", method = RequestMethod.GET)
   public ModelAndView list_by_recno_search_paging(RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();

     // 검색된 전체 글 수
     int search_count = this.recomcontentsProc.search_count(recomcontentsVO);
     mav.addObject("search_count", search_count);
     
     // 검색 목록: 검색된 레코드를 페이지 단위로 분할하여 가져옴
     ArrayList<RecomcontentsVO> list = recomcontentsProc.list_by_recno_search_paging(recomcontentsVO);
     mav.addObject("list", list);
     
     RecomplaceVO recomplaceVO = recomplaceProc.read(recomcontentsVO.getRecno());
     mav.addObject("recomplaceVO", recomplaceVO);

     /*
      * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
      * 18 19 20 [다음]
      * @param cateno 카테고리번호
      * @param now_page 현재 페이지
      * @param word 검색어
      * @return 페이징용으로 생성된 HTML/CSS tag 문자열
      */
     String paging = recomcontentsProc.pagingBox(recomcontentsVO.getRecno(), recomcontentsVO.getNow_page(), recomcontentsVO.getWord(), "list_by_recno.do");
     mav.addObject("paging", paging);

     // mav.addObject("now_page", now_page);
     
     mav.setViewName("/recomcontents/list_by_recno_search_paging");  // /contents/list_by_cateno_search_paging.jsp

     return mav;
   }
   
   
   /**
    * 목록 + 검색 + 페이징 + Grid(갤러리) 지원
    * http://localhost:9093/recomcontents/list_by_recno_grid.do?recno=1&word=스위스&now_page=1
    * 
    * @param cateno
    * @param word
    * @param now_page
    * @return
    */
   @RequestMapping(value = "/recomcontents/list_by_recno_grid.do", method = RequestMethod.GET)
   public ModelAndView list_by_recno_search_paging_grid(RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();

     // 검색된 전체 글 수
     int search_count = this.recomcontentsProc.search_count(recomcontentsVO);
     mav.addObject("search_count", search_count);
     
     // 검색 목록
     ArrayList<RecomcontentsVO> list = recomcontentsProc.list_by_recno_search_paging(recomcontentsVO);
     mav.addObject("list", list);

     RecomplaceVO recomplaceVO = recomplaceProc.read(recomcontentsVO.getRecno());
     mav.addObject("recomplaceVO", recomplaceVO);

     /*
      * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
      * 18 19 20 [다음]
      * @param cateno 카테고리번호
      * @param now_page 현재 페이지
      * @param word 검색어
      * @return 페이징용으로 생성된 HTML/CSS tag 문자열
      */
     String paging = recomcontentsProc.pagingBox(recomcontentsVO.getRecno(), recomcontentsVO.getNow_page(), recomcontentsVO.getWord(), "list_by_recno_grid.do");
     mav.addObject("paging", paging);

     // mav.addObject("now_page", now_page);
     
     mav.setViewName("/recomcontents/list_by_recno_search_paging_grid");  // /contents/list_by_cateno_search_paging_grid.jsp

     return mav;
   }
   
   
   
   /**
    * 수정 폼
    * http://localhost:9091/contents/update_text.do?contentsno=1
    * 
    * @return
    */
   @RequestMapping(value = "/recomcontents/update_text.do", method = RequestMethod.GET)
   public ModelAndView update_text(int recontno) {
     ModelAndView mav = new ModelAndView();
     
     RecomcontentsVO recomcontentsVO = this.recomcontentsProc.read(recontno);
     mav.addObject("recomcontentsVO", recomcontentsVO);
     
     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recomcontentsVO.getRecno());
     mav.addObject("recomplaceVO", recomplaceVO);
     
     mav.setViewName("/recomcontents/update_text"); // /WEB-INF/views/contents/update_text.jsp
     // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
     // mav.addObject("content", content);

     return mav; // forward
   }
   
   /**
    * 수정 처리
    * http://localhost:9091/contents/update_text.do?contentsno=1
    * 
    * @return
    */
   @RequestMapping(value = "/recomcontents/update_text.do", method = RequestMethod.POST)
   public ModelAndView update_text(HttpSession session, RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();
     
     // System.out.println("-> word: " + contentsVO.getWord());
     
     if (this.adminProc.isAdmin(session)) { // 관리자 로그인
       this.recomcontentsProc.update_text(recomcontentsVO);  
       
       mav.addObject("recontno", recomcontentsVO.getRecontno());
       mav.addObject("recno", recomcontentsVO.getRecno());
       mav.setViewName("redirect:/recomcontents/read.do");
     } else { // 정상적인 로그인이 아닌 경우
       if (this.recomcontentsProc.password_check(recomcontentsVO) == 1) {
         this.recomcontentsProc.update_text(recomcontentsVO);  
          
         // mav 객체 이용
         mav.addObject("recontno", recomcontentsVO.getRecontno());
         mav.addObject("recno", recomcontentsVO.getRecno());
         mav.setViewName("redirect:/recomcontents/read.do");
       } else {
         mav.addObject("url", "/recomcontents/passwd_check"); // /WEB-INF/views/contents/passwd_check.jsp
         mav.setViewName("redirect:/recomcontents/msg.do");  // POST -> GET -> JSP 출력
       }    
     }
     
     mav.addObject("now_page", recomcontentsVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
     
     // URL에 파라미터의 전송
     // mav.setViewName("redirect:/contents/read.do?contentsno=" + contentsVO.getContentsno() + "&cateno=" + contentsVO.getCateno());             
     
     return mav; // forward
   }
   
   /**
    * contentsno, passwd를 GET 방식으로 전달받아 패스워드 일치 검사를하고 결과 1또는 0을 Console에 출력하세요.
    * http://localhost:9091/contents/password_check.do?contentsno=2&passwd=123
    * @return
    */
   @RequestMapping(value="/recomcontents/password_check.do", method=RequestMethod.GET )
   public ModelAndView password_check(RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();

     int cnt = this.recomcontentsProc.password_check(recomcontentsVO);
     //System.out.println("-> cnt: " + cnt);
     
     if (cnt == 0) {
       mav.setViewName("/recomcontents/passwd_check"); // /WEB-INF/views/contents/passwd_check.jsp
     }
         
     return mav;
   }
   
   
   /**
    * 파일 수정 폼
    * http://localhost:9091/contents/update_file.do?contentsno=1
    * 
    * @return
    */
   @RequestMapping(value = "/recomcontents/update_file.do", method = RequestMethod.GET)
   public ModelAndView update_file(int recontno) {
     ModelAndView mav = new ModelAndView();
     
     RecomcontentsVO recomcontentsVO = this.recomcontentsProc.read(recontno);
     mav.addObject("recomcontentsVO", recomcontentsVO);
     
     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recomcontentsVO.getRecno());
     mav.addObject("recomplaceVO", recomplaceVO);
     
     mav.setViewName("/recomcontents/update_file"); // /WEB-INF/views/contents/update_file.jsp

     return mav; // forward
   }
   
   /**
    * 파일 수정 처리 http://localhost:9091/contents/update_file.do
    * 
    * @return
    */
   @RequestMapping(value = "/recomcontents/update_file.do", method = RequestMethod.POST)
   public ModelAndView update_file(HttpSession session, RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();
     
     if (this.adminProc.isAdmin(session)) {
       // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
       RecomcontentsVO recomcontentsVO_old = recomcontentsProc.read(recomcontentsVO.getRecontno());
       
       // -------------------------------------------------------------------
       // 파일 삭제 시작
       // -------------------------------------------------------------------
       String file1saved = recomcontentsVO_old.getFile1saved();  // 실제 저장된 파일명
       String thumb1 = recomcontentsVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
       long size1 = 0;
          
       String upDir =  Recomcontents.getUploadDir(); // C:/kd/deploy/resort_v2sbm3c/contents/storage/
       
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
       MultipartFile mf = recomcontentsVO.getFile1MF();
           
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
           
       recomcontentsVO.setFile1(file1);
       recomcontentsVO.setFile1saved(file1saved);
       recomcontentsVO.setThumb1(thumb1);
       recomcontentsVO.setSize1(size1);
       // -------------------------------------------------------------------
       // 파일 전송 코드 종료
       // -------------------------------------------------------------------
           
       this.recomcontentsProc.update_file(recomcontentsVO); // Oracle 처리

       mav.addObject("recontno", recomcontentsVO.getRecontno());
       mav.addObject("recno", recomcontentsVO.getRecno());
       mav.setViewName("redirect:/recomcontents/read.do"); // request -> param으로 접근 전환
                 
     } else {
       mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
       mav.setViewName("redirect:/recomcontents/msg.do"); // GET
     }

     // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
     mav.addObject("now_page", recomcontentsVO.getNow_page());
     
     return mav; // forward
   }
   
   
   
   /**
    * 삭제 폼
    * @param contentsno
    * @return
    */
   @RequestMapping(value="/recomcontents/delete.do", method=RequestMethod.GET )
   public ModelAndView delete(int recontno) { 
     ModelAndView mav = new  ModelAndView();
     
     // 삭제할 정보를 조회하여 확인
     RecomcontentsVO recomcontentsVO = this.recomcontentsProc.read(recontno);
     mav.addObject("recomcontentsVO", recomcontentsVO);
     
     RecomplaceVO recomplaceVO = this.recomplaceProc.read(recomcontentsVO.getRecno());
     mav.addObject("recomplaceVO", recomplaceVO);
     
     mav.setViewName("/recomcontents/delete");  // /webapp/WEB-INF/views/contents/delete.jsp
     
     return mav; 
   }
   
   /**
    * 삭제 처리 http://localhost:9091/contents/delete.do
    * 
    * @return
    */
   @RequestMapping(value = "/recomcontents/delete.do", method = RequestMethod.POST)
   public ModelAndView delete(RecomcontentsVO recomcontentsVO) {
     ModelAndView mav = new ModelAndView();
     
     // -------------------------------------------------------------------
     // 파일 삭제 시작
     // -------------------------------------------------------------------
     // 삭제할 파일 정보를 읽어옴.
     RecomcontentsVO recomcontentsVO_read = recomcontentsProc.read(recomcontentsVO.getRecontno());
         
     String file1saved = recomcontentsVO.getFile1saved();
     String thumb1 = recomcontentsVO.getThumb1();
     
     String uploadDir = Recomcontents.getUploadDir();
     Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
     Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
     // -------------------------------------------------------------------
     // 파일 삭제 종료
     // -------------------------------------------------------------------
         
     this.recomcontentsProc.delete(recomcontentsVO.getRecontno()); // DBMS 삭제
     
     this.recomplaceProc.update_cnt_sub(recomcontentsVO.getRecno()); // cate 테이블 글 수 감소
         
     // -------------------------------------------------------------------------------------
     // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
     // -------------------------------------------------------------------------------------    
     // 마지막 페이지의 마지막 10번째 레코드를 삭제후
     // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
     // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
     int now_page = recomcontentsVO.getNow_page();
     if (recomcontentsProc.search_count(recomcontentsVO) % Recomcontents.RECORD_PER_PAGE == 0) {
       now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
       if (now_page < 1) {
         now_page = 1; // 시작 페이지
       }
     }
     // -------------------------------------------------------------------------------------

     mav.addObject("recno", recomcontentsVO.getRecno());
     mav.addObject("now_page", now_page);
     mav.setViewName("redirect:/recomcontents/list_by_recno.do"); 
     
     return mav;
   }   
   
   // http://localhost:9091/contents/count_by_cateno.do?cateno=1
   @RequestMapping(value = "/recomcontents/count_by_recno.do", method = RequestMethod.GET)
   public String count_by_recno(int recno) {
     System.out.println("-> count: " + this.recomcontentsProc.count_by_recno(recno));
     return "";
   
   }
   
   
   // http://localhost:9091/contents/delete_by_cateno.do?cateno=1
   // 파일 삭제 -> 레코드 삭제
   @RequestMapping(value = "/recomcontents/delete_by_recno.do", method = RequestMethod.GET)
   public String delete_by_recno(int recno) {
     ArrayList<RecomcontentsVO> list = this.recomcontentsProc.list_by_recno(recno);
     
     for(RecomcontentsVO recomcontentsVO : list) {
       // -------------------------------------------------------------------
       // 파일 삭제 시작
       // -------------------------------------------------------------------
       String file1saved = recomcontentsVO.getFile1saved();
       String thumb1 = recomcontentsVO.getThumb1();
       
       String uploadDir = Recomcontents.getUploadDir();
       Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
       Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
       // -------------------------------------------------------------------
       // 파일 삭제 종료
       // -------------------------------------------------------------------
     }
     
     System.out.println("-> count: " + this.recomcontentsProc.delete_by_recno(recno));
     
     return "";
   
   }
   
  
}
