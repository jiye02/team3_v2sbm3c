<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>ART wave</title>
 <link rel="shortcut icon" href="/images/ee.png" /> <%-- /static 기준 --%>
 
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head> 
 
<body>
<c:import url="/menu/top.do" />
 

<DIV class='content_body'>
<DIV>
    <img src="/menu/images/bell.png" class="icon1" style='margin-left:10px; margin-right:10px; margin-bottom: 7px;'> <span style='font-size: 30px;'>전체 공지사항</span>
</DIV> 

  <ASIDE class="aside_right">
  <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
  
    <c:if test="${sessionScope.admin_id != null }">
     <A href="./create.do"> 공지사항 등록</A>
    <span class='menu_divide' >│</span>
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_search.do'>
      <input type='hidden'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type="submit" class="btn btn-custom btn-sm">검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type="button" class="btn btn-custom btn-sm" onclick="location.href='./list_by_search.do'">검색 취소</button>
      </c:if>
          <style>
          .btn-custom {
            background-color: #B6EADA; /* 원하는 색상 코드로 변경 */
            color: white; /* 버튼 텍스트 색상 설정 (선택적) */
          }
          </style>
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped">
    <colgroup>
    <c:choose>
        <c:when test="${sessionScope.admin_id != null }">
      <col style="width: 10%; "></col>
      <col style="width: 50%;"></col>
      <col style="width: 30%;"></col>
      <col style="width: 10%;"></col>
        </c:when>
    </c:choose>
    
    </colgroup>

<thead>
  <tr>
    <th style="text-align: center;">번호</th>
    <th style="text-align: center;">제목</th>
    <th style="text-align: center;">날짜</th>
    <th style="text-align: center;">조회수</th>
  </tr>
</thead>

 
 <tbody>
<c:forEach var="notesVO" items="${list}" varStatus="loop">
        <c:set var="title" value="${notesVO.title }" />
        <c:set var="content" value="${notesVO.content }" />
        <c:set var="notesno" value="${notesVO.notesno }" />
        <c:set var="rdate" value="${notesVO.rdate }" />
        <c:set var="cnt" value="${notesVO.cnt }" />   
         
  <tr style="height: 50px;">
    <td style='vertical-align: middle; text-align: center;'>
      <a href="./read.do?notesno=${notesno}&now_page=${param.now_page == null?1:now_page }">
        ${list.size() - loop.index}
      </a>
    </td>
      <td style='vertical-align: middle; text-align: center;'>
        <a href="./read.do?notesno=${notesno}&now_page=${param.now_page == null?1:now_page }" style="display: block;">
          <div style='font-weight:bold;'>${title}</div>
        </a>
      </td>
      <td style='vertical-align: middle; text-align: center;'>
        <div>${rdate}</div>
      </td>
      <td style='vertical-align: middle; text-align: center;'>
        <div>${cnt}</div>
      </td>
    </tr>
  </c:forEach>
</tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>