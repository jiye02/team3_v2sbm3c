<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>ART wave</title>
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> <!-- top 메뉴 drop down 버튼 스크립트를 작동하게 함 -->
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
<A href="list_all_search_paging.do?word=${word }&now_page=1" class='title_link'>공지사항 </a>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right" style="padding-bottom: 10px;">
    <c:if test="${sessionScope.rankno == 1}">
     <A href="./create.do">등록</A>
     <span class='menu_divide' >│</span>
    </c:if>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_all_search_paging.do'>
      <input type='hidden' name='now_page' value='1'>  <%-- 검색기본 시작 페이지 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%; ' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class="btn btn-secondary btn-sm">검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-info btn-sm'
                     onclick="location.href='./list_all_search_paging.do?now_page=1&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>

  <DIV class='menu_line'></DIV>
  <c:if test="${notesVO.getNow_page() == 1 }"> <!-- 1페이지에서만 상단노출 출력 -->
    <p class="text-center" style='font-size:200%;'> 중요 공지사항 </p>
    <meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 65%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
      </colgroup>
  
      
      <tbody>
        <c:forEach var="notesVO" items="${list_t }">
          <c:set var="title" value="${notesVO.title }" />
          <c:set var="content" value="${notesVO.content }" />
          <c:set var="notesno" value="${notesVO.notesno }" />
          <c:set var="rdate" value="${notesVO.rdate }" />
          <c:set var="memberno" value="${notesVO.memberno }" />
          <c:set var="topview" value="${notesVO.topview }" /> <%-- 글의 topview 값을 가져옴 --%>
  
  
          <c:if test="${topview == 'Y' }"> <%-- topview가 'Y'인 경우 상단에 고정 글을 출력함 --%>
            <tr style="height:50px;">
              <td style='vertical-align: middle; text-align: center; font-size: 17px;'>
              ${notesno}
              </td>
              <td style='vertical-align: middle;'>
                <a href="./read.do?notesno=${notesno}&now_page=${param.now_page}&word=${param.word}"><strong>${title}</strong>  
                </a> 
              </td> 
              <td style='vertical-align: middle; text-align: center;'>
              ${rdate }
              </td>
               <td style='vertical-align: middle; text-align: center; font-weight:bold;'>
              ${f.apply(memberno) }
              </td>
            </tr>
          </c:if> 
        </c:forEach>
      </tbody>
    </table>
  </c:if>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 5%;"></col>
      <col style="width: 65%;"></col>
      <col style="width: 15%;"></col>
      <col style="width: 15%;"></col>
    </colgroup>

    <thead>
      <tr >
        <th style='text-align: center;'>번호</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>작성일</th>
        <th style='text-align: center;'>작성자</th>
      </tr>
    
    </thead>
    
    <tbody>
      <c:forEach var="notesVO" items="${list }">
        <c:set var="title" value="${notesVO.title }" />
        <c:set var="content" value="${notesVO.content }" />
        <c:set var="notesno" value="${notesVO.notesno }" />
        <c:set var="rdate" value="${notesVO.rdate }" />
        <c:set var="memberno" value="${notesVO.memberno }" />
        <c:set var="topview" value="${notesVO.topview }" /> <%-- 글의 topview 값을 가져옴 --%>

        <c:if test="${topview == 'N' }"> <%-- topview가 'N'인 경우 하단에 고정 글을 출력함 --%>
          <tr style="height:50px;">
            <td style='vertical-align: middle; text-align: center; font-size: 17px;'>
            ${notesno}
            </td>
            <td style='vertical-align: middle;'>
              <a href="./read.do?notesno=${notesno}&now_page=${param.now_page}&word=${param.word}"><strong>${title}</strong>  
              </a> 
            </td> 
            <td style='vertical-align: middle; text-align: center;'>
            ${rdate }
            </td>
             <td style='vertical-align: middle; text-align: center; font-weight:bold;'>
            ${f.apply(memberno) }
            </td>
          </tr>
        </c:if>
        
        
      </c:forEach>


    </tbody>
  </table>
   <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>