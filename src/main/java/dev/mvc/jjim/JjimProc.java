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
  public int check(JjimVO jjimVO) {
    int cnt = this.jjimDAO.check(jjimVO);
    
    return cnt;
  }
  
  @Override
  public int delete(int memberno) {
    int cnt = this.jjimDAO.delete(memberno);
    return cnt;
  }
  
}

