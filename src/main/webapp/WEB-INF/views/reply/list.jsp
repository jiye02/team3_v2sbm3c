<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
 <link rel="shortcut icon" href="/images/ex_top.png" /> <%-- /static 기준 --%>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 
</head>
 
<body>
<c:import url="/menu/top.do" />
  <DIV class="title_line">
    등록된 모든 댓글
  </DIV>
  <ASIDE class='aside_left'>
    <A href='./list.do'>모든 댓글</A>
  </ASIDE>
  <ASIDE class='aside_right'>
    <A href="javascript:location.reload();">새로고침</A>
<!--     <span class='menu_divide' > | </span> -->

  </ASIDE>
  
   
  <DIV class='menu_line' style='clear: both;'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 5%;"></col>
        <col style="width: 5%;"></col>
        <col style="width: 70%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 5%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>댓글<br>번호</th>
          <th style='text-align: center;'>글<br>번호</th>
          <th style='text-align: center;'>회원<br>아이디</th>
          <th style='text-align: center;'>내용</th>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="replyVO" items="${list }">
          <c:set var="galleryno" value="${replyVO.galleryno }" />
          <c:set var="replyno" value="${replyVO.replyno }" />
          
          <tr style='height: 50px;'> 
            <td style='text-align: center; vertical-align: middle;'>
              ${replyVO.replyno }
            </td> 
            <td style='text-align: center; vertical-align: middle;'>
              <A href='../gallery/read.do?galleryno=${galleryno }'>${no}</A>
            </td>
            <td style='text-align: center; vertical-align: middle;'>
              <A href='../member/read.do?memberno=${replyVO.memberno }'>${replyVO.memberno}</A>
            </td>
            <td style='text-align: left; vertical-align: middle;'>${replyVO.content}</td>
            <td style='text-align: center; vertical-align: middle;'>
              ${replyVO.rdate.substring(0, 10)}
            </td>
            <td style='text-align: center; vertical-align: middle;'>
              <a href="./delete.do?replyno=${replyVO.replyno}"><img src="./images/delete.png" title="삭제"  border='0' /></a>
                        
            </td>
            
           
          </tr>
          
          
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
   