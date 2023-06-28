package dev.mvc.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.time.TimeProc")
public class TimeProc implements TimeProcInter {
  @Autowired
  private TimeDAOInter timeDAO;
  
  @Override
  public int create(TimeVO timeVO) {
    int cnt = this.timeDAO.create(timeVO);
    return cnt;
  }

}
