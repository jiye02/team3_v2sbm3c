<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />

<DIV class='title_line'>전체 카테고리 > [${recomplaceVO.recname}] 삭제</DIV>

<DIV class='content_body'>
  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <div class="msg_warning">카테고리를 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./read_delete.do'>
      <!-- 삭제할 글 번호 -->
      <input type='hidden' name='recno' id='recno' value="${recomplaceVO.recno}">
      
      <label>카테고리</label>: ${recomplaceVO.recname } 
       
      <button type="submit" id='submit'>삭제</button>
      <button type="button" onclick="location.href='./list_all.do'">취소</button>
    </FORM>
  </DIV>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 35%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 15%;'/>
      <col style='width: 15%;'/>
      <col style='width: 15%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">카테고리</TH>
      <TH class="th_bs">카테고리 이름</TH>
      <TH class="th_bs">자료수</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">수정일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="recomplaceVO" items="${list }">
      <c:set var="recno" value="${recomplaceVO.recno }" />
      <c:set var="name" value="${recomplaceVO.recname }" />
      <c:set var="cnt" value="${recomplaceVO.cnt }" />
      <c:set var="rdate" value="${recomplaceVO.rdate.substring(0, 16) }" />
      <c:set var="udate" value="${recomplaceVO.udate.substring(0, 16) }" />
      <c:set var="seqno" value="${recomplaceVO.seqno }" />
      <c:set var="visible" value="${recomplaceVO.visible }" />

      <TR>
        <TD class="td_bs">${recno} / ${visible}</TD>
        <TD class="td_bs_left">${recname}</TD>
        <TD class="td_bs">${cnt }</TD>
        <TD class="td_bs">${rdate}</TD>
        <TD class="td_bs">${udate }</TD>
        <TD class="td_bs">
          <A href="./read_update.do?recno=${recno}" title="수정"><IMG src="/recomplace/images/update.png" class="icon"></A>
          <A href="./read_delete.do?recno=${recno}" title="삭제"><IMG src="/recomplace/images/delete.png" class="icon"></A>
          <A href="./update_seqno_up.do?recno=${recno}" title="출력 순서 올림"><IMG src="/recomplace/images/up.png" class="icon"></A>
          <A href="./update_seqno_down.do?recno=${recno}" title="출력 순서 내림"><IMG src="/recomplace/images/down.png" class="icon"></A>
          
          <c:choose>
            <c:when test="${visible == 'Y' }">
              <A href="./update_visible_n.do?recno=${recno }" title="출력 모드 N로 변경"><IMG src="/recomplace/images/show.png" class="icon"></A>
            </c:when>
            <c:when test="${visible == 'N' }">
              <A href="./update_visible_y.do?recno=${recno }" title="출력 모드 Y로 변경"><IMG src="/recomplace/images/hide.png" class="icon"></A>            </c:when>
          </c:choose>
          
        </TD>   
      </TR> 
      
    </c:forEach>
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 