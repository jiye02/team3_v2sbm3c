<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
<link rel="shortcut icon" href="/images/ex_top.png" />
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  ${exhiVO.name }
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
  
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.admin_id != null }">
      <%--
      http://localhost:9093/gallery/create.do?exhino=1
      http://localhost:9093/gallery/create.do?exhino=2
      http://localhost:9093/gallery/create.do?exhino=3
      --%>
      <A href="./create.do?exhino=${exhiVO.exhino }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>

<!--     <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead> -->
    
    <tbody>
      <c:forEach var="galleryVO" items="${list}">
        <c:set var="title" value="${galleryVO.title }" />
        <c:set var="content" value="${galleryVO.content }" />
        <c:set var="exhino" value="${galleryVO.exhino }" />
        <c:set var="galleryno" value="${galleryVO.galleryno }" /> 
        <c:set var="thumb1" value="${galleryVO.thumb1 }" />
        <c:set var="min" value="${galleryVO.min }" />
        <c:set var="max" value="${galleryVO.max }" />
        
         <tr style="height: 112px;" onclick="location.href='./read.do?galleryno=${galleryno }'" class='hover'>
          <td style='vertical-align: middle; text-align: center; '>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Gallery.getUploadDir()); --%>
                <img src="/gallery/storage/${thumb1 }" style="width: 120px; height: 90px;">
              </c:when>
              <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/gallery/images/none1.png -->
                <IMG src="/gallery/images/none1.png" style="width: 120px; height: 90px;">
              </c:otherwise>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'>
            <div style='font-weight: bold;'>${title }</div>
            <c:choose> 
              <c:when test="${content.length() > 160 }"> <%-- 160자 이상이면 160자만 출력 --%>
                  ${content.substring(0, 160)}.....
              </c:when>
              <c:when test="${content.length() <= 160 }">
                  ${content}
              </c:when>
            </c:choose>
          </td> 
          <td style='vertical-align: middle; text-align: center;'>
            <A href="/gallery/map.do?exhino=${exhino }&galleryno=${galleryno}" title="지도"><IMG src="/gallery/images/map.png" class="icon"></A>
            <A href="/gallery/youtube.do?exhino=${exhino }&galleryno=${galleryno}" title="Youtube"><IMG src="/gallery/images/youtube.png" class="icon"></A>
          </td>
        </tr>
        
      </c:forEach>

    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

