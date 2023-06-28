<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
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
 
<DIV class='title_line'>${exhiVO.name } > 글 등록</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <c:if test="${sessionScope.admin_id != null }">
      <A href="./create.do?exhino=${exhiVO.exhino }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_exhino_search_paging.do?exhino=${exhiVO.exhino }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_exhino_grid.do?exhino=${exhiVO.exhino }">갤러리형</A>
  </ASIDE> 
  
  <%-- 검색 폼 --%>
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_exhino_search.do'>
      <input type='hidden' name='exhino' value='${exhiVO.exhino }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list_by_exhino_search.do?exhino=${exhiVO.exhino}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  <%--등록 폼 --%>
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="exhino" value="${param.exhino }">
    <%-- <input type="hidden" name="adminno" value="1"> --%> <%-- 관리자 개발후 변경 필요 --%>
    
    <div>
       <label>상품명</label>
       <input type='text' name='title' value='' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>상품 설명</label>
       <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
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
       <label>전시 시작일</label>
       <input type="date" name='min' value="defaultValue">
    </div>
    <div>
       <label>전시 종료일</label>
       <input type="date" name='max' value="defaultValue">
    </div>
    <div>
      <label>판매가(100원 단위)</label>
      <input type="number" name="saleprice" value="5700" required="required" 
                min="0" max="100000000" step="100" class="form-control" style="width: 100%;">
    </div>
    <div>
      <label>포인트(10원 단위)</label>
      <input type="number" name="point" value="300" required="required" 
                min="0" max="100000000" step="10" class="form-control" style="width: 100%;">
    </div>
    <div>
       <label>검색어</label>
       <input type='text' name='word' value='' required="required" 
                 class="form-control" style='width: 100%;'>
    </div>   
    <div>
       <label>이미지</label>
       <input type='file' class="form-control" name='file1MF' id='file1MF' 
                 value='' placeholder="파일 선택">
    </div>
    <div>
       <label>패스워드</label>
       <input type='password' name='passwd' value='1234' required="required" 
                 class="form-control" style='width: 50%;'>
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./list_by_exhino_search_paging.do?exhino=${param.exhino}'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

