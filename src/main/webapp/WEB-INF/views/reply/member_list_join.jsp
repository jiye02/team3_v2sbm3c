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
  <ASIDE class='aside_left'>
    <A href='./list.do'>내가 작성한 댓글</A>
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
        <c:forEach var="replyMemberVO" items="${list }">
          <c:set var="replyno" value="${replyMemberVO.replyno }" />
          <c:set var="galleryno" value="${replyMemberVO.galleryno }" />
          <c:set var="memberno" value="${replyMemberVO.memberno }" />
          <c:set var="id" value="${replyMemberVO.id }" />
          <c:set var="content" value="${replyMemberVO.content }" />
          <c:set var="rdate" value="${replyMemberVO.rdate }" />
          
          <tr style='height: 50px;'> 
            <td style='text-align: center; vertical-align: middle;'>
              ${replyno }
            </td> 
            <td style='text-align: center; vertical-align: middle;'>
              <A href='../gallery/read.do?galleryno=${galleryno }'>${galleryno}</A>
            </td>
            <td style='text-align: center; vertical-align: middle;'>
              <A href='../member/read.do?memberno=${memberno }'>${id}</A>
            </td>
            <td style='text-align: left; vertical-align: middle;'>${content}</td>
            <td style='text-align: center; vertical-align: middle;'>
              ${rdate.substring(0, 10)}
            </td>
            <td style='text-align: center; vertical-align: middle;'>
              <a href="./member_delete.do?replyno=${replyno}"><img src="./images/delete.png" title="삭제"  border='0' /></a>
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
   