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
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>
  ${exhiVO.name }(${search_count }) 
      
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
    <span class='menu_divide' >│</span>    
    <A href="./list_by_exhino.do?exhino=${param.exhino }&now_page=${param.now_page == null ? 1 : param.now_page}&word=${param.word }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_exhino_grid.do?exhino=${param.exhino }&now_page=${param.now_page == null ? 1: param.now_page}&word=${param.word }">갤러리형</A>
  </ASIDE>
  
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_exhino.do'>
      <input type='hidden' name='exhino' value='${exhiVO.exhino }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-info btn-sm'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-info btn-sm'  onclick="location.href='./list_by_exhino.do?exhino=${exhiVO.exhino}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>

  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="galleryVO" items="${list }" varStatus="status">
      <c:set var="title" value="${galleryVO.title.trim() }" />
      <c:set var="content" value="${galleryVO.content.trim() }" />
      <c:set var="exhino" value="${galleryVO.exhino }" />
      <c:set var="galleryno" value="${galleryVO.galleryno }" />
      <c:set var="thumb1" value="${galleryVO.thumb1 }" />
      <c:set var="min" value="${galleryVO.min }" />
      <c:set var="max" value="${galleryVO.max }" />
      <c:set var="size1" value="${galleryVO.size1 }" />
      <c:set var="saleprice" value="${galleryVO.saleprice }" />
        
      <%-- 하나의 행에 이미지를 6개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.index % 6 == 0 && status.index != 0 }"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
        <c:if test="${status.index % 6 == 0 && status.index != 0 }"> 
      </c:if>
      </c:if>
        
      <div onclick="location.href='./read.do?galleryno=${galleryno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }'" class='hover'  
             style='width: 15%; height: 310px; float: left; margin: 0.83%; padding: 0.1%; background-color: #ebebeb; text-align: left;'>
        
        <c:choose> 
          <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
            <%-- registry.addResourceHandler("/gallery/storage/**").addResourceLocations("file:///" +  Gallery.getUploadDir()); --%>
            <img src="/gallery/storage/${thumb1 }" style="width: 100%; height: 210px;">
          </c:when>
          <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/gallery/images/none1.png -->
            <IMG src="/gallery/images/none1.png" style="width: 100%; height: 210px;">
          </c:otherwise>
        </c:choose>
        
        <strong>
          <c:choose> 
            <c:when test="${title.length() > 25 }"> <%-- 25 이상이면 25자만 출력, 공백:&nbsp; 6자 --%>
              ${title.substring(0, 25)}...
            </c:when>
            <c:when test="${title.length() <= 25 }">
              ${title}
            </c:when>
          </c:choose>
        </strong>
        <br>
        
        <td style='vertical-align: middle;'>
            <div style='font-size: 1..em;float: left;'>기간 : ${min } ~ ${max }</div><br>
            <div style='font-size: 1.0em;float: left;'>판매가 : &nbsp;</div>
            <div style='font-size: 1.0em;'><a style='color: #ff0000;' href="./read.do?galleryno=${galleryno }&word=${param.word }&now_page=${param.now_page == null ? 1 : param.now_page }"><fmt:formatNumber value="${saleprice}" pattern="#,###" /> </a></div>
          </td> 
        
      </div>
      
    </c:forEach>
  </div>
  
  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
  
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

