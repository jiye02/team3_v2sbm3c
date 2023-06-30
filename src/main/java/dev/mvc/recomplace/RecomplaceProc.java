package dev.mvc.recomplace;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.recomplace.RecomplaceProc")
public class RecomplaceProc implements RecomplaceProcInter {
  @Autowired
  private RecomplaceDAOInter recomplaceDAO;

  @Override
  public int create(RecomplaceVO recomplaceVO) {
    int cnt = this.recomplaceDAO.create(recomplaceVO); // Spring이 자동으로 구현한 메소드를 호출
    return cnt;
  }

  @Override
  public ArrayList<RecomplaceVO> list_all() {
    ArrayList<RecomplaceVO> list = this.recomplaceDAO.list_all();
    
    return list;
  }

  @Override
  public RecomplaceVO read(int recno) {
    RecomplaceVO recomplaceVO = this.recomplaceDAO.read(recno);
    
    return recomplaceVO;
  }

  @Override
  public int update(RecomplaceVO recomplaceVO) {
    int cnt = this.recomplaceDAO.update(recomplaceVO);
    
    return cnt;
  }

  @Override
  public int delete(int recno) {
    int cnt = this.recomplaceDAO.delete(recno);
    return cnt;
  }

  @Override
  public int update_seqno_decrease(int recno) {
    int cnt = this.recomplaceDAO.update_seqno_decrease(recno);
    return cnt;
  }

  @Override
  public int update_seqno_increase(int recno) {
    int cnt = this.recomplaceDAO.update_seqno_increase(recno);
    return cnt;
  }
  
  @Override
  public int update_visible_y(int recno) {
    int cnt = this.recomplaceDAO.update_visible_y(recno);
    return cnt;
  }
  
  @Override
  public int update_visible_n(int recno) {
    int cnt = this.recomplaceDAO.update_visible_n(recno);
    return cnt;
  }

  @Override
  public ArrayList<RecomplaceVO> list_all_y() {
    ArrayList<RecomplaceVO> reclist = this.recomplaceDAO.list_all_y();
    return reclist;
  }
  
  @Override
  public int update_cnt_add(int recno) {
    int cnt = this.recomplaceDAO.update_cnt_add(recno);
    return cnt;
  }
  
  @Override
  public int update_cnt_sub(int recno) {
    int cnt = this.recomplaceDAO.update_cnt_sub(recno);
    return cnt;
  }
  
}
