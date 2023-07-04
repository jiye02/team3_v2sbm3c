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
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    

</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  <img src="/gallery/images/상단.png" class="icon1" style='margin-left:5px; margin-right:4px; margin-bottom: 2px; height: 30px;'> 
<span style='font-size: 20px; vertical-align: middle; line-height: 20px;'>전체 글 목록</span>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE>

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <c:choose>
        <c:when test="${sessionScope.admin_id != null }">
          <col style="width: 10%;"></col>
          <col style="width: 80%;"></col>
          <col style="width: 10%;"></col>        
        </c:when>
        <c:otherwise>
          <col style="width: 10%;"></col>
          <col style="width: 90%;"></col>
        </c:otherwise>
      </c:choose>
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
        <c:set var="saleprice" value="${galleryVO.saleprice }" />
        <c:set var="exhino" value="${galleryVO.exhino }" />
        <c:set var="galleryno" value="${galleryVO.galleryno }" />
        <c:set var="thumb1" value="${galleryVO.thumb1 }" />
        <c:set var="min" value="${galleryVO.min }" />
        <c:set var="max" value="${galleryVO.max }" />
        
        
        <tr style="height: 112px;" onclick="location.href='./read.do?galleryno=${galleryno }&now_page=${param.now_page == null ? 1 : param.now_page}'" class='hover'>
          <td style='vertical-align: middle; text-align: center; '>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Gallery.getUploadDir()); --%>
                <img src="/gallery/storage/${thumb1 }" style="width: 120px; height: 90px; ">
              </c:when>
              <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력 : /static/gallery/images/none1.png -->
                <IMG src="/gallery/images/none1.png" style="width: 120px; height: 90px;">
              </c:otherwise>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;' >
            <div style='font-weight: bold;'><a href="./read.do?galleryno=${galleryno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }">${title }</a></div>
            <div>전시 기간 : ${min } ~ ${max }</div>
            <div style='font-size: 1.0em;float: left;'>판매가 : &nbsp;</div> 
            <div style='font-size: 1.0em;'><a style='color: #ff0000;' href="./read.do?galleryno=${galleryno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }"><fmt:formatNumber value="${saleprice}" pattern="#,###" /> </a></div>
          </td>
          
          <c:choose>
            <c:when test="${sessionScope.admin_id != null }"> 
              <td style='vertical-align: middle; text-align: center;'>
                <A href="/gallery/map.do?exhino=${exhino }&galleryno=${galleryno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="지도"><IMG src="/gallery/images/map.png" class="icon"></A>
                <A href="/gallery/youtube.do?exhino=${exhino }&galleryno=${galleryno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="Youtube"><IMG src="/gallery/images/youtube.png" class="icon"></A>
                <A href="/gallery/delete.do?exhino=${exhino }&galleryno=${galleryno}&now_page=${param.now_page == null ? 1 : param.now_page}" title="삭제"><IMG src="/gallery/images/delete.png" class="icon"></A>
              </td>
            </c:when>
          </c:choose>
                    
        </tr>
        
      </c:forEach>

    </tbody>
  </table>
</DIV>

<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
