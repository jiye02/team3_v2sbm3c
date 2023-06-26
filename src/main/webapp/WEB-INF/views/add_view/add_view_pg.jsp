<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
$(function(){
  $("#btn_add").on("click", addView);
  addView();
});
 
function addView(){
  $.ajax({
      url: "./add_view_pg_ajax.do",
      type: "GET",
      cache: false,
      dataType: "JSON",
      data: "replyPage="+$("#reply_list").attr("data-replyPage"),
      success: function(rdata){
        $("#reply_list").attr("data-replyPage", rdata.replyPage+1);  // 개발자정의 속성 쓰기
        // alert(rdata.replyPage);
        // $('#reply_list').html(rdata.replyPage);
        var content="";

/*         {
           "list":[
             {"name":"JTBC","title":"물가상승","content":"5년간 물가 11%↑..담배·탄산음료·과자값 많이 올라"},
             {"name":"JTBC","title":"안종범/정호성","content":"'朴대통령 지시 따르고 받들어' 한목소리"}
           ],
           "replyPage":2
        }
 */        
        for(index=0; index <rdata.list.length; index++) {
          content += "<DIV style='width: 100%; padding: 10px;'>";
          content += rdata.list[index].title + "("+rdata.list[index].name+") <br>";
          content += rdata.list[index].content;
          content += "</DIV>";
        }
       
        $(content).appendTo("#reply_list");
      },
      error: function (request, status, error){
        console.log(error);  
      }
  });
}
 
</script>
 
 
</head> 
<body>
<DIV class='container'>
  
  <DIV id='reply_list' data-replyPage='1' 
         style='border: solid 1px #AAAAAA; margin: 0px auto; width: 70%; background-color: #FFFFFF;'>
    
  </DIV>  
  <DIV id='reply_list_btn' style='border: solid 1px #AAAAAA; margin: 0px auto; width: 70%;'>
    <button id='btn_add' style='width: 100%;' class='btn'>더보기 ▽</button>     
  </DIV>  
 
</DIV>
</body>
</html>