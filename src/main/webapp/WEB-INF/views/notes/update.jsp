<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="notesno" value="${notesVO.notesno }" />
<c:set var="title" value="${notesVO.title }" />
<c:set var="content" value="${notesVO.content }" />
<c:set var="word" value="${notesVO.word }" />
<c:set var="topview" value="${notesVO.topview }" /> <%-- topview 값을 읽어옴 --%>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> <!-- top 메뉴 drop down 버튼 스크립트를 작동하게 함 -->
    
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'> 
<A href="./list_all_search_paging.do?now_page=${param.now_page }" class='title_link'>공지사항</A> 
> ${title } > 수정</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  
  
  
  <DIV class='menu_line'></DIV>
  
  <FORM name='frm' method='POST' action='./update_text.do'>
    <input type="hidden" name="notesno" value="${notesno }">
    <!-- <input type="hidden" name="now_page" value="${param.now_page }">  -->
    
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
       <label>상단 노출 여부
       <input type='checkbox' name='topview' value="Y" <%-- 수정: 상단 노출 체크박스의 값을 'Y'로 지정 --%>
                 <%-- 상단 노출 체크박스가 체크된 경우에는 'Y', 체크되지 않은 경우에는 'N' --%>
                 <%-- topview 변수의 값을 'Y'로 설정해주어야 합니다. --%>
                 <%-- 예를 들어, ${topview == 'Y' ? 'checked' : ''} 로 수정할 수 있습니다. --%>
                 ${topview == 'Y' ? 'checked' : ''} class="form-control" style='width: 100%;'></label>
    </div>  
    
    
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="location.href='./read.do?notesno=${notesno }'" class="btn btn-primary">취소</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
