package dev.mvc.jjim;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.jjim.JjimProc")
public class JjimProc implements JjimProcInter {
  @Autowired
  private JjimDAOInter jjimDAO;
  
  @Override
  public int create(JjimVO jjimVO) {
    int cnt = this.jjimDAO.create(jjimVO);
    return cnt;
  }
  
  @Override
  public ArrayList<JjimVO> list_by_memberno(int memberno) {
    ArrayList<JjimVO> list = this.jjimDAO.list_by_memberno(memberno);
    return list;
  }
  
  @Override
  public int update_cnt(JjimVO jjimVO) {
    int cnt = this.jjimDAO.update_cnt(jjimVO);
    return cnt;
  }
  
  @Override
  public int delete(int jjimno) {
    int cnt = this.jjimDAO.delete(jjimno);
    return cnt;
  }
  
  /*
   * @Override public int check(HashMap<Object, Object> map) { int cnt =
   * this.jjimDAO.check(map);
   * 
   * if (cnt==0) { // 이미 찜한 경우, DELETE 실행 this.jjimDAO.delete(cnt); } else { //
   * 찜하지 않은 경우, INSERT 실행 this.jjimDAO.check(map); } return cnt; }
   */
  
  @Override
  public int check(HashMap<Object, Object> map) {
    int cnt = this.jjimDAO.check(map);

    if (cnt == 0) {
      // 찜하지 않은 경우, INSERT 실행
      this.jjimDAO.create((JjimVO) map.get("jjimVO"));
    } else {
      // 이미 찜한 경우, DELETE 실행
      this.jjimDAO.delete((int) map.get("jjimno"));
    }

    cnt = this.jjimDAO.check(map); // 다시 확인하여 변경된 cnt 값 가져오기
    return cnt;
  }
  
  
  
}

