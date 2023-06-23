package dev.mvc.addview;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddViewCont {

  public AddViewCont(){
    System.out.println("-> AddViewCont created.");
  }
  
  /**
   * JSON 페이징 출력
   * http://127.0.0.1:9091/add_view/add_view.do
   * @param replyPage
   * @return
   */
  @RequestMapping(value = "/add_view/add_view.do", 
                            method = RequestMethod.GET,
                            produces="text/plain;charset=UTF-8")
  public ModelAndView add_view() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/add_view/add_view"); // /add_view/add_view.jsp
    
    return mav;
  }
  
  /**
   * JSON 페이징 출력
   * http://127.0.0.1:9091/add_view/add_view_ajax.do?replyPage=1
   * @param replyPage
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/add_view/add_view_ajax.do", 
                            method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
  public String add_view_ajax(int replyPage) {
    System.out.println("addView 호출됨: " + replyPage);
    JSONObject obj = new JSONObject();
    obj.put("replyPage", replyPage);
    obj.put("name","JTBC");
    obj.put("title","물가상승");
    obj.put("content","5년간 물가 11%↑..담배·탄산음료·과자값 많이 올라");
  
    return obj.toString();
  }
  
  
}

