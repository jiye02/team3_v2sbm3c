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
function delete_func(order_payno) {  // GET -> POST 전송, 상품 삭제
    var frm = $('#frm_post');
    frm.attr('action', './delete.do');
    $('#order_payno',  frm).val(order_payno);
    
    frm.submit();
}
</script>
</head> 
 
<body>
<c:import url="/menu/top.do" />

<form name='frm_post' id='frm_post' action='' method='post'>
  <input type='hidden' name='order_payno' id='order_payno'>
</form>
 
  <DIV class='title_line'>
    전체 주문 내역
  </DIV>

  <DIV class='content_body' style='width: 100%;'>

    <ASIDE class="aside_right">
      <A href="javascript:location.reload();">새로고침</A>
    </ASIDE> 
   
    <div class='menu_line'></div>
   
   
    <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style='width: 5%;'/>
      <col style='width: 5%;'/>
      <col style='width: 7%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 7%;'/>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
    </colgroup>
    <TR>
      <TH class='th_bs'>예약 번호</TH>
      <TH class='th_bs'>회원 번호</TH>
      <TH class='th_bs'>전화번호</TH>
      <TH class='th_bs'>주소</TH>
      <TH class='th_bs'>결제 타입</TH>
      <TH class='th_bs'>결제 금액</TH>
      <TH class='th_bs'>포인트</TH>
      <TH class='th_bs'>주문일</TH>
      <TH class='th_bs'>예약일</TH>
      <TH class='th_bs'>기타</TH>
    </TR>
   
    <c:forEach var="order_payVO" items="${list }">
      <c:set var="order_payno" value ="${order_payVO.order_payno}" />
      <c:set var="memberno" value ="${order_payVO.memberno}" />
      <c:set var="rtel" value ="${order_payVO.rtel}" />
      <c:set var="address" value ="(${order_payVO.rzipcode}) ${order_payVO.raddress1}" />
      <c:set var="paytype" value ="${order_payVO.paytype}" />
      <c:set var="amount" value ="${order_payVO.amount}" />
      <c:set var="point" value ="${amount*0.05}" />
      <c:set var="vday" value ="" />
      <c:set var="rdate" value ="${order_payVO.rdate}" />
         
       
     <TR>
      <TD class=td_basic>${order_payno}</TD>
      <TD class=td_basic><A href="/member/read.do?memberno=${memberno}">${memberno}</A></TD>
      <TD class='td_left'>${rtel}</TD>
      <TD class='td_basic'>${address}</TD>
      <TD class='td_basic'>
        <c:choose>
          <c:when test="${paytype == 1}">신용 카드</c:when>
          <c:when test="${paytype == 2}">모바일</c:when>
          <c:when test="${paytype == 3}">계좌 이체</c:when>
        </c:choose>
      </TD>
      <TD class='td_basic'><fmt:formatNumber value="${amount }" pattern="#,###" /></TD>
      <TD class='td_basic'><fmt:formatNumber value="${point }" pattern="#,###" /></TD>
      <TD class='td_basic'>${rdate.substring(1,16) }</TD>
      <TD class='td_basic'></TD>
      <TD class='td_basic'>
        <A href="/order_item/list.do?order_payno=${order_payno }"><img src="/order_pay/images/item.png" title="예약 내역 상세 조회"></A>
        <A href="javascript: delete_func(${order_payno })"><img src="/order_pay/images/delete1.png" title="주문 삭제"></A>
      </TD>
    </TR>
    </c:forEach>
    
  </TABLE>   
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>