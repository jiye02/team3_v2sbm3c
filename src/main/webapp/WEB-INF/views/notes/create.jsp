<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
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
<A href="./list_all.do?now_page=1" class='title_link'>공지사항</A> 
> 새 공지사항 등록</DIV>

<DIV class='content_body'>
  
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
    <input type="hidden" name="adminno" value="${sessionScope.adminno}">
    <input type="hidden" name="file1" value="" id="file1">
    <input type="hidden" name="file1saved" value="" id="file1saved">
    <input type="hidden" name="size1" value="0" id="size1">
    
    <div>
       <label>제목</label>
       <input type='text' name='title' value='' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='content' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
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
       <label>상단 노출 여부
       <input type='checkbox' name='topview' value='Y' 
                 class="form-control" style='width: 100%;'></label>
    </div>  
    
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./list_all.do=${param.now_page }'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

