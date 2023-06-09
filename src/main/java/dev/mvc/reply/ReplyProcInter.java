package dev.mvc.reply;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public interface ReplyProcInter {
  public int create(ReplyVO replyVO);
  
  public List<ReplyMemberVO> list();

  public List<ReplyMemberVO> list_member_join(int memberno);
  
  public List<ReplyVO> list_by_galleryno(int galleryno);
  
  /**
   * 특정글 관련 전체 댓글 목록
   * @param galleryno
   * @return
   */
  public List<ReplyMemberVO> list_by_galleryno_join(int galleryno);
  
  public int checkPasswd(Map<String, Object> map);

  public int delete(int replyno);
  
  /**
   * 더보기 버튼
   * @param map
   * @return
   */
  public List<ReplyMemberVO> list_by_galleryno_join_add(HashMap<String, Object> map);

  public List<ReplyMemberVO> list_member_join_byUserId(String userId);
  
}