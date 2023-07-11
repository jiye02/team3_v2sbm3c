<%@ page contentType="text/html; charset=UTF-8" %>

<%!
public String checkNull(Object str) {
  if (str == null) {
    str = "";
  } 
  
  return (String)str;
}
%>  
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>http://localhost:9093/session/send.jsp</title>
<link href="/css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
  <br>
  다른 페이지로 이동해도 session은 살아있음.
  <br>
  request.getParameter("id") 같은 코드가 필요 없음.
  <br>
  특정 시간동안 메모리상에 계속 유지됨
 
  <ul>
    <li>
      ID: <%=checkNull(session.getAttribute("id")) %> <br> 
      PASSWORD: <%=checkNull(session.getAttribute("passwd")) %><br>
    </li>
  </ul> 
  <br>
  [<A href='./session.jsp'>session.jsp 페이지로 이동</A>]
  <br><br>
  
 
</body>
</html>

