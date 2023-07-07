<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>Resort world</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
    <c:otherwise>
     <DIV
            style='width: 100%; margin: 30px auto; text-align: center;'>
            <%-- 로그인된 경우 추천 --%>
            <c:if test="${sessionScope.id != null}">
              <DIV
                style='width: 70%; margin: 10px auto; text-align: left;'>
                <h4>${sessionScope.mname}님을 위한 추천 상품</h4>
                <c:import url="/gallery/recommend_jjim.do" />
                <%-- 좋아요가 높은 상품 --%>
              </DIV>

              <DIV style="clear: both; height: 20px;"></DIV>

              <DIV
                style='width: 70%; margin: 10px auto; text-align: left;'>
                <h4>가격이 저렴한 상품</h4>
                <c:import url="/gallery/recommend_jjim.do" />
              </DIV>

              <DIV style="clear: both; height: 20px;"></DIV>

              <DIV
                style='width: 70%; margin: 10px auto; text-align: left;'>
                <h4>최신 상품 추천 상품</h4>
                <c:import url="/gallery/recommend_jjim.do" />
              </DIV>
            </c:if>
          </DIV>
    </c:otherwise>
 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
