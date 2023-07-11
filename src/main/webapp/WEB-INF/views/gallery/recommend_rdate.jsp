<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
<link rel="shortcut icon" href="/images/ex_top.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

</head> 
 
<body>
 
<DIV class='title_line'>
  <img src="/gallery/images/new.png" class="icon1" style='margin-left:5px; margin-right:4px; margin-bottom: 2px; height: 30px;'> 
<span style='font-size: 20px; vertical-align: middle; line-height: 20px;'>최신 등록 상품</span>
</DIV>

<DIV class='content_body'>
    <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="galleryVO" items="${list_rdate }" varStatus="status">
      <c:set var="title" value="${galleryVO.title.trim() }" />
      <c:set var="content" value="${galleryVO.content.trim() }" />
      <c:set var="exhino" value="${galleryVO.exhino }" />
      <c:set var="galleryno" value="${galleryVO.galleryno }" />
      <c:set var="thumb1" value="${galleryVO.thumb1 }" />
      <c:set var="size1" value="${galleryVO.size1 }" />
        
      <%-- 하나의 행에 이미지를 5개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.index % 5 == 0 && status.index != 0 }"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
      </c:if>
        
      <!-- 4기준 하나의 이미지, 24 * 4 = 96% -->
      <!-- 5기준 하나의 이미지, 19.2 * 5 = 96% -->
      <div onclick="location.href='/gallery/read.do?galleryno=${galleryno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" class='hover'  
             style='width: 19%; height: 216px; float: left; margin: 0.5%; padding: 0.1%; background-color: #EEEFFF; text-align: left;'>
        
        <c:choose> 
          <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
            <%-- registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Contents.getUploadDir()); --%>
            <img src="/gallery/storage/${thumb1 }" style="width: 100%; height: 120px;">
          </c:when>
          <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/gallery/images/none1.png -->
            <IMG src="/gallery/images/none1.png" style="width: 100%; height: 140px;">
          </c:otherwise>
        </c:choose>
        
        <strong>
          <c:choose> 
            <c:when test="${title.length() > 25 }"> <%-- 25 이상이면 25자만 출력, 공백:  6자 --%>
              ${title.substring(0, 25)}.....
            </c:when>
            <c:when test="${title.length() <= 25 }">
              ${title}
            </c:when>
          </c:choose>
        </strong>
        <br>
        
        <div style='font-size: 0.95em; word-break: break-all;'>
          <c:choose>         
            <c:when test="${content.length() > 20 }"> <%-- 55 이상이면 55자만 출력 --%>
              ${content.substring(0, 20)}.....
            </c:when>
            <c:when test="${content.length() <= 20 }">
              ${content}
            </c:when>
          </c:choose>
        </div>
        
      </div>
      
    </c:forEach>
  </div>
  
  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
  </div>
</body>
 
</html>
