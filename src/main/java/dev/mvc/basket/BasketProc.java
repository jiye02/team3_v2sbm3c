package dev.mvc.basket;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.basket.BasketProc")
public class BasketProc implements BasketProcInter {
  @Autowired
  private BasketDAOInter basketDAO;
  
  @Override
  public int create(BasketVO basketVO) {
    int quantity = this.basketDAO.create(basketVO);
    return quantity;
  }
  
  @Override
  public ArrayList<BasketVO> list_by_memberno(int memberno) {
    ArrayList<BasketVO> list = this.basketDAO.list_by_memberno(memberno);
    return list;
  }
  
  @Override
  public int update_quantity(BasketVO basketVO) {
    int quantity = this.basketDAO.update_quantity(basketVO);
    return quantity;
  }
  
  @Override
  public int delete(int basketno) {
    int quantity = this.basketDAO.delete(basketno);
    return quantity;
  }
  
}

