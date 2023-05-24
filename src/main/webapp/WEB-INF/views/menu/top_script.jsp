<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.exhi.ExhiVO" %>

<DIV class='container_main'> 
  <%-- 화면 상단 메뉴 --%>
  <DIV class='top_img'>
    <DIV class='top_menu_label'>ArtWave</DIV>
    <NAV class='top_menu'>
      <A class='menu_link'  href='/' >힐링 전시회</A><span class='top_menu_sep'> </span>
      
      <%
      // 레코드가 없어도 list는 null 아님
      ArrayList<ExhiVO> list = (ArrayList<ExhiVO>)request.getAttribute("list");
      for (int i=0; i < list.size(); i++) {
        ExhiVO exhiVO = list.get(i);
      %>
        <A href="#" class="menu_link"><%=exhiVO.getName() %></A><span class='top_menu_sep'> </span>
      <%  
      }
      %>
      
      <A href="/gallery/list_all.do" class="menu_link">전체 글 목록</A><span class='top_menu_sep'> </span>
      
      <%
      String admin_id = (String)session.getAttribute("admin_id");

      if (admin_id == null) { // 로그인 안된 경우
      %>
        <a href="/admin/login.do" class="menu_link">관리자 로그인</a><span class='top_menu_sep'> </span>
      <%  
      } else { // 로그인 한 경우
      %>
        <A class='menu_link'  href='/exhi/list_all.do'>카테고리 전체 목록</A><span class='top_menu_sep'> </span>
        
        <a href="/admin/logout.do" class="menu_link">관리자 <%=admin_id %> 로그아웃</a><span class='top_menu_sep'> </span>
      <%  
      }
      %>
            
    </NAV>
  </DIV>
  
  <%-- 내용 --%> 
  <DIV class='content'>
  
  