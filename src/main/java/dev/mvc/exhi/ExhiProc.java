package dev.mvc.exhi;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.exhi.ExhiProc") // Controller가 사용하는 이름
public class ExhiProc implements ExhiProcInter {
  @Autowired
  private ExhiDAOInter exhiDAO; // Spring이 CateDAOInter.java를 구현하여 객체를 자동 생성후 할당
  
  @Override
  public int create(ExhiVO exhiVO) {
    int cnt = this.exhiDAO.create(exhiVO); // Spring이 자동으로 구현한 메소드를 호출
    return cnt;
  }

  @Override
  public ArrayList<ExhiVO> list_all() {
    ArrayList<ExhiVO> list = this.exhiDAO.list_all();
    
    return list;
  }

  @Override
  public ExhiVO read(int exhino) {
    ExhiVO exhiVO = this.exhiDAO.read(exhino);
    
    return exhiVO;
  }

  @Override
  public int update(ExhiVO exhiVO) {
    int cnt = this.exhiDAO.update(exhiVO);
    
    return cnt;
  }

  @Override
  public int delete(int exhino) {
    int cnt = this.exhiDAO.delete(exhino);
    return cnt;
  }

  @Override
  public int update_seqno_decrease(int exhino) {
    int cnt = this.exhiDAO.update_seqno_decrease(exhino);
    return cnt;
  }

  @Override
  public int update_seqno_increase(int exhino) {
    int cnt = this.exhiDAO.update_seqno_increase(exhino);
    return cnt;
  }
  
  @Override
  public int update_visible_y(int exhino) {
    int cnt = this.exhiDAO.update_visible_y(exhino);
    return cnt;
  }
  
  @Override
  public int update_visible_n(int exhino) {
    int cnt = this.exhiDAO.update_visible_n(exhino);
    return cnt;
  }

  @Override
  public ArrayList<ExhiVO> list_all_y() {
    ArrayList<ExhiVO> list = this.exhiDAO.list_all_y();
    return list;
  }
  
  @Override
  public int update_cnt_add(int exhino) {
    int cnt = this.exhiDAO.update_cnt_add(exhino);
    return cnt;
  }
  
  @Override
  public int update_cnt_sub(int exhino) {
    int cnt = this.exhiDAO.update_cnt_sub(exhino);
    return cnt;
  }

  @Override
  public ArrayList<ExhiVO> adminList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void recommend_add(int exhino) {
    // TODO Auto-generated method stub
    
  }
  
}




