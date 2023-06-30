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
function delete_func(order_itemno) {  // GET -> POST 전송, 상품 삭제
    var frm = $('#frm_post');
    frm.attr('action', './delete.do');
    $('#order_itemno',  frm).val(order_itemno);
    
    frm.submit();
}
</script>
</head> 
 
<body>
<c:import url="/menu/top.do" />
 
 <form name='frm_post' id='frm_post' action='' method='post'>
  <input type='hidden' name='order_itemno' id='order_itemno'>
  <input type='hidden' name='order_payno' id='order_Payno' value="${param.order_payno }">
</form>

  <DIV class='title_line'>
    주문별 상세 내역
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
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 5%;'/>
      <col style='width: 5%;'/>
      <col style='width: 5%;'/>
      <col style='width: 5%;'/>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 7%;'/>
     
    </colgroup>
    <TR>
      <TH class='th_bs'>예약 번호</TH>
      <TH class='th_bs'>상세 번호</TH>
      <TH class='th_bs'>회원 번호</TH>
      <TH class='th_bs'>전시회 번호</TH>
      <TH class='th_bs'>상품명</TH>
      <TH class='th_bs'>가격</TH>
      <TH class='th_bs'>수량</TH>
      <TH class='th_bs'>총 금액</TH>
      <TH class='th_bs'>포인트</TH>
      <TH class='th_bs'>배송상태</TH>
      <TH class='th_bs'>주문일</TH>
      <TH class='th_bs'>예약일</TH>
      <TH class='th_bs'>삭제</TH>
    </TR>
   
    <c:forEach var="order_itemVO" items="${list }">
      <c:set var="order_payno" value ="${order_itemVO.order_payno}" />
      <c:set var="order_itemno" value ="${order_itemVO.order_itemno}" />
      <c:set var="memberno" value ="${order_itemVO.memberno}" />
      <c:set var="galleryno" value ="${order_itemVO.galleryno}" />
      <c:set var="title" value ="${order_itemVO.title}" />
      <c:set var="saleprice" value ="${order_itemVO.saleprice}" />
      <c:set var="cnt" value ="${order_itemVO.cnt}" />
      <c:set var="tot" value ="${order_itemVO.tot}" />
      <c:set var="point" value ="${tot*0.05}" />
      <c:set var="stateno" value ="${order_itemVO.stateno}" />
      <c:set var="rdate" value ="${order_itemVO.rdate}" />
      <c:set var="vday" value ="" />
         
    <TR>
      <TD class=td_basic>${order_payno}</TD>
      <TD class=td_basic>${order_itemno}</TD>
      <TD class=td_basic><A href="/member/read.do?memberno=${memberno}">${memberno}</A></TD>
      <TD class=td_basic><A href="/gallery/read.do?galleryno=${galleryno}">${galleryno}</A></TD>
      <TD class='td_left'><A href="/gallery/read.do?galleryno=${galleryno}">${title}</A></TD>
      <TD class='td_left'><fmt:formatNumber value="${saleprice }" pattern="#,###" /></TD>
      <TD class='td_basic'>${cnt }</TD>
      <TD class='td_basic'><fmt:formatNumber value="${tot }" pattern="#,###" /></TD>
      <TD class='td_basic'><fmt:formatNumber value="${point }" pattern="#,###" /></TD>
      <TD class='td_basic'>
        <c:choose>
          <c:when test="${stateno == 1}">결제 완료</c:when>
          <c:when test="${stateno == 2}">상품 준비중</c:when>
          <c:when test="${stateno == 3}">배송 시작</c:when>
          <c:when test="${stateno == 4}">배달중</c:when>
          <c:when test="${stateno == 5}">오늘 도착</c:when>
          <c:when test="${stateno == 6}">배달 완료</c:when>
        </c:choose>
      </TD>
      
      <TD class='td_basic'>${rdate.substring(1,16) }</TD>
      <TD class='td_basic'></TD>
      <TD class='td_basic'>
        <A href="javascript: delete_func(${order_itemno })"><img src="/order_pay/images/delete1.png" title="주문 삭제"></A>
      </TD>
    </TR>
    </c:forEach>
    
  </TABLE>
    <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='/order_pay/list.do'" class="btn btn-primary">전체 주문 목록</button>
  </DIV>
  
</DIV>                        

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>