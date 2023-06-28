package dev.mvc.time;


public interface TimeDAOInter {
  /**
   * 등록
   * @param exhiVO
   * @return 등록된 갯수
   */
  public int create(TimeVO timeVO); // 추상 메소드
}
