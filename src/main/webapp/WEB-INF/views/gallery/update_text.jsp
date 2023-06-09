<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="exhino" value="${exhiVO.exhino }" />

<c:set var="galleryno" value="${galleryVO.galleryno }" />
<c:set var="title" value="${galleryVO.title }" />
<c:set var="content" value="${galleryVO.content }" />
<c:set var="word" value="${galleryVO.word }" />
 
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
 
<DIV class='title_line'> ${exhiVO.name } > ${title } > 수정</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do?exhino=${exhino }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_exhino.do?exhino=${exhino }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_exhino_grid.do?exhino=${exhino }">갤러리형</A>
  </ASIDE> 
  
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_exhino.do'>
      <input type='hidden' name='exhino' value='${exhino }'>  <%-- 게시판의 구분 --%>
      
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
        <button type='button' class='btn btn-info btn-sm' 
                    onclick="location.href='./list_by_exhino.do?exhino=${exhiVO.exhino}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  
  <FORM name='frm' method='POST' action='./update_text.do'>
    <input type="hidden" name="exhino" value="${exhino }">
    <input type="hidden" name="galleryno" value="${galleryno }">
    <input type="hidden" name="now_page" value="${param.now_page }">
    
    <div>
       <label>제목</label>
       <input type='text' name='title' value='${title }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'>${content }</textarea>
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='word' value="${word }" required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    <div>
      <label>정가(100원 단위)</label>
      <input type="number" name="price" value="6000" required="required" 
                min="0" max="100000000" step="100" class="form-control" style="width: 100%;">
    </div>
    <div>
      <label>할인률(%)</label>
      <input type="number" name="dc" value="5" required="required" 
                min="0" max="100" step="1" class="form-control" style="width: 100%;">
    </div>
    
    <div>
      <label>판매가(100원 단위)</label>
      <input type="number" name="saleprice" value="5700" required="required" 
                min="0" max="100000000" step="100" class="form-control" style="width: 100%;">
    </div>
    <div>
       <label>전시 시작일</label>
       <input type="date" name='min' value="defaultValue">
    </div>
    <div>
       <label>전시 종료일</label>
       <input type="date" name='max' value="defaultValue">
    </div>
    
    <c:choose>
      <c:when test="${sessionScope.admin_id == null }">
        <div>
          <label>패스워드</label>
          <input type='password' name='passwd' value='' required="required" 
                    class="form-control" style='width: 50%;'>
        </div>
      </c:when>
      <c:otherwise>
      </c:otherwise>
    </c:choose>
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="location.href='./read.do?exhino=${param.exhino}&galleryno=${galleryno }'" class="btn btn-primary">취소</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

