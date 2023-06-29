package dev.mvc.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.notes.NotesVO;

public interface NotesDAOInter {
  
  /**
   * 등록
   * @param notesVO
   * @return
   */
  public int create(NotesVO notesVO);
  
  public ArrayList<NotesVO> list_all();
  
  
  /**
   * 조회
   * @param notesno
   * @return
   */
  public NotesVO read(int notesno);
  
  /**
   * 특정 카테고리의 검색된 글목록
   * @return
   */
  public ArrayList<NotesVO> list_by_search(NotesVO NotesVO);
  
  /**
   * 검색 수
   * @return
   */
  public int search_count(NotesVO notesVO);
  
  /**
   * 특정 카테고리의 검색된 페이지 목록
   * @return
   */
  public ArrayList<NotesVO> list_by_search_paging(NotesVO notesVO);
  

  /**
   * 패스워드 확인
   * @param notesVO
   * @return 처리된 레코드 갯수
   */
  public int password_check(NotesVO notesVO);
  
  /**
   * 글 정보 수정
   * @param notesVO
   * @return 처리된 레코드 갯수
   */
  public int update_text(NotesVO notesVO);
  
  /**
   * 파일 정보 수정
   * @param notesVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(NotesVO notesVO);
 
  /**
   * 삭제
   * @param notesno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int notesno);
  
  
  /**
   * 조회수
   * @param itemno
   * @return
   */
  public int cnt_add (int notesno);


}