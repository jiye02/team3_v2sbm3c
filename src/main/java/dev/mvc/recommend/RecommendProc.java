package dev.mvc.recommend;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.mvc.gallery.GalleryDAOInter;
import dev.mvc.exhi.ExhiDAOInter;

@Component("dev.mvc.recommend.RecommendProc")
public class RecommendProc implements RecommendProcInter {
    @Autowired
    private RecommendDAOInter recommendDAO;

    @Override
    public RecommendVO read(int memberno) {
        RecommendVO recommendVO = this.recommendDAO.read(memberno);
        return recommendVO;
    }
    @Override
    public ArrayList<RecommendVO> recommend(int exhino) {
        ArrayList<RecommendVO> list = this.recommendDAO.recommend(exhino);
        return list;
    }


}