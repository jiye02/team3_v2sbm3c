package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.tool.Tool;

@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
  @Autowired
  private ReplyDAOInter replyDAO; 
  
  @Override
  public int create(ReplyVO replyVO) {
    int count = replyDAO.create(replyVO);
    return count;
  }

  @Override
  public List<ReplyMemberVO> list() {
    List<ReplyMemberVO> list = replyDAO.list();
    return list;
  }
  @Override
  public List<ReplyMemberVO> list_member_join_byUserId(String userId) {
    List<ReplyMemberVO> list = replyDAO.getRepliesByUserId(userId);
      
    return list;
  }


  @Override
  public List<ReplyMemberVO> list_member_join(int memberno) {
    List<ReplyMemberVO> list = replyDAO.list_member_join(memberno);
    
    // 특수 문자 변경
    for (ReplyMemberVO replyMemberVO:list) {
      String content = replyMemberVO.getContent();
      content = Tool.convertChar(content);
      replyMemberVO.setContent(content);
    }
    
    return list;
  }
  
  @Override
  public List<ReplyVO> list_by_galleryno(int galleryno) {
    List<ReplyVO> list = replyDAO.list_by_galleryno(galleryno);
    String content = "";
    
    // 특수 문자 변경
    for (ReplyVO replyVO:list) {
      content = replyVO.getContent();
      content = Tool.convertChar(content);
      replyVO.setContent(content);
    }
    return list;
  }

  @Override
  public List<ReplyMemberVO> list_by_galleryno_join(int galleryno) {
    List<ReplyMemberVO> list = replyDAO.list_by_galleryno_join(galleryno);
    String content = "";
    
    // 특수 문자 변경
    for (ReplyMemberVO replyMemberVO:list) {
      content = replyMemberVO.getContent();
      content = Tool.convertChar(content);
      replyMemberVO.setContent(content);
    }
    return list;
  }

  @Override
  public int checkPasswd(Map<String, Object> map) {
    int count = replyDAO.checkPasswd(map);
    return count;
  }

  @Override
  public int delete(int replyno) {
    int count = replyDAO.delete(replyno);
    return count;
  }


  @Override
  public List<ReplyMemberVO> list_by_galleryno_join_add(HashMap<String, Object> map) {
    int record_per_page = 2; // 한페이지당 2건
    
    // replyPage는 1부터 시작
    int beginOfPage = ((Integer)map.get("replyPage") - 1) * record_per_page; // 한페이지당 2건

    int startNum = beginOfPage + 1; 
    int endNum = beginOfPage + record_per_page;  // 한페이지당 2건
    /*
    1 페이지: WHERE r >= 1 AND r <= 2
    2 페이지: WHERE r >= 3 AND r <= 4
    3 페이지: WHERE r >= 5 AND r <= 6
    */
    map.put("startNum", startNum);
    map.put("endNum", endNum);
    
    List<ReplyMemberVO> list = replyDAO.list_by_galleryno_join_add(map);
    String content = "";
    
    // 특수 문자 변경
    for (ReplyMemberVO replyMemberVO:list) {
      content = replyMemberVO.getContent();
      content = Tool.convertChar(content);
      replyMemberVO.setContent(content);
    }
    return list;
  }
 
 
}

