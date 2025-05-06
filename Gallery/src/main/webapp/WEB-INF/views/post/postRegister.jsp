<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>

<body>
    <div>
        <h1>갤러리 작성</h1>

        <div>
            <form>
                <div>
                    <label for="title">제목</label><input type="text" name="post_title" id="title" maxlength="30">
                </div>
                <div>
                    <label for="content">내용</label><textarea id="content" name="post_content"
                        maxlength="300"></textarea> 
                    </div>
                <div>
                    <label for="imgs">첨부파일</label>
                    <input type="file" name="post_imgs" multiple="multiple" id="imgs"
                    accept=".gif, .jpg, .png">
                </div>
            </form>
            <div>
                <button id="btn-regist">작성</button>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
    <script type="text/javascript">
        const maxLength = 5;

        $(document).ready(function(){
            $('#btn-regist').click(function(){
                // formData 객체 생성
                let formData = new FormData();
                // 업로드 할 파일
                let inputFile = $("input[name='post_imgs']");
                let files = inputFile[0].files;

                //제목 및 내용
                let title = $('#title').val();
                let content = $('#content').val();

                console.log(files);
                
                /*유효성 검사*/

                //제목 및 내용
                if(!title || !content){
                    alert("제목과 내용을 모두 입력해주세요.");
                    return;
                }
                // 이미지 개수 제한
                if(files.length > maxLength){
                    alert("업로드 가능한 이미지는 5개 이하입니다.");
                    return;
                }
                // 확장자 검사 및 파일 담기
                for(let i =0;i<files.length;i++){
                    const file = files[i];
                    // 파일 타입 image가 아닐시
                    if(!file.type.startsWith('image/')){
                        alert("이미지 파일만 업로드할 수 있습니다.");
                        return;
                    }
                    formData.append("images",file);
                }
                const jsonData = {
                    title: title,
                    content: content
                };
                formData.append('postData', new Blob([JSON.stringify(jsonData)],{type:'application/json'} ));

                $.ajax({
                     url:'/post/insPost.do'
                    ,type: 'POST'
                    ,data: formData
                    ,contentType: false
                    ,processData: false
                    ,success: function(response){
                        alert("업로드 성공!!");
                    }//success
                    ,error: function(xhr,status,error){
                        alert("업로드 실패: "+error);
                    }//error
                })//ajax

            }); // #btn-regist
        }); // document

    </script>
</body>

</html>