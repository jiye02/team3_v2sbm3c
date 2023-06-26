<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko">
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Art Wave</title>
 <link rel="shortcut icon" href="/images/ex_top.png" /> <%-- /static 기준 --%>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
  function update_cnt(basketno) {  // 수량 변경
    var frm = $('#frm_post');
    frm.attr('action', './update_cnt.do');
    $('#basketno',  frm).val(basketno);
    
    var new_cnt = $('#' + basketno + '_cnt').val();  // $('#1_cnt').val()로 변환됨, 사용자가 변경한 수량을 다시 읽어옴 ★.

    if (new_cnt > 0) {
        $('#cnt',  frm).val(new_cnt);
     
        frm.submit();
    } else {
        alert('수량은 1개 이상이어야 합니다.');
    }
    
  }
  
  function delete_func(basketno) {  // GET -> POST 전송, 상품 삭제
    var frm = $('#frm_post');
    frm.attr('action', './delete.do');
    $('#basketno',  frm).val(basketno);
    
    frm.submit();
  }   


</script>

<style type="text/css">

    
</style>
 
</head> 
 
<body>
<c:import url="/menu/top.do" />

<%-- GET -> POST: 수량 변경, 상품 삭제용 폼 --%>
<form name='frm_post' id='frm_post' action='' method='post'>
  <input type='hidden' name='basketno' id='basketno'>
  <input type='hidden' name='cnt' id='cnt'>
</form>
 
<DIV class='title_line'>장바구니</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <%-- 
    http://localhost:9091/basket/list_by_memberno.do
    http://localhost:9091/basket/list_by_memberno.do?exhino=
    http://localhost:9091/basket/list_by_memberno.do?exhino=4  <- 이런 패턴만 링크 출력
    --%>
    <c:if test="${param.exhino != null and param.exhino != ''}"> 
      <A href="/gallery/list_by_exhino_search_paging.do?exhino=${param.exhino }">쇼핑 계속하기</A>
      <span class='menu_divide' >│</span>  
    </c:if>
 
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>

  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 40%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col> <%-- 수량 --%>
      <col style="width: 10%;"></col> <%-- 합계 --%>
      <col style="width: 10%;"></col>
    </colgroup>
    <%-- table 컬럼 --%>
<!--     <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>상품명</th>
        <th style='text-align: center;'>정가, 할인률, 판매가, 포인트</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    
    </thead> -->
    
    <%-- table 내용 --%>
    <tbody>
      <c:choose>
        <c:when test="${list.size() > 0 }"> <%-- 상품이 있는지 확인 --%>
          <c:forEach var="basketVO" items="${list }">  <%-- 상품 목록 출력 --%>
            <c:set var="basketno" value="${basketVO.basketno }" />
            <c:set var="galleryno" value="${basketVO.galleryno }" />
            <c:set var="title" value="${basketVO.title }" />
            <c:set var="thumb1" value="${basketVO.thumb1 }" />
            <c:set var="price" value="${basketVO.price }" />
            <c:set var="dc" value="${basketVO.dc }" />
            <c:set var="saleprice" value="${basketVO.saleprice }" />
            <c:set var="point" value="${basketVO.point }" />
            <c:set var="memberno" value="${basketVO.memberno }" />
            <c:set var="cnt" value="${basketVO.cnt }" />
            <c:set var="tot" value="${basketVO.tot }" />
            <c:set var="rdate" value="${basketVO.rdate }" />
            
            <tr> 
              <td style='vertical-align: middle; text-align: center;'>
                <c:choose>
                  <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                    <%-- /static/gallery/storage/ --%>
                    <a href="/gallery/read.do?galleryno=${galleryno}"><IMG src="/gallery/storage/${thumb1 }" style="width: 120px; height: 80px;"></a> 
                  </c:when>
                  <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                    ${galleryVO.file1}
                  </c:otherwise>
                </c:choose>
              </td>  
              <td style='vertical-align: middle;'>
                <a href="/gallery/read.do?galleryno=${galleryno}"><strong>${title}</strong></a> 
              </td> 
              <td style='vertical-align: middle; text-align: center;'>
                <del><fmt:formatNumber value="${price}" pattern="#,###" /></del><br>
                <span style="color: #FF0000; font-size: 1.2em;">${dc} %</span>
                <strong><fmt:formatNumber value="${saleprice}" pattern="#,###" /></strong><br>
                <span style="font-size: 0.8em;">포인트: <fmt:formatNumber value="${point}" pattern="#,###" /></span>
              </td>
              <td style='vertical-align: middle; text-align: center;'>
              <%-- 레코드에 따라 ID를 고유하게 구분할 목적으로 id 값 생성, 예) 1_cnt, 2_cnt, c_cnt... --%>
                <input type='number' id='${basketno }_cnt' min='1' max='100' step='1' value="${cnt }" style='width: 52px;'><br>
                <button type='button' onclick="update_cnt(${basketno})" class='btn btn-light btn-sm' style='margin-top: 5px;'>변경</button>
              </td>
              <td style='vertical-align: middle; text-align: center;'>
                <fmt:formatNumber value="${tot}" pattern="#,###" />
              </td>
              <td style='vertical-align: middle; text-align: center;'>
                <A href="javascript: delete_func(${basketno })"><IMG src="/basket/images/delete.png" class="icon"></A>
              </td>
            </tr>
          </c:forEach>
        
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="6" style="text-align: center; font-size: 1.3em;">장바구니에 상품이 없습니다.</td>
          </tr>
        </c:otherwise>
      </c:choose>
      
      
    </tbody>
  </table>
  
  <table class="table table-striped" style='margin-top: 50px; margin-bottom: 50px; width: 100%;'>
    <tbody>
      <tr>
        <td style='width: 50%;'>
          <div class='basket_label'>상품 금액</div>
          <div class='basket_price'><fmt:formatNumber value="${tot_sum }" pattern="#,###" /> 원</div>
          
          <div class='basket_label'>포인트</div>
          <div class='basket_price'><fmt:formatNumber value="${point_tot }" pattern="#,###" /> 원 </div>
          
          <div class='basket_label'>배송비</div>
          <div class='basket_price'><fmt:formatNumber value="${baesong_tot }" pattern="#,###" /> 원</div>
        </td>
        <td style='width: 50%;'>
          <div class='basket_label' style='font-size: 1.7em;'>전체 주문 금액</div>
          <div class='basket_price'  style='font-size: 1.7em; color: #FF0000;'><fmt:formatNumber value="${total_order }" pattern="#,###" /> 원</div>
          
          <form name='frm' id='frm' style='margin-top: 50px;' action="/order_pay/create.do" method='get'>
            <button type='submit' id='btn_order' class='btn btn-info' style='font-size: 1.0em;'>주문하기</button>
          </form>
        <td>
      </tr>
    </tbody>
  </table>   
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

