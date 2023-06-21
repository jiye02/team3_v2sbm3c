package dev.mvc.gallery;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
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
import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.jjim.JjimProcInter;
import dev.mvc.jjim.JjimVO;
import dev.mvc.exhi.ExhiProcInter;
import dev.mvc.exhi.ExhiVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class GalleryCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") 
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.jjim.JjimProc")
  private JjimProcInter jjimProc;
  
  @Autowired
  @Qualifier("dev.mvc.exhi.ExhiProc") 
  private ExhiProcInter exhiProc;
  
  @Autowired
  @Qualifier("dev.mvc.gallery.GalleryProc") 
  private GalleryProcInter galleryProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public GalleryCont () {
    System.out.println("-> GalleryCont created.");
  }
  
  // 등록 폼, gallery 테이블은 FK로 exhino를 사용함.
  // http://localhost:9093/gallery/create.do  X
  // http://localhost:9093/gallery/create.do?exhino=1
  // http://localhost:9093/gallery/create.do?exhino=2
  // http://localhost:9093/gallery/create.do?exhino=3
  @RequestMapping(value="/gallery/create.do", method = RequestMethod.GET)
  public ModelAndView create(int exhino) {
//  public ModelAndView create(HttpServletRequest request,  int exhino) {
    ModelAndView mav = new ModelAndView();

    ExhiVO exhiVO = this.exhiProc.read(exhino); // create.jsp에 카테고리 정보를 출력하기위한 목적
    mav.addObject("exhiVO", exhiVO);
//    request.setAttribute("exhiVO", exhiVO);
    
    mav.setViewName("/gallery/create"); // /webapp/WEB-INF/views/gallery/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/gallery/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/gallery/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();
    
    if (adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // preview image

      String upDir =  Gallery.getUploadDir();
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = galleryVO.getFile1MF();
      
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
      
      galleryVO.setFile1(file1);   // 순수 원본 파일명
      galleryVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      galleryVO.setThumb1(thumb1);      // 원본이미지 축소판
      galleryVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int adminno = (int)session.getAttribute("adminno"); // adminno FK
      galleryVO.setAdminno(adminno);
      int cnt = this.galleryProc.create(galleryVO); 
      
      // ------------------------------------------------------------------------------
      // PK의 return
      // ------------------------------------------------------------------------------
      // System.out.println("--> galleryno: " + galleryVO.getGalleryno());
      // mav.addObject("galleryno", galleryVO.getGalleryno()); // redirect parameter 적용
      // ------------------------------------------------------------------------------
      
      if (cnt == 1) {
        this.exhiProc.update_cnt_add(galleryVO.getExhino()); // exhi 테이블 글 수 증가
        mav.addObject("code", "create_success");
        // exhiProc.increaseCnt(galleryVO.getExhino()); // 글수 증가
      } else {
        mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
      // System.out.println("--> exhino: " + galleryVO.getExhino());
      // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
      mav.addObject("exhino", galleryVO.getExhino()); // redirect parameter 적용
      
      mav.addObject("url", "/gallery/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/gallery/msg.do"); 

    } else {
      mav.addObject("url", "/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/gallery/msg.do"); 
    }
    
    return mav; // forward
  }
  
  /**
   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * @return
   */
  @RequestMapping(value="/gallery/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
   * 모든 카테고리의 등록된 글목록, http://localhost:9093/gallery/list_all.do
   * @return
   */
  @RequestMapping(value="/gallery/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<GalleryVO> list = this.galleryProc.list_all();
    mav.addObject("list", list);
    
    mav.setViewName("/gallery/list_all"); // /webapp/WEB-INF/views/gallery/list_all.jsp
    
    return mav;
  }
  
//  /**
//   * 특정 카테고리의 등록된 글목록
//   * http://localhost:9093/gallery/list_by_exhino.do?exhino=1
//   * http://localhost:9093/gallery/list_by_exhino.do?exhino=2
//   * http://localhost:9093/gallery/list_by_exhino.do?exhino=3
//   * @return
//   */
//  @RequestMapping(value="/gallery/list_by_exhino.do", method=RequestMethod.GET)
//  public ModelAndView list_by_exhino(int exhino) {
//    ModelAndView mav = new ModelAndView();
//    
//    ExhiVO exhiVO = this.exhiProc.read(exhino);
//    mav.addObject("exhiVO", exhiVO);
//        
//    ArrayList<GalleryVO> list = this.galleryProc.list_by_exhino(exhino);
//    mav.addObject("list", list);
//    
//    mav.setViewName("/gallery/list_by_exhino"); // /webapp/WEB-INF/views/gallery/list_by_exhino.jsp
//    
//    return mav;
//  }
  
  // http://localhost:9093/gallery/read.do
  /**
   * 조회
   * @return
   */
  @RequestMapping(value="/gallery/read.do", method=RequestMethod.GET )
  public ModelAndView read(int galleryno, JjimVO jjimVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();

    GalleryVO galleryVO = this.galleryProc.read(galleryno);
    
    String title = galleryVO.getTitle();
    String content = galleryVO.getContent();
    
    title = Tool.convertChar(title);  // 특수 문자 처리
    content = Tool.convertChar(content); 
    
    galleryVO.setTitle(title);
    galleryVO.setContent(content);  
    
    long size1 = galleryVO.getSize1();
    galleryVO.setSize1_label(Tool.unit(size1));    
    
    mav.addObject("galleryVO", galleryVO); // request.setAttribute("galleryVO", galleryVO);

    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino()); // 그룹 정보 읽기
    mav.addObject("exhiVO", exhiVO);
    
    // 회원 번호: admino -> AdminVO -> mname
    String mname = this.adminProc.read(galleryVO.getAdminno()).getMname();
    mav.addObject("mname", mname);

    mav.setViewName("/gallery/read"); // /WEB-INF/views/gallery/read.jsp
        
 // 찜 확인
    if (memberProc.isMember(session)) {
      int memberno = (int) (session.getAttribute("memberno"));
      jjimVO.setMemberno(memberno);
      
      int check_cnt = this.jjimProc.check(jjimVO);
      mav.addObject("check", check_cnt);
      
    }
    
    return mav;
  }
  
  /**
   * 맵 등록/수정/삭제 폼
   * http://localhost:9093/gallery/map.do?galleryno=1
   * @return
   */
  @RequestMapping(value="/gallery/map.do", method=RequestMethod.GET )
  public ModelAndView map(int galleryno) {
    ModelAndView mav = new ModelAndView();

    GalleryVO galleryVO = this.galleryProc.read(galleryno); // map 정보 읽어 오기
    mav.addObject("galleryVO", galleryVO); // request.setAttribute("galleryVO", galleryVO);

    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino()); // 그룹 정보 읽기
    mav.addObject("exhiVO", exhiVO); 

    mav.setViewName("/gallery/map"); // /WEB-INF/views/gallery/map.jsp
        
    return mav;
  }
  
  /**
   * MAP 등록/수정/삭제 처리
   * http://localhost:9093/gallery/map.do
   * @param galleryVO
   * @return
   */
  @RequestMapping(value="/gallery/map.do", method = RequestMethod.POST)
  public ModelAndView map_update(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();
    
    this.galleryProc.map(galleryVO);
    
    mav.setViewName("redirect:/gallery/read.do?galleryno=" + galleryVO.getGalleryno()); 
    // /webapp/WEB-INF/views/gallery/read.jsp
    
    return mav;
  }
  
  /**
   * Youtube 등록/수정/삭제 폼
   * http://localhost:9093/gallery/youtube.do?galleryno=1
   * @return
   */
  @RequestMapping(value="/gallery/youtube.do", method=RequestMethod.GET )
  public ModelAndView youtube(int galleryno) {
    ModelAndView mav = new ModelAndView();

    GalleryVO galleryVO = this.galleryProc.read(galleryno); // map 정보 읽어 오기
    mav.addObject("galleryVO", galleryVO); // request.setAttribute("galleryVO", galleryVO);

    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino()); // 그룹 정보 읽기
    mav.addObject("exhiVO", exhiVO); 

    mav.setViewName("/gallery/youtube"); // /WEB-INF/views/gallery/youtube.jsp
        
    return mav;
  }
  
  /**
   * Youtube 등록/수정/삭제 처리
   * http://localhost:9093/gallery/map.do
   * @param galleryVO
   * @return
   */
  @RequestMapping(value="/gallery/youtube.do", method = RequestMethod.POST)
  public ModelAndView youtube_update(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();
    
    if (galleryVO.getYoutube().trim().length() > 0) { // 삭제 중인지 확인, 삭제가 아니면 youtube 크기 변경
      // youtube 영상의 크기를 width 기준 640 px로 변경 
      String youtube = Tool.youtubeResize(galleryVO.getYoutube());
      galleryVO.setYoutube(youtube);
    }
    
    this.galleryProc.youtube(galleryVO);

    // youtube 크기 조절
    // <iframe width="1019" height="573" src="https://www.youtube.com/embed/Aubh5KOpz-4" title="교보문고에서 가장 잘나가는 일본 추리소설 베스트셀러 10위부터 1위까지 소개해드려요📚" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
    
    
    mav.setViewName("redirect:/gallery/read.do?galleryno=" + galleryVO.getGalleryno()); 
    // /webapp/WEB-INF/views/gallery/read.jsp
    
    return mav;
  }
  
//  /**
//   * 특정 카테고리의 검색된 글목록
//   * http://localhost:9093/gallery/list_by_exhino.do?exhino=8&word=부대찌게
//   * @return
//   */
//  @RequestMapping(value="/gallery/list_by_exhino.do", method=RequestMethod.GET)
//  public ModelAndView list_by_exhino_search(GalleryVO galleryVO) {
//    ModelAndView mav = new ModelAndView();
//    
//    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino());
//    mav.addObject("exhiVO", exhiVO);
//        
//    ArrayList<GalleryVO> list = this.galleryProc.list_by_exhino_search(galleryVO);
//    mav.addObject("list", list);
//    
//    mav.setViewName("/gallery/list_by_exhino_search"); // /webapp/WEB-INF/views/gallery/list_by_exhino_search.jsp
//    
//    return mav;
//  }
  
  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9093/gallery/list_by_exhino.do?exhino=1&word=스위스&now_page=1
   * 
   * @param exhino
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/gallery/list_by_exhino.do", method = RequestMethod.GET)
  public ModelAndView list_by_exhino_search_paging(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();

    // 검색된 전체 글 수
    int search_count = this.galleryProc.search_count(galleryVO);
    mav.addObject("search_count", search_count);
    
    // 검색 목록: 검색된 레코드를 페이지 단위로 분할하여 가져옴
    ArrayList<GalleryVO> list = galleryProc.list_by_exhino_search_paging(galleryVO);
    mav.addObject("list", list);
    
    ExhiVO exhiVO = exhiProc.read(galleryVO.getExhino());
    mav.addObject("exhiVO", exhiVO);

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

  /**
   * 목록 + 검색 + 페이징 + Grid(갤러리) 지원
   * http://localhost:9093/gallery/list_by_exhino_grid.do?exhino=1&word=스위스&now_page=1
   * 
   * @param exhino
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/gallery/list_by_exhino_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_exhino_search_paging_grid(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();

    // 검색된 전체 글 수
    int search_count = this.galleryProc.search_count(galleryVO);
    mav.addObject("search_count", search_count);
    
    // 검색 목록
    ArrayList<GalleryVO> list = galleryProc.list_by_exhino_search_paging(galleryVO);
    mav.addObject("list", list);

    ExhiVO exhiVO = exhiProc.read(galleryVO.getExhino());
    mav.addObject("exhiVO", exhiVO);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param exhino 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    String paging = galleryProc.pagingBox(galleryVO.getExhino(), galleryVO.getNow_page(), galleryVO.getWord(), "list_by_exhino_grid.do");
    mav.addObject("paging", paging);

    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/gallery/list_by_exhino_search_paging_grid");  // /gallery/list_by_exhino_search_paging_grid.jsp

    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/gallery/update_text.do?galleryno=1
   * 
   * @return
   */
  @RequestMapping(value = "/gallery/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(int galleryno) {
    ModelAndView mav = new ModelAndView();
    
    GalleryVO galleryVO = this.galleryProc.read(galleryno);
    mav.addObject("galleryVO", galleryVO);
    
    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino());
    mav.addObject("exhiVO", exhiVO);
    
    mav.setViewName("/gallery/update_text"); // /WEB-INF/views/gallery/update_text.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/gallery/update_text.do?galleryno=1
   * 
   * @return
   */
  @RequestMapping(value = "/gallery/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(HttpSession session, GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("-> word: " + galleryVO.getWord());
    
    if (this.adminProc.isAdmin(session)) { // 관리자 로그인
      this.galleryProc.update_text(galleryVO);  
      
      mav.addObject("galleryno", galleryVO.getGalleryno());
      mav.addObject("exhino", galleryVO.getExhino());
      mav.setViewName("redirect:/gallery/read.do");
    } else { // 정상적인 로그인이 아닌 경우
      if (this.galleryProc.password_check(galleryVO) == 1) {
        this.galleryProc.update_text(galleryVO);  
         
        // mav 객체 이용
        mav.addObject("galleryno", galleryVO.getGalleryno());
        mav.addObject("exhino", galleryVO.getExhino());
        mav.setViewName("redirect:/gallery/read.do");
      } else {
        mav.addObject("url", "/gallery/passwd_check"); // /WEB-INF/views/gallery/passwd_check.jsp
        mav.setViewName("redirect:/gallery/msg.do");  // POST -> GET -> JSP 출력
      }    
    }
    
    mav.addObject("now_page", galleryVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
    
    // URL에 파라미터의 전송
    // mav.setViewName("redirect:/gallery/read.do?galleryno=" + galleryVO.getGalleryno() + "&exhino=" + galleryVO.getExhino());             
    
    return mav; // forward
  }
     
  /**
   * galleryno, passwd를 GET 방식으로 전달받아 패스워드 일치 검사를하고 결과 1또는 0을 Console에 출력하세요.
   * http://localhost:9093/gallery/password_check.do?galleryno=2&passwd=123
   * @return
   */
  @RequestMapping(value="/gallery/password_check.do", method=RequestMethod.GET )
  public ModelAndView password_check(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.galleryProc.password_check(galleryVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 0) {
      mav.setViewName("/gallery/passwd_check"); // /WEB-INF/views/gallery/passwd_check.jsp
    }
        
    return mav;
  }
 
  /**
   * 파일 수정 폼
   * http://localhost:9093/gallery/update_file.do?galleryno=1
   * 
   * @return
   */
  @RequestMapping(value = "/gallery/update_file.do", method = RequestMethod.GET)
  public ModelAndView update_file(int galleryno) {
    ModelAndView mav = new ModelAndView();
    
    GalleryVO galleryVO = this.galleryProc.read(galleryno);
    mav.addObject("galleryVO", galleryVO);
    
    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino());
    mav.addObject("exhiVO", exhiVO);
    
    mav.setViewName("/gallery/update_file"); // /WEB-INF/views/gallery/update_file.jsp

    return mav; // forward
  }
  
  /**
   * 파일 수정 처리 http://localhost:9093/gallery/update_file.do
   * 
   * @return
   */
  @RequestMapping(value = "/gallery/update_file.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.adminProc.isAdmin(session)) {
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
      GalleryVO galleryVO_old = galleryProc.read(galleryVO.getGalleryno());
      
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      String file1saved = galleryVO_old.getFile1saved();  // 실제 저장된 파일명
      String thumb1 = galleryVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
      long size1 = 0;
         
      String upDir =  Gallery.getUploadDir(); // C:/kd/deploy/art_v2sbm3c/gallery/storage/
      
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
      MultipartFile mf = galleryVO.getFile1MF();
          
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
          
      galleryVO.setFile1(file1);
      galleryVO.setFile1saved(file1saved);
      galleryVO.setThumb1(thumb1);
      galleryVO.setSize1(size1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
          
      this.galleryProc.update_file(galleryVO); // Oracle 처리

      mav.addObject("galleryno", galleryVO.getGalleryno());
      mav.addObject("exhino", galleryVO.getExhino());
      mav.setViewName("redirect:/gallery/read.do"); // request -> param으로 접근 전환
                
    } else {
      mav.addObject("url", "/admin/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/gallery/msg.do"); // GET
    }

    // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
    mav.addObject("now_page", galleryVO.getNow_page());
    
    return mav; // forward
  }   

  /**
   * 삭제 폼
   * @param galleryno
   * @return
   */
  @RequestMapping(value="/gallery/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int galleryno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    GalleryVO galleryVO = this.galleryProc.read(galleryno);
    mav.addObject("galleryVO", galleryVO);
    
    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino());
    mav.addObject("exhiVO", exhiVO);
    
    mav.setViewName("/gallery/delete");  // /webapp/WEB-INF/views/gallery/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9093/gallery/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/gallery/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(GalleryVO galleryVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 삭제 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    GalleryVO galleryVO_read = galleryProc.read(galleryVO.getGalleryno());
        
    String file1saved = galleryVO.getFile1saved();
    String thumb1 = galleryVO.getThumb1();
    
    String uploadDir = Gallery.getUploadDir();
    Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
    Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료
    // -------------------------------------------------------------------
        
    this.galleryProc.delete(galleryVO.getGalleryno()); // DBMS 삭제
    
    this.exhiProc.update_cnt_sub(galleryVO.getExhino()); // exhi 테이블 글 수 감소
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    int now_page = galleryVO.getNow_page();
    if (galleryProc.search_count(galleryVO) % Gallery.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }
    // -------------------------------------------------------------------------------------

    mav.addObject("exhino", galleryVO.getExhino());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/gallery/list_by_exhino.do"); 
    
    return mav;
  }   
  
  // http://localhost:9093/gallery/count_by_exhino.do?exhino=1
  @RequestMapping(value = "/gallery/count_by_exhino.do", method = RequestMethod.GET)
  public String count_by_exhino(int exhino) {
    System.out.println("-> count: " + this.galleryProc.count_by_exhino(exhino));
    return "";
  
  }
  
  // http://localhost:9093/gallery/delete_by_exhino.do?exhino=1
  // 파일 삭제 -> 레코드 삭제
  @RequestMapping(value = "/gallery/delete_by_exhino.do", method = RequestMethod.GET)
  public String delete_by_exhino(int exhino) {
    ArrayList<GalleryVO> list = this.galleryProc.list_by_exhino(exhino);
    
    for(GalleryVO galleryVO : list) {
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
    
    System.out.println("-> count: " + this.galleryProc.delete_by_exhino(exhino));
    
    return "";
  
  }
  // http://localhost:9091/gallery/read.do
  /**
   * 조회
   * @return
   */
  @RequestMapping(value="/gallery/read_ajax.do", method=RequestMethod.GET )
  public ModelAndView read_ajax(HttpServletRequest request, int galleryno) {
    // public ModelAndView read(int galleryno, int now_page) {
    // System.out.println("-> now_page: " + now_page);
    
    ModelAndView mav = new ModelAndView();

    GalleryVO galleryVO = this.galleryProc.read(galleryno);
    mav.addObject("galleryVO", galleryVO); // request.setAttribute("galleryVO", galleryVO);

    ExhiVO exhiVO = this.exhiProc.read(galleryVO.getExhino());
    mav.addObject("exhiVO", exhiVO); 

    // 단순 read
    // mav.setViewName("/gallery/read"); // /WEB-INF/views/gallery/read.jsp
    
    // 쇼핑 기능 추가
    // mav.setViewName("/gallery/read_cookie"); // /WEB-INF/views/gallery/read_cookie.jsp
    
    // 댓글 기능 추가 
    mav.setViewName("/gallery/read_cookie_reply"); // /WEB-INF/views/gallery/read_cookie_reply.jsp
    
    // -------------------------------------------------------------------------------
    // 쇼핑 카트 장바구니에 상품 등록전 로그인 폼 출력 관련 쿠기  
    // -------------------------------------------------------------------------------
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_id = ""; // id 저장
    String ck_id_save = ""; // id 저장 여부를 체크
    String ck_passwd = ""; // passwd 저장
    String ck_passwd_save = ""; // passwd 저장 여부를 체크

    if (cookies != null) {  // Cookie 변수가 있다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue();                                 // Cookie에 저장된 id
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();                          // Cookie에 id를 저장 할 것인지의 여부, Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();                          // Cookie에 저장된 password
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();                  // Cookie에 password를 저장 할 것인지의 여부, Y, N
        }
      }
    }
    
    System.out.println("-> ck_id: " + ck_id);
    
    mav.addObject("ck_id", ck_id); 
    mav.addObject("ck_id_save", ck_id_save);
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
    // -------------------------------------------------------------------------------
    
    return mav;
  }
  
  
}




