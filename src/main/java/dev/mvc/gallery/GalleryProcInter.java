package dev.mvc.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface GalleryProcInter {
  /**
   * 등록
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param exhino          카테고리번호 
   * @param now_page      현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명 
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int exhino, int now_page, String word, String list_file);
  
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
   * 찜 ++
   * @param itemno
   * @return
   */
  public int jjim_add (int galleryno);
  
  /**
   * 찜--
   * @param itemno
   * @return
   */
  public int jjim_sub (int galleryno);
  
  /**
   * 특정 카테고리에 속한 모든 레코드 삭제
   * @param exhino
   * @return 삭제된 레코드 갯수
   */
  public int delete_by_exhino(int exhino);
  /**
   * 글 수 증가
   * @param 
   * @return
   */ 
  public int increaseReplycnt(int galleryno);
 
  /**
   * 글 수 감소
   * @param 
   * @return
   */   
  public int decreaseReplycnt(int galleryno);
  
  /**
   *  특정 카테고리의 등록된 추천 목록 7건
   *  spring framework이 JDBC 관련 코드를 모두 생성해줌
   * @return
   */
  public ArrayList<GalleryVO> recommend_jjim(int exhino);
  
  

    
  
  
}
  
  

 

