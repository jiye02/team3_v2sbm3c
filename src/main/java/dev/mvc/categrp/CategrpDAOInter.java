//package dev.mvc.categrp;
//
//import java.util.ArrayList;
//
//
//
//public interface CategrpDAOInter {
//  
//  /**
//   * 등록
//   * @param cateVO
//   * @return insert 태그가 추가한 레코드 갯수를 리턴
//   */
//  public int create(CategrpVO categrpVO);
//  
//  /**
//   * 카테 그룹 번호 오름차순 정렬
//   * @return CategrpVO
//   */
//  public ArrayList<CategrpVO> list_categrpno_asc();
//  
//  /**
//   * 조회
//   * @param int
//   * @return CategrpVO
//   */
//  public CategrpVO read (int categrpno);
//  
//   /**
//   * 수정
//   * @param categrpVO
//   * @return 수정된 레코드 갯수를 리턴
//   */
//  public int update(CategrpVO categrpVO);
//  
//  /**
//   * 삭제
//   * @param categrpno
//   * @return 삭제된 레코드 갯수를 리턴
//   */
//  public int delete(int categrpno);
//  
//  /**
//   * 출력순서 번호 오름차순 정렬
//   * @return CategrpVO
//   */
//  public ArrayList<CategrpVO> list_seqno_asc();
//  
//  /**
//   * 출력 순서 상향(10등 -> 1등), seqno 컬럼의 값 감소
//   * @param cateno
//   * @return 변경된 레코드 수
//   */
//  public int update_seqno_up(int categrpno);
//  
//  /**
//   * 출력 순서 하향(1등 -> 10등), seqno 컬럼의 값 증가
//   * @param cateno
//   * @return 변경된 레코드 수
//   */
//  public int update_seqno_down(int categrpno);
//
//  /**
//   * 공개,비공개 설정
//   * @param cateno
//   * @return
//   */
//  public int update_visible(CategrpVO categrpVO);
//  
//  
//  
//}




