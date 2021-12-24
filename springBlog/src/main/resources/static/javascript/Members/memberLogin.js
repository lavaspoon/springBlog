$('#Members-login-submit').on('click', function (){
    var userID = $('#userID').val();
    var userPWD = $('#userPWD').val();

    if(userID == ""){
        $("#ID_check").text("아이디를 입력해주세요");
    } else if (userPWD == "") {
        $("#PWD_check").text("비밀번호를 입력해주세요");
    } else {
        $.ajax({
            url: "/html/Login/login",
            type: "POST",
            data:
                {
                    userID : userID,
                    userPWD : userPWD
                },
            success: function (data){
                if(data.message == true) {
                    location.href = "/";
                } else if(data.message == false) {
                    alert("아이디와 비밀번호가 틀렸습니다.");
                }
            },
            error: function (){
                alert("server error");
            }
        });
    }
});