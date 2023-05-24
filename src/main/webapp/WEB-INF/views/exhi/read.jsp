<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="dev.mvc.exhi.ExhiVO" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=5.0, width=device-width" /> 
<title>ArtWord</title>
<link rel="shortcut icon" href="/images/ex_top.png" />
<style type="text/css">
  *{ font-family: Malgun Gothic; font-size: 26px;}
</style>
</head>
<body>
<DIV style="font-size: 20px;">
<% ExhiVO exhiVO = (ExhiVO) request.getAttribute("exhiVO"); %>

 exhino:<%= exhiVO.getExhino() %><br>
 name:<%= exhiVO.getName() %><br>
 cnt :<%= exhiVO.getCnt() %><br>
 rdate:<%= exhiVO.getRdate() %><br>
</DIV>
</body>
</html>

