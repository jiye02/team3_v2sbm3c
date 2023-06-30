package dev.mvc.recomcontents;

import java.util.ArrayList;



public interface RecomcontentsDAOInter {
  /**
   * 등록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param contentsVO
   * @return
   */
  public int create(RecomcontentsVO recomcontentsVO);

  /**
   *  모든 카테고리의 등록된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<RecomcontentsVO> list_all();

  /**
   *  특정 카테고리의 등록된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<RecomcontentsVO> list_by_recno(int recno);
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public RecomcontentsVO read(int recontno);
  
  /**
   * Map
   * @param contentsVO
   * @return
   */
  public int map(RecomcontentsVO recomcontentsVO);

  /**
   * Youtube
   * @param contentsVO
   * @return
   */
  public int youtube(RecomcontentsVO recomcontentsVO);

  /**
   *  특정 카테고리의 검색된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<RecomcontentsVO> list_by_recno_search(RecomcontentsVO recomcontentsVO);
  
  /**
   * 검색된 레코드 갯수 리턴
   * @param contentsVO
   * @return
   */
  public int search_count(RecomcontentsVO recomcontentsVO);

  /**
   *  특정 카테고리의 검색 + 페이징된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<RecomcontentsVO> list_by_recno_search_paging(RecomcontentsVO recomcontentsVO);
  
  /**
   * 글 정보 수정
   * @param contentsVO
   * @return 변경된 레코드 갯수
   */
  public int update_text(RecomcontentsVO recomcontentsVO);
 
  /**
   * 패스워드 검사  
   * @param contentsVO
   * @return 1: 패스워드 일치, 0: 패스워드 불일치
   */
  public int password_check(RecomcontentsVO recomcontentsVO);
  
  /**
   * 파일 정보 수정
   * @param contentsVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(RecomcontentsVO recomcontentsVO);
  
  /**
   * 삭제
   * @param contentsno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int recontno);
  
  /**
   * 카테고리별 레코드 갯수
   * @param cateno
   * @return
   */
  public int count_by_recno(int recno);
  
  /**
   * 특정 카테고리에 속한 모든 레코드 삭제
   * @param cateno
   * @return 삭제된 레코드 갯수
   */
  public int delete_by_recno(int recno);
}
