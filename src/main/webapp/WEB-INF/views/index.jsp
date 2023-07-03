<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" />
<title>Art Wave</title>
<link rel="shortcut icon" href="/images/ex_top.png" />
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<!-- /static 기 준 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
<style>
.carousel-item img {
  max-width: 50%; /* 이미지의 최대 너비를 50%로 설정 */
  max-height: 50%; /* 이미지의 최대 높이를 50%로 설정 */
  display: block; /* 이미지를 블록 요소로 설정하여 가로 중앙 정렬을 적용합니다. */
  margin: 0 auto; /* 좌우 여백을 auto로 설정하여 가로 중앙 정렬을 적용합니다. */
}



</style>


	<c:import url="/menu/top.do" />
	<%-- <jsp:include page="../menu/top.jsp" flush='false' /> --%>

<div id="carouselExampleCaptions" class="carousel slide"
data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0"
        class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="3"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="4"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
      <A href="/gallery/list_by_exhino.do?exhino=1&now_page=1">
        <img src="./images/미술-001.jpg" class="d-block w-100" alt="...">
        </A>
        <div class="carousel-caption d-none d-md-block"></div>
      </div>
      <div class="carousel-item">
      <A href="/gallery/list_by_exhino.do?exhino=2&now_page=1">
        <IMG src="./images/의류-001.jpg" class="d-block w-100">
        </A>
        <div class="carousel-caption d-none d-md-block"></div>
      </div>
      <div class="carousel-item">
      <A href="/gallery/list_by_exhino.do?exhino=3&now_page=1">
        <IMG src="./images/팝업-001.jpg" class="d-block w-100">
        </A>
      </div>
      <div class="carousel-item">
      <A href="/gallery/list_by_exhino.do?exhino=4&now_page=1">
        <IMG src="./images/이색-001.jpg" class="d-block w-100">
        </A>
      </div>
      <div class="carousel-item">
      <A href="/gallery/list_by_exhino.do?exhino=5&now_page=1">
        <IMG src="./images/지역-001.jpg" class="d-block w-100">
        </A>
      </div>

    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions"
      role="button" data-slide="prev"> <span
      class="carousel-control-prev-icon" aria-hidden="true" style="filter: invert(100%);"></span> <span
      class="sr-only">Previous</span>
    </a> <a class="carousel-control-next" href="#carouselExampleCaptions"
      role="button" data-slide="next"> <span
      class="carousel-control-next-icon" aria-hidden="true" style="filter: invert(100%);"></span> <span
      class="sr-only">Next</span>
    </a>
  </div>


	<jsp:include page="./menu/bottom.jsp" flush='false' />

</body>
</html>

