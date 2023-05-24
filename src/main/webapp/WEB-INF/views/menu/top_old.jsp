<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<DIV class='container_main'> 
  <%-- 화면 상단 메뉴 --%>
  <DIV class='top_img'>
    <DIV class='top_menu_label'>ArtWave</DIV>
    <NAV class='top_menu'>
      <a class='menu_link'  href='/' >힐링 전시회</a><span class='top_menu_sep'> </span>
      
      <c:forEach var="exhiVO" items="${list}">
        <c:set var="exhino" value="${exhiVO.exhino }" />
        <c:set var="name" value="${exhiVO.name }" />
        <a href="/gallery/list_by_exhino.do?exhino=${exhino }&now_page=1" class="menu_link">${name }</a><span class='top_menu_sep'> </span>        
      </c:forEach>
      
      <a href="/gallery/list_all.do" class="menu_link">전체 글 목록</a><span class='top_menu_sep'> </span>
    
      <c:choose>
        <c:when test="${sessionScope.admin_id == null }">
          <a href="/admin/login.do" class="menu_link">관리자 로그인</a><span class='top_menu_sep'> </span>        
        </c:when>
        <c:otherwise>
          <a class='menu_link'  href='/exhi/list_all.do'>카테고리 전체 목록</a><span class='top_menu_sep'> </span>
          <a class='menu_link'  href='/member/list.do'>회원 목록</a><span class='top_menu_sep'> </span>
          <a href="/admin/logout.do" class="menu_link">관리자 ${sessionScope.admin_id } 로그아웃</a><span class='top_menu_sep'> </span>
        </c:otherwise>
      </c:choose>     
            
    </NAV>
  </DIV>
  
  <%-- 내용 --%> 
  <DIV class='content'>
  
  