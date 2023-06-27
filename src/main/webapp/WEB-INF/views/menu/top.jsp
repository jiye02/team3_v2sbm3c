<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.exhi.ExhiVO" %>

<DIV class='container_main'> 
    <!-- 헤더 start 
    
    -->
    <div class="header">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="/">ArtWave</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle Navigation">
              <span class="navbar-toggler-icon"></span>
            </button>    
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                  <c:forEach var="exhiVO" items="${list}">
                    <c:set var="exhino" value="${exhiVO.exhino }" />
                    <c:set var="name" value="${exhiVO.name }" />
                  
                  </c:forEach>
                  
                  <li class="nav-item dropdown"> <%-- 카테고리 서브 메뉴 --%>
                      <a class="nav-link dropdown-toggle" data-toggle="dropdown"  href="#">전시회 둘러보기</a>
                      <div class="dropdown-menu">
                          <a class="dropdown-item" href="/gallery/list_by_exhino.do?exhino=1&now_page=1" >미술전시회</a>
                          <a class="dropdown-item" href="/gallery/list_by_exhino.do?exhino=2&now_page=1" >의류전시회</a>
                          <a class="dropdown-item" href="/gallery/list_by_exhino.do?exhino=3&now_page=1" >이색전시회</a>
                          <a class="dropdown-item" href="/gallery/list_by_exhino.do?exhino=4&now_page=1" >팝업스토어</a>
                          <a class="dropdown-item" href="/gallery/list_by_exhino.do?exhino=5&now_page=1" >지역축제</a>
                          <a class="dropdown-item" href="/gallery/list_all.do">전체 목록</a>
                      </div>
                   </li>

                  <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
                      <c:choose>
                          <c:when test="${sessionScope.id == null}">
                              <a class="nav-link" href="/member/login.do">로그인</a>
                          </c:when>
                          <c:otherwise>
                           <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
                          <a class="nav-link" href='/basket/list_by_memberno.do'>장바구니</a>
                          </li>
                           <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
                          <a class="nav-link" href='/jjim/list_by_memberno.do'>찜</a>
                          </li>
                              <a class="nav-link" href='/member/logout.do'>${sessionScope.id } 로그아웃</a>
                          </c:otherwise>
                      </c:choose>
                  </li>
                  <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
                      <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">회원</a>
                      <div class="dropdown-menu">
                      <c:choose>
                              <c:when test="${sessionScope.id == null}">
                                <a class="dropdown-item" href="/member/create.do">회원 가입</a>
                                <a class="dropdown-item" href="#">아이디 찾기</a>
                                <a class="dropdown-item" href="#">비밀번호 찾기</a>
                              </c:when>
                              <c:otherwise>
                                <a class="dropdown-item" href="/order_pay/list_by_memberno.do?memberno=${sessionScope.memberno }">예약 주문 내역</a>
                                <a class="dropdown-item" href="http://localhost:8000/ais/recommend_form/?memberno=${sessionScope.memberno }">관심 분야 등록</a>
                                <a class="dropdown-item" href="/member/read.do">회원 정보 수정</a>
                                <a class="dropdown-item" href="/member/passwd_update.do?memberno=${sessionScope.memberno}">비밀번호 변경</a>
                               <a class="dropdown-item" href="/member/leave.do?memberno=${sessionScope.memberno}">회원 탈퇴</a>
                              </c:otherwise>
                          </c:choose>
                          </div>
               
                  <c:choose>
                    <c:when test="${sessionScope.admin_id == null }">
                      <li class="nav-item">
                        <a class="nav-link" href="/admin/login.do">관리자 로그인</a>
                      </li>
                    </c:when>
                    <c:otherwise>
                      <li class="nav-item dropdown"> <%-- 관리자 서브 메뉴 --%>
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">관리자</a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href='/member/list.do'>회원 목록</a>        
                          <a class="dropdown-item" href='/admin/list.do'>관리자 목록</a>                             
                          <a class="dropdown-item" href='/exhi/list_all.do'>카테고리 전체 목록</a>
                          <a class="dropdown-item" href='/reply/list.do'> 댓글 전체 목록</a>
                          <a class="dropdown-item" href='/recommend/list.do'>추천 목록</a>
                          <a class="dropdown-item" href='/jjim/list_by_jjim'>찜 목록</a>
                          <a class="dropdown-item" href='/admin/logout.do'>관리자 ${sessionScope.admin_id } 로그아웃</a>
                          
                        </div>
                        
                        
                      </li>
                    </c:otherwise>
                  </c:choose>     
                </ul>
            </div>    
        </nav>

    </div>
    </DIV>
    <!-- 헤더 end -->
    
    <%-- 내용 --%> 
    <DIV class='content'>
      <div style='clear: both; height: 50px;'></div></DIV>