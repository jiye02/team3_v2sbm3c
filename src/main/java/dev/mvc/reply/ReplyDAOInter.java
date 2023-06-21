package dev.mvc.reply;

import java.util.List;
import java.util.Map;

public interface ReplyDAOInter {
  public int create(ReplyVO replyVO);
  
  public List<ReplyVO> list();
  
  public List<ReplyVO> list_by_galleryno(int galleryno);
  
  public List<ReplyMemberVO> list_by_galleryno_join(int galleryno);
  
  public int checkPasswd(Map<String, Object> map);

  public int delete(int replyno);
 

  
}