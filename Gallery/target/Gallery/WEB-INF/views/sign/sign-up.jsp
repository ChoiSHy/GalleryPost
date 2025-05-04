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
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h2>회원가입</h2>
    <form action="login.jsp" method="post">
        <div class="mb-3">
            <label class="form-label" for="id">아이디</label>
            <input class="form-control" type="text" name="userUsername" id="id"/>
            <button class="btn btn-outline-primary btn-sm" type="button" id="btn-id-check">아이디 중복확인</button>
        </div>
        <div class="mb-3">
            <label class="form-label" for="pwd">비밀번호</label>
            <input class="form-control" type="password" name="userPassword" id="pwd"/>
        </div>
        <button class="btn btn-outline-primary btn-sm" type="button" id="btn-sign-up" disabled="disabled">회원가입</button>
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
    function isPw(value){
        const pwRegExp = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/   // 영문 숫자 조합 8~20자리
        return pwRegExp.test(value);
    }
    $('document').ready(function(){
        /*아이디 중복 검사*/
        $('#btn-id-check').click(function(){
            if( isId($('#id').val()) ){
                $.ajax({
                    url:'/sign/idCheck.do'
                    ,data: {
                        userUsername: $('#id').val()
                    } // data
                    ,type: 'post'
                    ,dataType: 'json'
                    ,success: function(result){
                        if(result.check == true){
                            alert(result.msg);
                            $('#btn-sign-up').attr('disabled', false);
                        }
                        else{
                            alert(result.msg);
                            $('#id').val('');
                        }
                    } // success
                });
            } // if isId
            else{
                alert("아이디는 영문으로 시작하는 영문자/숫자 조합 6~20자로 지정해주세요.");
            } // else

        }); //#btn-id-check
        /* 중복 확인 후, 아이디 변경 시*/
        $('#id').keydown(function(){
            $('#btn-sign-up').attr('disabled', true);
        })
        /*회원가입 절차*/
        $('#btn-sign-up').click(function (){
            if(!isId($('#id').val())){
                alert("아이디는 영문으로 시작하는 영문자/숫자 조합 6~20자로 지정해주세요.")
            }
            if(!isPw($('#pwd').val())){
                alert("비밀번호는 영문자/숫자 조합 8~20자로 지정해주세요.")
            }
            else{
                $.ajax({
                     url: '/sign/sign-up.do'
                    ,data: $('form').serialize()
                    ,type:'post'
                    ,success: function(result){
                         alert(result.msg);
                         location.href="/sign-in/page.do";
                    }
                    ,error: function (){
                         alert("회원가입 실패");
                    }
                })
            } // if isId & isPw
        }); // #btn-sign-up
        $('#btn-back').click(function(){
            location.href="/sign-in/page.do";
        })

    }); // document



    

</script>
</body>
</html>
