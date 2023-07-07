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

 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
  
  function delete_func(jjimno) {  // GET -> POST 전송, 상품 삭제
    var frm = $('#frm_post');
    frm.attr('action', './delete.do');
    $('#jjimno',  frm).val(jjimno);
    
    frm.submit();
  }   


</script>

<style type="text/css">

    
</style>
 
</head> 
 
<body>
<c:import url="/menu/top.do" />

<%-- GET -> POST: 수량 변경, 상품 삭제용 폼 --%>
<form name='frm_post' id='frm_post' action='' method='post'>
  <input type='hidden' name='jjimno' id='jjimno'>
  <input type='hidden' name='cnt' id='cnt'>
</form>
 
<DIV class='title_line'>♥ 찜 목록 ♥</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <%-- 
    http://localhost:9091/jjim/list_by_memberno.do
    http://localhost:9091/jjim/list_by_memberno.do?exhino=
    http://localhost:9091/jjim/list_by_memberno.do?exhino=4  <- 이런 패턴만 링크 출력
    --%>
    <c:if test="${param.exhino != null and param.exhino != ''}"> 
      <A href="/gallery/list_by_exhino_search_paging.do?exhino=${param.exhino }">쇼핑 계속하기</A>
      <span class='menu_divide' >│</span>  
    </c:if>
 
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>

  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 40%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col> <%-- 수량 --%>
      <col style="width: 10%;"></col> <%-- 합계 --%>
      <col style="width: 10%;"></col>
    </colgroup>
    <%-- table 컬럼 --%>
<!--     <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>상품명</th>
        <th style='text-align: center;'>정가, 할인률, 판매가, 포인트</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead> -->
    
    <%-- table 내용 --%>
    <tbody>
      <c:choose>
        <c:when test="${list.size() > 0 }"> <%-- 상품이 있는지 확인 --%>
          <c:forEach var="jjimVO" items="${list }">  <%-- 상품 목록 출력 --%>
            <c:set var="jjimno" value="${jjimVO.jjimno }" />
            <c:set var="galleryno" value="${jjimVO.galleryno }" />
            <c:set var="title" value="${jjimVO.title }" />
            <c:set var="thumb1" value="${jjimVO.thumb1 }" />
            <c:set var="memberno" value="${jjimVO.memberno }" />
            <c:set var="rdate" value="${jjimVO.rdate }" />
            
            <tr> 
              <td style='vertical-align: middle; text-align: center;'>
                <c:choose>
                  <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                    <%-- /static/gallery/storage/ --%>
                    <a href="/gallery/read.do?galleryno=${galleryno}"><IMG src="/gallery/storage/${thumb1 }" style="width: 120px; height: 80px;"></a> 
                  </c:when>
                  <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                    ${galleryVO.file1}
                  </c:otherwise>
                </c:choose>
              </td>  
              <td style='vertical-align: middle;'>
                <a href="/gallery/read.do?galleryno=${galleryno}"><strong>${title}</strong></a> 
              </td> 
              <td style='vertical-align: middle; text-align: center;'>
              </td>
              <td style='vertical-align: middle; text-align: center;'>
              <%-- 레코드에 따라 ID를 고유하게 구분할 목적으로 id 값 생성, 예) 1_cnt, 2_cnt, c_cnt... --%>
                
              </td>
              <td style='vertical-align: middle; text-align: center;'>
              </td>
              <td style='vertical-align: middle; text-align: center;'>
                
                <A type='button' onclick="location.href='/jjim/delete.do?memberno=${memberno}&galleryno=${galleryno }'"><IMG src="/jjim/images/delete.png" class="icon"></A>
                
              </td>
            </tr>
          </c:forEach>
        
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="6" style="text-align: center; font-size: 1.3em;">찜 목록이 없습니다.</td>
          </tr>
        </c:otherwise>
      </c:choose>
      
      
    </tbody>
  </table>

</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>