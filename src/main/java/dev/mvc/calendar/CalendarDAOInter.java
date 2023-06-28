package dev.mvc.calendar;

import java.util.ArrayList;

public interface CalendarDAOInter {
  /**
   * 등록
   * @param exhiVO
   * @return 등록된 갯수
   */
  public int create(CalendarVO calendarVO); // 추상 메소드
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<CalendarVO> list_all();
  
}
