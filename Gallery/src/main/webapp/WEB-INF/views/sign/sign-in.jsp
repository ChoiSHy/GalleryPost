<%--
  User: 최성현
  Date: 2025-05-04
  Time: 오후 5:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h2>로그인</h2>
    <form action="login.jsp" method="post">
        <div class="mb-3">
            <label class="form-label" for="id">아이디</label>
            <input class="form-control" type="text" name="userUsername" id="id"/>
        </div>
        <div class="mb-3">
            <label class="form-label" for="pwd">비밀번호</label>
            <input class="form-control" type="password" name="userPassword" id="pwd"/>
        </div>
        <button class="btn btn-outline-primary btn-sm" type="button" id="btn-sign-in">로그인</button>
        <button class="btn btn-outline-primary btn-sm" type="button" id="btn-sign-up">회원가입</button>
        <button class="btn btn-outline-primary btn-sm" type="button" id="btn-back">뒤로가기</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    /*아이디 형식 정규식 검사*/
    function isId(value) {
        const idRegExp = /^[a-z]+[a-z0-9]{5,19}$/g;     //영문으로 시작하는 영문/숫자 6~20자
        return idRegExp.test(value);
    }

    /*비밀번호 형식 정규식 검사*/
    function isPw(value) {
        const pwRegExp = /^.{8,20}$/   // 영문 숫자 조합 8~20자리
        return pwRegExp.test(value);
    }

    $('document').ready(function () {
        /*뒤로가기*/
        $('#btn-back').click(function () {
            location.href = "/post/postList.do";
        }) // #btn-back

        /*회원가입*/
        $('#btn-sign-up').click(function () {
            location.href = "/sign-up/page.do";
        }) // #btn-sign-up

        /*로그인*/
        $('#btn-sign-in').click(function () {
                if (!isId($('#id').val())) {
                    alert("아이디는 영문으로 시작하는 6~20자리의 영문/숫자 조합입니다.")
                } else if (!isPw($('#pwd').val())) {
                    alert("비밀번호는 8~20자리 입니다.");
                    $('#pwd').val("");
                } else {
                    $.ajax({
                        url: '/sign/sign-in.do'
                        , type: 'post'
                        , data: $('form').serialize()
                        , success: function (result) {
                            if (result.check) {
                                alert(result.msg);
                                location.href = "/post/postList.do";
                            } else{
                                alert(result.msg);
                                $('#pwd').val("");
                            }
                        } // success
                    })// ajax
                    //
                }
            }
        )//#btn-sign-in
    }) // document

</script>
</body>
</html>
