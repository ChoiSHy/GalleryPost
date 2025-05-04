<%--
  User: 최성현
  Date: 2025-05-05
  Time: 오전 2:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>갤러리 게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<div>
    <c:if test="${not empty userId}">
        <span><label>${userUsername}</label>님 환영합니다.</span>
        <button type="button" id="btn-sign-out">로그아웃</button>
    </c:if>
    <c:if test="${empty userId}">
        <button type="button" id="btn-sign-in">로그인</button>
    </c:if>

</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $('document').ready(function (){
        $('#btn-sign-in').click(function (){
            location.href="/sign-in/page.do";
        }); // #btn-sign-in
        $('#btn-sign-out').click(function (){
            location.href="/sign/sign-out.do";
        })
    }); // document;
</script>
</body>
</html>
