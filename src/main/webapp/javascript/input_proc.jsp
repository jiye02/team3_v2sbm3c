<%@ page contentType="text/html; charset=UTF-8" %>
 
<%
request.setCharacterEncoding("utf-8");
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>http://localhost:9091/javascript/input_proc.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="../css/style.css" rel="Stylesheet" type="text/css"> <!-- /static -->
</head>
<body>
<DIV class="container">
  <DIV class="content">
    <H2>예약 조회</H2>
    <HR>
    객실: <%=request.getParameter("rname") %><br>
    크기: <%=request.getParameter("py") %> 평<br>
    인원: <%=request.getParameter("cnt") %> 명<br>
    <a href="./input_form.html" class="btn btn-light btn-sm">재신청</a>
  </DIV>
</DIV>
</body>
</html>
 