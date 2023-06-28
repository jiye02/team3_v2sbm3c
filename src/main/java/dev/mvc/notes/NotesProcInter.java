package dev.mvc.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import dev.mvc.gallery.GalleryVO;

public interface NotesProcInter {
  /**
   * 공지사항 등록
   * @param NotesVO
   * @return
   */
  public int create(NotesVO notesVO);
  
  /**
   * 공지사항 전체 목록
   * @param 
   * @return 전체목록을 ArrayList<NotesVO>로 리턴
   */
  public ArrayList<NotesVO> list_all();
  
  /**
   * 검색된 레코드 갯수 리턴
   * @param notesVO
   * @return
   */
  public int search_count(NotesVO notesVO);
  
  /**
   *  검색 + 페이징된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<NotesVO> list_all_search_paging(NotesVO notesVO);
  
  /**
   * 공지사항 조회
   * @return
   */
  public NotesVO read(int notesno);
  
  /**
   * 공지사항 글 수정
   * @return
   */
  public int update_text(NotesVO notesVO);
  
  /**
   * 공지사항 파일 수정
   * @return
   */
  public int update_file(NotesVO notesVO);
  
  /**
   * 공지사항 삭제
   * @return
   */
  public int delete(int notesno);

  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param now_page  현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @return 페이징 생성 문자열
   */
  public String pagingBox(int now_page, String word, String list_file);
  
  

}
  

 

