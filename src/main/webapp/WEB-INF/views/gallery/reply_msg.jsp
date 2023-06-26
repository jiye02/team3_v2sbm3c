<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
<link rel="shortcut icon" href="/images/ex_top.png" />
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head> 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>알림</DIV>
  
  <ASIDE class='aside_left'>
    ${exhiVO.name } > 답변 등록
  </ASIDE>
  <ASIDE class='aside_right'>
    <A href='./list.do?exhino=${param.exhino }'>목록</A>
    <!-- <span class='menu_divide' >│</span> --> 
  </ASIDE> 
  
  <div class='menu_line'></div>
   
  <DIV class='message'>
    <fieldset class='fieldset_basic'>
      <UL>
        <c:choose>
          <c:when test="${param.cnt == 1}">
            <LI class='li_none'>
              <span class='span_success'>답변을 등록했습니다.</span>
            </LI>
          </c:when>
          <c:otherwise>
            <LI class='li_none'>
              <span class='span_fail'>답변 등록에 실패했습니다.</span>
            </LI>
          </c:otherwise>
        </c:choose>
        <LI class='li_none'>
          <br>
          <c:choose>
            <c:when test="${param.cnt == 1 }">
              <button type='button' 
                           onclick="location.href='../attachfile/create.do?exhino=${param.exhino}&galleryno=${param.galleryno }'"
                           class="btn btn-info">첨부파일 등록</button>     
              <button type='button' 
                           onclick="location.href='./map_create.do?exhino=${param.exhino}&galleryno=${param.galleryno }'"
                           class="btn btn-info">Map 등록</button>                
              <button type='button' 
                           onclick="location.href='./youtube_create.do?exhino=${param.exhino}&galleryno=${param.galleryno }'"
                           class="btn btn-info">Youtube 등록</button>                
              <button type='button' 
                           onclick="location.href='./mp3_create.do?exhino=${param.exhino}&galleryno=${param.galleryno }'"
                           class="btn btn-info">MP3 등록</button>         
              <button type='button' 
                           onclick="location.href='./mp4_create.do?exhino=${param.exhino}&galleryno=${param.galleryno }'"
                           class="btn btn-info">MP4 등록</button>
              <br>         
              <button type='button' 
                           onclick="location.href='./create.do?exhino=${param.exhino}'"
                           class="btn btn-info">새로운 컨텐츠 등록</button>
            </c:when>
            <c:otherwise>
              <button type='button' 
                           onclick="history.back();"
                           class="btn btn-info">다시 시도</button>
            </c:otherwise>
          </c:choose>
                      
          <button type='button' 
                      onclick="location.href='./list.do?exhino=${param.exhino}'"
                      class="btn btn-info">목록</button>
        </LI>
       </UL>
    </fieldset>
   
  </DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

