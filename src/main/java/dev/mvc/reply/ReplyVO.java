package dev.mvc.reply;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ReplyVO {
  /** 댓글 번호 */
  private int replyno;
  /** 관련 글 번호 */
  private int galleryno;
  /** 회원 번호 */
  private int memberno;
  /** 내용 */
  private String content;
  /** 패스워드 */
  private String passwd;
  /** 등록일 */
  private String rdate;
  public int getReplyno() {
    return replyno;
  }
  public void setReplyno(int replyno) {
    this.replyno = replyno;
  }
  public int getGalleryno() {
    return galleryno;
  }
  public void setGalleryno(int galleryno) {
    this.galleryno = galleryno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  

}
