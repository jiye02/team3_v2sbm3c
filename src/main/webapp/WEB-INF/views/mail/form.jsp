<%@ page contentType="text/html; charset=UTF-8" %>
 
<% String root = request.getContextPath(); // context 추출 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=5.0, width=device-width" />

<title>메일 쓰기 http://localhost:9091/mail/form.do</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
 
<body>
 
<div style='width: 80%; margin: 0px auto;'>
<div class='title_line'>mail 쓰기</div>
<form name='mailForm' method='post' action="./send.do">
  <table class='table_basic'>
    <colgroup>
      <col style='width: 20%;' />   <!-- 출력 순서 -->
      <col style='width: 80%;' /> <!-- 제목 -->
    </colgroup>
    <tr>
      <th class='th_basic'>받는 사람</th>
      <td class='td_left'><input type="text" name="receiver" value='testcell2014@studydesk.co.kr' class='input_basic' style='width: 50%;'></td>
    </tr>
    <tr>
      <th class='th_basic'>보내는 사람</th>
      <td class='td_left'><input type="text" name="from" value='testcell2014@gmail.com' class='input_basic' style='width: 90%;'></td>
  </tr>
  <tr>
    <th class='th_basic'>제 목</th>
    <td class='td_left'><input type="text" name="title" value="회원 가입해주셔서 감사합니다." class='input_basic' style='width: 90%;'></td>
  </tr>
  <tr>
    <td class='td_left' colspan="2">
      <textarea name="content" rows="15"  style='width: 100%; border: #AAAAAA 1px solid;'>오늘 저녁에 많은 눈 예상</textarea>
    </td>
  </tr>
</table>
 
<div  class="bottom_menu">
  <input type="submit" value="보내기">
  <input type="button" value="취소" onclick="history.back()">
</div>
</form>
</div>
 
</body>
</html>

