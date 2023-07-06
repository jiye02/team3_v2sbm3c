<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>ART Wave</title>
 <link rel="shortcut icon" href="/images/ee.png" /> <%-- /static 기준 --%>
<%-- /static/css/style.css --%> 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<script>
  function goBack() {
    window.history.back();
  }
</script>

</head> 
<body>
<c:import url="/menu/top.do" />

<DIV class='title_line'>알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.code == 'create_success'}"> <%-- Java if --%>
          <LI class='li_none'>
          <img src="/css/images/url5.png" class="icon" style="width:15px">
            <span  style="margin-right:80px;">${param.mname }님(${param.id }) 회원 가입을 축하합니다.</span>
          </LI>  
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='./login.do?id=${param.id}'"
                         class="btn btn-dark" style="margin-right:80px; margin-top:30px; width:80px; height:47px;">로그인</button>
          </LI> 
        </c:when>
        
        <c:when test="${param.code == 'create_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail" style="margin-right:80px;">회원 가입에 실패했습니다. 다시 시도해주세요.</span>
          </LI>                                                                      
        </c:when>
        
         <c:when test="${param.code == 'leave_success'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_success">${param.mname }님(${param.id }) 회원 탈퇴에 성공했습니다.</span>
          </LI>   
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='../index.do'"
                         class="btn btn-info btn-sm">홈으로</button>
          </LI>                                                                     
        </c:when>    
            
        <c:when test="${code == 'leave_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 회원 탈퇴에 실패했습니다.</span>
          </LI>                                                                      
        </c:when>  

        <c:when test="${param.code == 'update_success'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_success" style="margin-right:80px;">${param.mname }님(아이디: ${param.id }) 회원 정보를 변경했습니다.</span>
          </LI>
          <LI class='li_none' style="margin-right:80px;">
            <button type="button" onclick="location.href='/'" class="btn btn-outline-dark" style='width:70px; height:50px;'><img src="/member/images/home.png" class="icon" style="width:30px"></button>
            <button type='button' 
                         onclick="location.href='/member/read.do'"
                         class="btn btn-dark" style='width:200px; height:50px;'>수정 페이지로 돌아가기</button>                   
          </LI>                                                                       
        </c:when>
                
        <c:when test="${param.code == 'update_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail" style="margin-right:80px;">${param.mname }님(${param.id }) 회원 정보 수정에 실패했습니다.</span>
          </LI>                                                                      
        </c:when>
        
        <c:when test="${param.code == 'delete_success'}"> <%-- Java if --%>
          <LI class='li_none'>
            <img src="/member/images/memout.png" class="icon" style="width:30px">
            <span   style="margin-right:80px;">${param.mname }님(${param.id }) 회원 정보 삭제에 성공했습니다.</span>
          </LI>   
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='/member/list.do'"
                         class="btn btn-dark" style="margin-right:80px; margin-top:30px; width:130px; height:50px;">회원 목록</button>

          </LI>                                                                     
        </c:when>    
            
        <c:when test="${param.code == 'delete_success_mem'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_success"  style="margin-right:80px;">${param.mname }님(${param.id }) 회원 정보 삭제에 성공했습니다.</span>
          </LI>   
          <LI class='li_none'>
            <button type="button" onclick="location.href='/'" class="btn btn-outline-dark" style='width:70px; height:50px; margin-right:90px;'><img src="/member/images/home.png" class="icon" style="width:30px"></button>
          </LI>                                                                     
        </c:when>
        
        <c:when test="${code == 'delete_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 회원 정보 삭제에 실패했습니다.</span>
          </LI>                                                                      
        </c:when>
        
        
        <c:when test="${param.code == 'passwd_update_success'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_success" style="margin-right:80px;">${param.mname }님(${param.id }) 패스워드를 변경했습니다.</span>
          </LI>   
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='/member/login.do'"
                         class="btn btn-dark"  style="margin-right:80px; margin-top:30px; width:80px; height:47px;">확인</button>
          </LI>                                                                     
        </c:when>   
        
        <c:when test="${param.code == 'passwd_update_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 패스워드 변경에 실패했습니다.</span>
          </LI>                                                                      
        </c:when>  
        
         <c:when test="${param.code == 'passwd_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">${param.mname }님(${param.id }) 현재 패스워드를 올바르게 입력해주세요.</span>
          </LI>                                                                      
        </c:when> 
        
        
         <c:when test="${param.code == 'reply_update_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">댓글 수정은 작성자에게만 허용된 작업입니다.<br> 자신이 작성한 댓글만 수정할 수 있습니다.</span>
            <br>
            <button onclick="goBack()"  class="btn btn-dark" style='width:200px; height:50px; margin-top:70px;'>이전 페이지로 돌아가기</button>
          </LI>                                                                      
        </c:when> 
        
         <c:when test="${param.code == 'reply_delete_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail">댓글 삭제는 작성자에게만 허용된 작업입니다.<br> 자신이 작성한 댓글만 삭제할 수 있습니다.</span>
            <br>
             <button onclick="goBack()"  class="btn btn-dark" style='width:200px; height:50px; margin-top:70px;'>이전 페이지로 돌아가기</button>
          </LI>                                                                      
        </c:when>                       
        
        <c:when test="${param.code == 'id_find_success'}"> <%-- Java if --%>
          <LI class='li_none'>
             <img src="/member/images/user.png" class="icon" style="width:30px">
            <span>${param.mname }님의 아이디는</span><span style="font-weight: bold; font-size:25px;"> ${param.id }</span><span style="margin-right:80px; margin-left:3px;">입니다.</span><br>
            <span  style="margin-right:190px; font-size:15px;color:gray;">가입일  ${fn:substring(param.mdate, 0, 10)}</span>
          </LI>
          <LI class='li_none' style="margin-right:80px;">
           <button type='button' 
                         onclick="location.href='/member/login.do'"
                         class="btn btn-dark" style='width:200px; height:50px; margin-top:70px;'>로그인</button>  
            <button type='button' 
                         onclick="location.href='/member/passwd_find.do'"
                         class="btn btn-dark" style='width:200px; height:50px; margin-top:70px;'>비밀번호 찾기</button>                   
          </LI>                                                                       
        </c:when>

         <c:when test="${param.code == 'nonFind_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail" style="margin-right:80px;">일치하는 정보가 없습니다. 다시 시도해주세요</span>
          </LI>
          <LI class='li_none' style="margin-right:80px;">
            <button type="button" onclick="location.href='/'" class="btn btn-outline-dark" style='width:70px; height:50px;'><img src="/member/images/home.png" class="icon" style="width:30px"></button>
            <button type='button' 
                         onclick="location.href='/member/id_find.do'"
                         class="btn btn-dark" style='width:200px; height:50px;'>아이디 찾기</button>                   
          </LI>                                                                       
        </c:when>
        
               
        <c:when test="${param.code == 'passwd_find_success'}"> <%-- Java if --%>
          <LI class='li_none'>
             <img src="/member/images/user.png" class="icon" style="width:30px">
            <span>${param.mname }님의 패스워드는</span><span style="font-weight: bold; font-size:25px;">${param.pw}</span><span style="margin-right:80px; margin-left:3px;">입니다.</span><br>
            <span  style="margin-right:190px; font-size:15px;color:gray;">가입일  ${fn:substring(param.mdate, 0, 10)}</span>
          </LI>
          <LI class='li_none' style="margin-right:80px;">
           
            <button type="button" onclick="location.href='/'" class="btn btn-outline-dark" style='width:70px; height:50px;  margin-top:70px;'><img src="/member/images/home.png" class="icon" style="width:30px"></button>     
            <button type='button' 
                         onclick="location.href='/member/login.do'"
                         class="btn btn-dark" style='width:200px; height:50px; margin-top:70px;'>로그인</button>                
          </LI>                                                                       
        </c:when>
        
              <c:when test="${param.code == 'nonpasswd_find_fail'}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_fail" style="margin-right:80px;">일치하는 정보가 없습니다. 다시 시도해주세요</span>
          </LI>
          <LI class='li_none' style="margin-right:80px;">
            <button type="button" onclick="location.href='/'" class="btn btn-outline-dark" style='width:70px; height:50px;'><img src="/member/images/home.png" class="icon" style="width:30px"></button>
            <button type='button' 
                         onclick="location.href='/member/passwd_find.do'"
                         class="btn btn-dark" style='width:200px; height:50px;'>비밀번호 찾기</button>                   
          </LI>                                                                       
        </c:when>
        
        <c:when test="${param.code == 'duplicate_id'}"> <%-- Java if --%>
          <LI class='li_none'>
            <img src="/member/images/x.png" class="icon" style="width:30px">
            <span   style="margin-right:80px;">(${param.id }) 이미 존재하는 id 입니다 중복 검사 바랍니다.</span>
          </LI>   
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='/member/create.do'"
                         class="btn btn-dark" style="margin-right:80px; margin-top:30px; width:130px; height:50px;">다시 가입</button>

          </LI>                                                                     
        </c:when>
        
        
        <c:when test="${param.code == 'leave_id'}"> <%-- Java if --%>
          <LI class='li_none'>
            <img src="/member/images/x.png" class="icon" style="width:30px">
            <span   style="margin-right:80px;">탈퇴된 회원 입니다.</span>
          </LI>   
          <LI class='li_none'>
            <button type='button' 
                         onclick="location.href='/member/create.do'"
                         class="btn btn-dark" style="margin-right:80px; margin-top:30px; width:130px; height:50px;">다시 가입</button>

          </LI>                                                                     
        </c:when>            
        
        
        
        <c:otherwise>
          <LI class='li_none_left'>
            <span class="span_fail">알 수 없는 에러로 작업에 실패했습니다.</span>
          </LI>
          <LI class='li_none_left'>
            <span class="span_fail">다시 시도해주세요.</span>
          </LI>
        </c:otherwise>
        
      </c:choose>
      <LI class='li_none'>
        <br>
        <c:choose>
            <c:when test="${param.cnt == 0 }">
                <button type='button' onclick="history.back()" class="btn btn-dark" style="margin-right:80px; margin-top:30px; width:80px; height:47px;">다시 시도</button>    
            </c:when>
        </c:choose>
        
        <%-- <a href="./list_by_cateno.do?cateno=${param.cateno}" class="btn btn-primary">목록</a> --%>
        <%-- <button type='button' onclick="location.href='./list_by_cateno_search.do?cateno=${param.cateno}'" class="btn btn-primary">목록</button> --%>
        <%-- <button type='button' onclick="location.href='./list_by_cateno_search_paging.do?cateno=${param.cateno}'" class="btn btn-primary">목록</button> --%>

      </LI>
    </UL>
  </fieldset>

</DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>