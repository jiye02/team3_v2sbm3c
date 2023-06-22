<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art world</title>
<link rel="shortcut icon" href="/images/ex_top.png" />
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
  $(function(){
 
  });
</script>
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>
    관리자 목록
  </DIV>

  <DIV class='content_body'>

    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
      <span class='menu_divide' >│</span> 
      <A href='./create.do'>관리자 등록</A>
      <span class='menu_divide' >│</span> 
      <A href='./list.do'>목록</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
    
   
    <table class="table table-striped" style='width: 133.5%;'>
    <colgroup>
      <col style='width: 5%;'/>
      <col style='width: 10%;'/>
      <col style='width: 15%;'/>
      <col style='width: 15%;'/>
      <col style='width: 30%;'/>
      <col style='width: 15%;'/>
      <col style='width: 10%;'/>
    </colgroup>
    <TR>
      <TH class='th_bs'>등급 </TH>
      <TH class='th_bs'>ID</TH>
      <TH class='th_bs'>성명</TH>
      <TH class='th_bs'>등록일</TH>
      <TH class='th_bs'>기타</TH>
    </TR>
   
    <c:forEach var="adminVO" items="${list }">
      <c:set var="adminno" value ="${adminVO.adminno}" />
      <c:set var="grade" value ="${adminVO.grade}" />
      <c:set var="id" value ="${adminVO.id}" />
      <c:set var="mname" value ="${adminVO.mname}" />
      <c:set var="mdate" value ="${adminVO.mdate}" />
       
    <TR>
      <TD class= 'td_basic'><img src='/member/images/admin.png' title="관리자" class="icon">
      <TD class='td_basic'><A href="./read.do?adminno=${adminno}">${id}</A></TD>
      <TD class='td_basic'><A href="./read.do?adminno=${adminno}">${mname}</A></TD>
      </TD>
      <TD class='td_basic'>${mdate.substring(0, 10)}</TD> <%-- 년월일 --%>
      <TD class='td_basic'>
        <A href="./passwd_update.do?adminno=${adminno}"><IMG src='/member/images/passwd.png' title='패스워드 변경' class="icon"></A>
        <A href="./read.do?adminno=${adminno}"><IMG src='/member/images/update.png' title='수정' class="icon"></A>
        <A href="./delete.do?adminno=${adminno}"><IMG src='/member/images/delete.png' title='삭제' class="icon"></A>
      </TD>
      
    </TR>
    </c:forEach>
    
  </TABLE>
   
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./create.do'" class="btn btn-info">등록</button>
    <button type='button' onclick="location.reload();" class="btn btn-info">새로 고침</button>
  </DIV>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

