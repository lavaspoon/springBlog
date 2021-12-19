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
                alert(data.message);
            },
            error: function (){
                alert("server error");
            }
        });
    }
});