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
            contentType: 'application/json',
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