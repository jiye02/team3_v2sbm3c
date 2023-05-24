package dev.mvc.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.exhi.ExhiVO;

public interface GalleryDAOInter {
  /**
   * 등록
   * spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @param galleryVO
   * @return
   */
  public int create(GalleryVO galleryVO);

  /**
   *  모든 카테고리의 등록된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<GalleryVO> list_all();

  /**
   *  특정 카테고리의 등록된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<GalleryVO> list_by_exhino(int exhino);
  
  /**
   * 조회
   * @param galleryno
   * @return
   */
  public GalleryVO read(int galleryno);
  
  /**
   * Map
   * @param galleryVO
   * @return
   */
  public int map(GalleryVO galleryVO);

  /**
   * Youtube
   * @param galleryVO
   * @return
   */
  public int youtube(GalleryVO galleryVO);

  /**
   *  특정 카테고리의 검색된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<GalleryVO> list_by_exhino_search(GalleryVO galleryVO);
  
  /**
   * 검색된 레코드 갯수 리턴
   * @param galleryVO
   * @return
   */
  public int search_count(GalleryVO galleryVO);

  /**
   *  특정 카테고리의 검색 + 페이징된 글목록
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<GalleryVO> list_by_exhino_search_paging(GalleryVO galleryVO);
  
  /**
   * 글 정보 수정
   * @param galleryVO
   * @return 변경된 레코드 갯수
   */
  public int update_text(GalleryVO galleryVO);
 
  /**
   * 패스워드 검사  
   * @param galleryVO
   * @return 1: 패스워드 일치, 0: 패스워드 불일치
   */
  public int password_check(GalleryVO galleryVO);
  
  /**
   * 파일 정보 수정
   * @param galleryVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(GalleryVO galleryVO);
  
  /**
   * 삭제
   * @param galleryno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int galleryno);
  
  /**
   * 카테고리별 레코드 갯수
   * @param exhino
   * @return
   */
  public int count_by_exhino(int exhino);
  
  /**
   * 특정 카테고리에 속한 모든 레코드 삭제
   * @param exhino
   * @return 삭제된 레코드 갯수
   */
  public int delete_by_exhino(int exhino);
  
}





