package dev.mvc.jjim;

import java.util.ArrayList;

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
  
  @Override
  public int check(int memberno, int galleryno) {
    int cnt = this.jjimDAO.check(memberno, galleryno);
    return cnt;
  }
  
}

