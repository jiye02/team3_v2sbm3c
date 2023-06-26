package dev.mvc.recommend;

import java.util.ArrayList;

public interface RecommendProcInter {
    
    /**
     * exhino 읽어오기
     * @param memberno
     * @return
     */
    public int recommend_read(int memberno);
    /**
     * 추천하는 카테고리 출력
     * @param exhino
     * @return
     */
    public ArrayList<RecommendVO> recommend(int exhino);

}