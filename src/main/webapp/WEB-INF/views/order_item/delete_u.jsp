<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
<link rel="shortcut icon" href="/images/ex_top.png" />

<link href="/css/style.css" rel="Stylesheet" type="text/css">  <!-- /static -->

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
</head> 
 
<body>

<c:import url="/menu/top.do" />
 
<DIV class='title_line'>주문별 상세 목록</DIV>
  <DIV class='message'>
    <fieldset class='fieldset_basic'>
      <ul>
        <li class='li_none'>주문번호 : %{order_itemno}</li>
        <li class='li_none'>주문이 삭제 되었습니다.</li>
        <li class='li_none'>
          <button type="button" onclick="location.href='/order_item/list.do?order_payno=${order_payno}'" class="btn btn-info btn-sm">확인(목록)</button>
        </li>
        
      </ul>
    </fieldset>    
  </DIV>   

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
 