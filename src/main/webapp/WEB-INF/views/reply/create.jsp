<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="galleryno" value="${galleryVO.galleryno }" />
<c:set var="exhino" value="${galleryVO.exhino }" />
<c:set var="title" value="${galleryVO.title }" />        
<c:set var="file1" value="${galleryVO.file1 }" />
<c:set var="file1saved" value="${galleryVO.file1saved }" />
<c:set var="thumb1" value="${galleryVO.thumb1 }" />
<c:set var="ingredient" value="${galleryVO.ingredient }" />
<c:set var="article" value="${galleryVO.article }" />
<c:set var="youtube" value="${galleryVO.youtube }" />
<c:set var="word" value="${galleryVO.word }" />
<c:set var="size1_label" value="${galleryVO.size1_label }" />
<c:set var="rdate" value="${galleryVO.rdate.substring(0,16) }" />
<c:set var="recom" value="${galleryVO.recom }" />
 <c:set var="replycont" value="${replyVO.replycont}" />
<c:set var="replyno" value="${replyVO.replyno}" />


 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>댕키트</title>
 <link rel="shortcut icon" href="/images/ee.png" /> <%-- /static 기준 --%>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">

<!--댓글 등록시 로그인 여부 확인 -->
function checkLoginStatus() {
    var isMemberLoggedIn = ${sessionScope.id != null};
    
    // 일반 사용자가 로그인한 경우 댓글을 작성할 수 있음
    if (!isMemberLoggedIn) {
        // 로그인하지 않은 상태이므로 폼 제출을 방지하고 로그인 알림을 표시
        
        alert('로그인이 필요합니다.');
        window.location.href = "../member/login.do";
        return false; // 폼 제출 중단
    }
    return true; // 폼 제출 진행
}
</script>

</head>

<style>


  .content_body {
    width: 100%;
    max-width: 1200px;
    text-align: center;
  }

  .fieldset_basic {
    width: 22%;
    height: 300px;
    margin: 1.5%;
    padding: 0.5%;
    text-align: center;
  }
</style>
</head>  
 
<body style="background-color: #FEFCE6;">
<c:import url="/menu/top.do" />
 
<br>
<A href="./list_by_exhino.do?exhino=${exhino }" class='title_link'  style='background-color:#FEFCF0; margin-left: 280px;'><img src="/menu/images/menu2.png" class="icon0"> ${exhiVO.exhi } 레시피</A></DIV>

<DIV class='content_body'  style='background-color:#FEFCF0;'>
  <ASIDE class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.id != null }">
      <%--
      http://localhost:9091/gallery/create.do?exhino=1
      http://localhost:9091/gallery/create.do?exhino=2
      http://localhost:9091/gallery/create.do?exhino=3
      --%>
      <A href="./create.do?exhino=${exhiVO.exhino }">등록</A>
      <span class='menu_divide' >│</span>
      <A href="./update_text.do?galleryno=${galleryno}&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word}">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?galleryno=${galleryno}&now_page=${param.now_page == null ? 1 : param.now_page }">파일 수정</A>  
      <span class='menu_divide' >│</span>
      <A href="./youtube.do?galleryno=${galleryno}">유튜브</A> 
      <span class='menu_divide' >│</span>
      <A href="./delete.do?galleryno=${galleryno}&now_page=${param.now_page == null ? 1 : param.now_page }&exhino=${param.exhino}">삭제</A>  
    <span class='menu_divide' >│</span>
    </c:if>

    <A href="./list_by_exhino.do?exhino=${galleryVO.exhino }&now_page=${param.now_page == null?1:param.now_page}&word=${param.word }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_exhino_grid.do?exhino=${exhino }&now_page=${param.now_page == null?1:param.now_page}&word=${param.word }">갤러리형</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>

   
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
      <button type='submit'class='btn btn-info'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list_by_exhino.do?exhino=${exhiVO.exhino}&word='"class='btn btn-info'>검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic" style='background-color:#FEFCF0;'>
    <ul>
      <li class="li_none">
        <DIV style="width:100%;">
          <span style="font-size: 1.5em; font-weight: bold;">${title }</span><br>
          <div style="font-size: 0.7em;">${mname } ${rdate }</div><br>
                ${ingredient } <br>    
          

          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                <%-- /static/gallery/storage/ --%>
                <img src="/dogproject/storage/${file1saved }" style='width: 50%; float:left; margin-top:0.5%; margin-right:1%'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
                <img src="/gallery/images/none1.png" style='width: 50%; float:left; margin-top:0.5%; margin-right:1%'> 
            </c:otherwise>
            </c:choose>
            <br>
            ${article }
        </DIV>
      </li>
      
      <c:if test="${youtube.trim().length() > 0 }">
          <li class="li_none" style="clear: both; padding-top: 15px; padding-bottom: 15px;">
                  <DIV style='width:640px; height: 380px; margin: 0px auto;'>
                    ${youtube }
                  </DIV>
          </li>
      </c:if>
        
     <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
        <br>
          검색어(키워드): ${word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${file1.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/gallery/storage&filename=${file1saved}&downname=${file1}'>${file1}</A> (${size1_label})  
          </c:if>
         
        </DIV>
      </li>   
    </ul>
  </fieldset>
</DIV>

    <!-- 좋아요 -->
  <form name= 'frm' action="/recom/create.do" method='POST'>
  <input type="hidden" name="galleryno" value="${galleryno}"/>
  <input type="hidden" name="check" value="${check}"/>
  <c:choose>
    <c:when test="${sessionScope.adminno != null}">
      <button type='submit' id='recom' class='btn btn-outline-danger btn-sm' style='font-size: 0.8em;margin-left: 280px;'>♡ ${recom }</button>
    </c:when>  
    <c:when test="${sessionScope.memberno == null}">
      <button type='submit' id='recom' class='btn btn-outline-danger btn-sm' style='font-size: 0.8em;margin-left: 280px;'>♡ ${recom }</button>
    </c:when>    
    <c:when test="${check == 1 }">
      <button type='submit' id='recom' class='btn btn-danger btn-sm' style='font-size: 0.8em; margin-left: 280px;' >♡ ${recom }</button>
    </c:when>
    <c:otherwise>
      <button type='submit' id='recom' class='btn btn-outline-danger btn-sm' style='font-size: 0.8em;margin-left: 280px;'>♡ ${recom }</button>
    </c:otherwise>
  </c:choose>

</form>

<%-- 댓글 조회 --%>

 <FORM name='frm' method='POST' action='../reply/reply_create.do' enctype="multipart/form-data"  onsubmit="return checkLoginStatus();">
    <input type="hidden" name="galleryno" value="${galleryno}"/><!-- 현재 gallery의 galleryno -->
    <input type="hidden" name="memberno" value="${sessionScope.memberno}"/>
    <input type="hidden" name="id" value="${sessionScope.id}"/>
    
    <div  style='background-color:#FEFCF0; margin-left: 280px;'>🗨️댓글 ${replycnt.replycnt }개 
</div>   <br>   
    <textarea name='replycont' required="required" rows="7" cols="63"  style='background-color:#FEFCF0; margin-left: 280px;'></textarea>
    
  <br>
   <button type='submit' class='btn btn-info btn-sm'  style='margin-left: 280px;'>댓글 등록</button>
 </FORM>    
 <br>
 
 <!-- 댓글 목록 -->
 <br>
   <table class="table table-striped" style='width: 100%; table-layout: fixed;'>
    <colgroup>
              <col style="width: 10%;"></col>
              <col style="width: 70%;"></col>
              <col style="width: 10%;"></col>
              <col style="width: 10%;"></col>
    </colgroup>

    <thead>
      <tr>
        <th style='text-align: center;'>id</th>
        <th style='text-align: center;'>댓글</th>
        <th style='text-align: center;'>작성일</th>
        <th style='text-align: center;'>수정/삭제</th>
      </tr>
     <tbody>
        <c:set var="replycont" value="${replyVO.replycont}" />
        <c:set var="rdate" value="${replyVO.rdate}" />
         <c:set var="mid" value="${memberVO.id}" />
            
        <tr style="height: 112px;"  class='hover'>
          
          <td style='vertical-align: middle; text-align: center;'>
           <div> ${replyVO.mid }</div>
          </td>  
          
          <td style='vertical-align: middle; text-align: center;' >
            <div>${replycont}</div>
          </td> 
          
          <td style='vertical-align: middle; text-align: center;'>
            <div>${rdate}</div>
          </td>
          
  
          
          <td style='vertical-align: middle; text-align: center;'>
            <div><a href="/reply/update.do?galleryno=${galleryno }&replyno=${replyVO.replyno}">수정</a>/<a href="/reply/delete.do?galleryno=${galleryno }&replyno=${replyVO.replyno}" onclick="return confirm('리뷰를 삭제하시겠습니까?')">삭제</a></div>
          </td>
        </tr>


    </tbody>
  </table>
    <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>