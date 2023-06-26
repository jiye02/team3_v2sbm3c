<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
 <link rel="shortcut icon" href="/images/ex_top.png" /> <%-- /static 기준 --%>
<%-- /static/css/style.css --%> 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head> 
<body>
<c:import url="/menu/top.do" />

<DIV class='title_line'>알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.code == 'payment_success'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_success">${param.mname }님 구매에 감사합니다.</span>
          </LI>  
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='./login.do?id=${param.id}'"
                         class="btn btn-primary">로그인</button>
          </LI> 
        </c:when>
        
        <c:when test="${param.code == 'payment_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">구매에 실패했습니다. 다시 시도해주세요.</span>
          </LI>                                                                      
        </c:when>

        
        <c:otherwise>
          <LI class='li_none_left'>
            <span class="span_fail">알 수 없는 오류로 작업에 실패했습니다.</span>
          </LI>
          <LI class='li_none_left'>
            <span class="span_fail">다시 시도해주세요.</span>
          </LI>
        </c:otherwise>
        
      </c:choose>
      <LI class='li_none'>
        <br>
        <c:choose>
            <c:when test="${param.cnt == 0 }">
                <button type='button' onclick="history.back()" class="btn btn-info">다시 시도</button>    
            </c:when>
        </c:choose>

      </LI>
    </UL>
  </fieldset>

</DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>