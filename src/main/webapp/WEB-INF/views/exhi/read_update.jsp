<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.exhi.ExhiVO" %>

<%
ExhiVO exhiVO_read = (ExhiVO)request.getAttribute("exhiVO");
%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art world</title>
<link rel="shortcut icon" href="/images/ex_top.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head> 
 
<body>
<%-- <jsp:include page="../menu/top.jsp" flush='false' /> --%>
<c:import url="/menu/top.do" />
 
<DIV class='title_line'>전체 카테고리 > 수정</DIV>

<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./update.do'>
      <input type="hidden" name="exhino" value="<%=exhiVO_read.getExhino() %>">
      <label>카테고리 이름</label>
      <input type='text' name='name' value='<%=exhiVO_read.getName() %>' required="required" style='width: 25%;' autofocus="autofocus">
      <label>출력 순서</label>
      <input type='number' name='seqno' value='<%=exhiVO_read.getSeqno() %>' min='1' required="required" style='width: 5%;'>  
      <button type="submit" id='submit' class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>수정</button>
      <button type="button" onclick="location.href='/exhi/list_all.do'" class='btn btn-info btn-sm' style='height: 28px; margin-bottom: 5px;'>취소</button>
    </FORM>
  </DIV>

  <TABLE class='table table-hover'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 50%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
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
        <TD><%=exhiVO.getName() %></TD>
        <TD class='td_bs'><%=exhiVO.getCnt() %></TD>
        <TD class='td_bs'><%=exhiVO.getRdate().substring(0, 10) %></TD>
        <TD>
          <A href="./read_update.do?exhino=<%=exhiVO.getExhino() %>" title="수정"><IMG src="/exhi/images/update.png" class="icon"></A>
          <A href="./read_delete.do?exhino=<%=exhiVO.getExhino() %>" title="삭제"><IMG src="/exhi/images/delete.png" class="icon"></A>
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

