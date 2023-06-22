<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="galleryno" value="${galleryVO.galleryno }" />
<c:set var="exhino" value="${param.exhino }" />
<c:set var="title" value="${galleryVO.title }" />        
<c:set var="file1" value="${galleryVO.file1 }" />
<c:set var="file1saved" value="${galleryVO.file1saved }" />
<c:set var="content" value="${galleryVO.content }" />
<c:set var="rdate" value="${galleryVO.rdate }" />
<c:set var="word" value="${galleryVO.word }" />
<c:set var="size1_label" value="${galleryVO.size1_label }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 <script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> <!-- top 메뉴 drop down 버튼 스크립트를 작동하게 함 -->
<script type="text/javascript">

function confirmClick(image) { //댓글 삭제 할때 경고 후 내 댓글인지 확인

    var memno = $(image).data("value");
    console.log("댓글쓴 회원번호:" +memno);

    var result = confirm("댓글을 삭제하시겠습니까?");

      if (result) {

        if ( ${sessionScope.memberno == null ? -1 : sessionScope.memberno } != memno ){
        alert("다른 회원의 댓글은 삭제할 수 없습니다");
        event.preventDefault(); // 기본 동작(링크 이동) 막기
          }
        
    } else {
      event.preventDefault(); // 기본 동작(링크 이동) 막기
    }
  }




    function gaechu(image) { // 추천 누를 때 로그인 했는지 확인, 이미 추천했을경우는 rdata.result가 "실패"가 된다.
        var dataToSend = {replyno: $(image).data("value")};
        console.log("클릭한 댓글의 데이터: " + dataToSend.replyno);

        if (${sessionScope.id != null}){
          $.ajax({                // 자바스크립트 객체 표기법: {, }  
                url: "/like_reply/gaechu_ajax.do", // form action 기능을 함.
                type: "post",          // form method 기능을 함.
                cache: false,         // 응답 결과 임시 저장 취소
                async: true,          // true: 비동기 통신 (권장), false: 동기 통신
                dataType: "json",     // 서버로부터의 응답 형식: json, html, xml..., JSON.parse() 자동 처리
                data: dataToSend,         // 서버로 보내는 데이터, id=user1&passwd=1234
                success: function(rdata) { // 응답 성공
                  if (rdata.result == "성공") {
                    window.location.reload();
                  } else {
                    alert("이미 추천한 댓글입니다");  
                  }
                      
                },
                error: function(request, status, error) {
                }   
              });

            }else{
              alert("추천을 하려면 로그인을 하십시오")
                }

        }

      function send(){ // 댓글을 작성했는지 확인하고 댓글 작성전 로그인 했는지 확인
        if (document.getElementById("frm2").checkValidity()) {
          if (${sessionScope.memberno != null}){
              $("#frm2").submit();

              }else{
                alert("댓글을 쓰려면 로그인을 하십시오");
                  
                  }
          
          }else {
            alert("댓글 내용을 입력해 주세요");
              }
      }
</script>

</head> 
 
<body>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'><A href="./list_by_exhino_search_paging.do?exhino=${exhino }&now_page=1" class='title_link'>${param.exhino != 0 ? exhiVO.name : "전체목록" } 게시판</A> > ${galleryVO.title } </DIV>
<c:if test="${sessionScope.id != null}">
  <A href="./create.do?exhino=${param.exhino }&now_page=${param.now_page}">등록</A>
  <span class='menu_divide' >│</span>
  <A href="./update.do?exhino=${param.exhino }&galleryno=${galleryno}&now_page=${param.now_page}">수정</A>
  <span class='menu_divide' >│</span>
  <A href="./delete.do?galleryno=${galleryno}&now_page=${param.now_page}&exhino=${exhino}">삭제</A>  
</c:if>

<DIV class='content_body'>
  <ASIDE class="aside_right" style="padding-bottom: 10px;">
    <A href="./list_by_exhino_search_paging.do?exhino=${param.exhino}&now_page=${param.now_page}&word=${param.word }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>

  </ASIDE> 

  
  
  <DIV style="text-align: right; clear: both;">  
    <ASIDE class="aside_left" style="padding-bottom: 10px;">
      <span style="font-size: 1.5em; font-weight: bold;">${title }</span>
      <span class='menu_divide' >│</span> ${memberVO.id}
      <span class='menu_divide' >│</span> ${rdate }
    </ASIDE>
  
    <form name='frm' id='frm' method='get' action='./list_by_exhino_search_paging.do'>
      <input type='hidden' name='exhino' value='${param.exhino }'>
      <input type='hidden' name='now_page' value='1'>  <%-- 검색기본 시작 페이지 --%>
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class="btn btn-secondary btn-sm">검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-info btn-sm'
                     onclick="location.href='./list_by_exhino_search_paging.do?exhino=${param.exhino}&now_page=1&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
 
  <DIV class='menu_line'></DIV>
  <BR>
  <fieldset class="fieldset_basic">
  ${content }
  <BR>
  <BR>
  <BR>
  <BR>
  </fieldset>
  
  <DIV style="text-align: right;">
 <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${word }
        </DIV>
      </li>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  
  <BR>
 
  <DIV class='reply-div'> <!-- 댓글 -->
  <label style='font-size:15px; font-weight:bold;'>댓글목록</label> <span class='reply-count'>[${reply_count }]</span>
  
  <table class="table" style="width:100%;">
   <colgroup>
      <col style="width: 8%;"></col>
      <col style="width: 75%;"></col>
      <col style="width: 5%;"></col>
      <col style="width: 8%;"></col>
      <col style="width: 4%;"></col>
   </colgroup>
      <c:forEach var="replyVO" items="${reply_list }">
      <c:set var="memberno" value="${replyVO.memberno }" />
      <tr>
      <td>
      <c:choose>
          <c:when test="${f.apply(memberno).rankno == 1}"><img src='/member/images/admin.png' title="관리자" class="icon"></c:when> <%-- static 기준 --%>
          <c:when test="${f.apply(memberno).rankno == 2}"><img src='/member/images/user.png' title="개인 회원" class="icon"></c:when>
          <c:when test="${f.apply(memberno).rankno == 3}"><img src='/member/images/enterprise.png' title="기업 회원" class="icon"></c:when>
          <c:when test="${f.apply(memberno).rankno == 4}"><img src='/member/images/x.png' title="탈퇴 회원" class="icon"></c:when>
        </c:choose>  ${f.apply(memberno).id }
      </td>
      <td>${replyVO.reply_content }</td>
      <td><a href="#"><IMG src="/images/gaechu.png" class="icon" data-value="${replyVO.replyno }" onclick="gaechu(this)"></a> +${f2.apply(replyVO.replyno) } </td>
      <td style="font-size:13px; vertical-align: middle;">${replyVO.rdate }</td>
      <td>
      <a href="/reply/delete.do?galleryno=${param.galleryno }&exhino=${param.exhino}&now_page=${param.now_page}&word=${param.word}&replyno=${replyVO.replyno}"><IMG src="/cate/images/delete.png" class="icon" 
      onclick="confirmClick(this)" data-value="${replyVO.memberno}"></a>
      </td>
      </tr>
      </c:forEach>
  </table>
  
  

  
  <form name='frm2' id='frm2' method='post' action='/reply/create.do'>
      <input type="hidden" name="galleryno" value="${param.galleryno }" >
      <input type="hidden" name="exhino" value="${param.exhino}" >
      <input type="hidden" name="now_page" value="${param.now_page}" >
      <input type="hidden" name="word" value="${param.word}" >
      <textarea name="reply_content" class="form-control" placeholder="댓글입력" id="reply_content" style="height: 100px" required="required"  maxlength="100"></textarea>
      <BR>
      <div style="text-align:right;">
      <button type='button' onclick="send()" class="btn btn-dark btn-sm">댓글 등록</button>
      </div>
      
  </form>
  
  </DIV> <!-- 댓글 -->

</DIV>



 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>