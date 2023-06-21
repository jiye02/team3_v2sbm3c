<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.exhi.ExhiVO" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art world</title>
<link rel="shortcut icon" href="/images/ex_top.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>   
</head> 
 
<body>
<%-- <jsp:include page="../menu/top.jsp" flush='false' /> --%>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>전체 카테고리</DIV>

<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>카테고리 이름</label>
      <input type='text' name='name' value='' required="required" style='width: 25%; margin-right: 10px;' autofocus="autofocus">
      <label> 출력 순서</label>
      <input type='number' name='seqno' min="1" value='1' required="required" style='width: 5%;'>
  
      <button type="submit" id='submit' class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>등록</button>
      <button type="button" onclick="location.href='/exhi/list_all.do'" class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>취소</button>
    </FORM>
  </DIV>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 45%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 20%;'/>
      <col style='width: 15%;'/>
    </colgroup>
   
    <thead>  
    <TR>
      <TH class="th_bs">순서</TH>
      <TH class="th_bs">카테고리 이름</TH>
      <TH class="th_bs">자료수</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <%
    ArrayList<ExhiVO> list = (ArrayList<ExhiVO>)request.getAttribute("list");
    
    for (int i=0; i < list.size(); i++) {
      ExhiVO exhiVO = list.get(i);
    %>
      <TR>
        <TD class='td_bs'><%= exhiVO.getSeqno() %></TD>
        <TD><a href="/gallery/list_by_exhino.do?exhino=<%=exhiVO.getExhino() %>&now_page=1"><%=exhiVO.getName() %></a></TD>
        <TD class='td_bs'><%=exhiVO.getCnt() %></TD>
        <TD class='td_bs'><%=exhiVO.getRdate().substring(0, 10) %></TD>
        <TD>
          <A href="./read_update.do?exhino=<%=exhiVO.getExhino() %>" title="수정"><IMG src="/exhi/images/update.png" class="icon"></A>
          <A href="./read_delete.do?exhino=<%=exhiVO.getExhino() %>" title="삭제"><IMG src="/exhi/images/delete.png" class="icon"></A>
          <A href="./update_seqno_decrease.do?exhino=<%=exhiVO.getExhino() %>" title="우선순위 높이기"><IMG src="/exhi/images/decrease.png" class="icon"></A>
          <A href="./update_seqno_increase.do?exhino=<%=exhiVO.getExhino() %>" title="우선순위 낮추기"><IMG src="/exhi/images/increase.png" class="icon"></A>
 
          <%
          if (exhiVO.getVisible().equals("Y")) {
          %>
            <A href="./update_visible_n.do?exhino=<%=exhiVO.getExhino() %>"><IMG src="/exhi/images/show.png" class="icon"></A>
          <%  
          } else { // N
          %>
            <A href="./update_visible_y.do?exhino=<%=exhiVO.getExhino() %>"><IMG src="/exhi/images/hide.png" class="icon"></A>
          <%  
          }
          %>
                    
        </TD>
      </TR>
    <%  
    }
    %>
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

