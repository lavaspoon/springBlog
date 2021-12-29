$('#Members-addMember-submit').on('click', function (){
    var userID = $('#userID').val();
    var userPWD = $('#userPWD').val();
    var userPWD_Check = $('#userPWD_Check').val();
    var userName = $('#userName').val();
    var userPhone = $('#userPhone').val();

    if(userID == ""){
        $("#ID_check").text("아이디를 입력해주세요");
    } else if (userPWD == "") {
        $("#PWD_check").text("비밀번호를 입력해주세요");
    } else if (userPWD_Check == "") {
        $("#PWD_Check_check").text("비밀번호를 입력해주세요");
    } else if (userPWD != userPWD_Check) {
        $("#PWD_Check_check").text("비밀번호가 일치하지 않습니다");
    } else {
        $.ajax({
            url: "/html/Members/addMember",
            type: "POST",
            data:
                {
                    userID : userID,
                    userPWD : userPWD,
                    userPWD_Check : userPWD_Check,
                    userName : userName,
                    userPhone : userPhone
                },
            success: function (data){
                alert(data.message);
            },
            error: function (){
                alert("server error");
            }
        });
    }
});

/*
리다이렉트로 주소가 찍혀서 해당 컨트롤러 호출 못하는 버그 발생
로그인된 상태에서는 해당 기능 정상작동 -> requsetURL 이 다름
정상 : http://localhost:8081/html/Members/addMember
비정상(현재) : Request URL: http://localhost:8081/html/Login/login?redirectURL=/html/Members/ckeckID

function checkID(){
    var userID = $('#userID').val();
    $('#ID_ava').text("");
    $('#ID_dis').text("");
    $.ajax({
        url:"/html/Members/ckeckID",
        type:"POST",
        data:
            {
                userID: userID
            },
        success:function(count){
            if(count == 0){
                $('#ID_ava').text("사용가능");
            } else {
                $('#ID_dis').text("사용불가능");
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
};

*/