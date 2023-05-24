package dev.mvc.exhi;

import java.util.ArrayList;

public interface ExhiProcInter {
  /**
   * 등록
   * @param exhiVO 등록할 데이터
   * @return 등록된 레코드 갯수
   */
  public int create(ExhiVO exhiVO); // 추상 메소드
 
  /**
   *  전체 목록
   * @return
   */
  public ArrayList<ExhiVO> list_all();
  
  /**
   * 조회, 읽기
   * @param exhino
   * @return
   */
  public ExhiVO read(int exhino);
  
  /**
   * 수정
   * @param exhiVO
   * @return 수정된 레코드 갯수를 리턴
   */
  public int update(ExhiVO exhiVO);
  
  /**
   * 삭제
   * @param exhino
   * @return 삭제된 레코드 갯수를 리턴
   */
  public int delete(int exhino);
  
  /**
   * 출력 순서 하향(1등 -> 10등), seqno 컬럼의 값 증가
   * @param exhino
   * @return 변경된 레코드 수
   */
  public int update_seqno_decrease(int exhino);
  
  /**
   * 출력 순서 상향(10등 -> 1등), seqno 컬럼의 값 감소
   * @param exhino
   * @return 변경된 레코드 수
   */
  public int update_seqno_increase(int exhino);

  /**
   * 공개
   * @param exhino
   * @return
   */
  public int update_visible_y(int exhino);
  
  /**
   * 비공개
   * @param exhino
   * @return
   */
  public int update_visible_n(int exhino);
  
  /**
   * visible y만 출력
   * @return
   */
  public ArrayList<ExhiVO> list_all_y();

  /**
   * 글수 증가 
   * @param exhino
   * @return
   */
  public int update_cnt_add(int exhino);
  
  /**
   * 글수 감소
   * @param exhino
   * @return
   */
  public int update_cnt_sub(int exhino);
  
  
}


