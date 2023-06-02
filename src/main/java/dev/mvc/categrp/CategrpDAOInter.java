package dev.mvc.categrp;

import java.util.ArrayList;



public interface CategrpDAOInter {
  
  /**
   * 등록
   * @param cateVO
   * @return insert 태그가 추가한 레코드 갯수를 리턴
   */
  public int create(CategrpVO categrpVO);
  
  /**
   * 카테 그룹 번호 오름차순 정렬
   * @return CategrpVO
   */
  public ArrayList<CategrpVO> list_all();
  
  /**
   * 조회
   * @param int
   * @return CategrpVO
   */
  public CategrpVO read (int Categrpno);
  
   /**
   * 수정
   * @param categrpVO
   * @return 수정된 레코드 갯수를 리턴
   */
  public int update(CategrpVO categrpVO);
  
  /**
   * 삭제
   * @param categrpno
   * @return 삭제된 레코드 갯수를 리턴
   */
  public int delete(int categrpno);
  
  
  
}
