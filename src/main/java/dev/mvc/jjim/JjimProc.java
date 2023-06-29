package dev.mvc.jjim;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.basket.BasketVO;

@Component("dev.mvc.jjim.JjimProc")
public class JjimProc implements JjimProcInter {
  @Autowired
  private JjimDAOInter jjimDAO;
  
  @Override
  public int create(HashMap<Object, Object> map) {
    int cnt = this.jjimDAO.create(map);
    return cnt;
  }
  
  @Override
  public int count(int galleryno) {
    int cnt = this.jjimDAO.count(galleryno);
    return cnt;
  }
  
  @Override
  public ArrayList<JjimVO> list_by_memberno(int memberno) {
    ArrayList<JjimVO> list = this.jjimDAO.list_by_memberno(memberno);
    return list;
  }
  
  @Override
  public int delete(HashMap<Object, Object> map) {
    int cnt = this.jjimDAO.delete(map);
    return cnt;
  }
  
  @Override
  public int jjim_check(HashMap<Object, Object> map) {
    int cnt = this.jjimDAO.jjim_check(map);
    return cnt;
  }
  
  
  
  
  
}
