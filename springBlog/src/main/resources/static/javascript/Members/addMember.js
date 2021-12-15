$(document).ready(function (){
    $('#Members-addMember-submit').on('click', function (){
        $.ajax({
            url: "/html/Members/addMember",
            type: "POST",
            data: $("#Members-addMember").serialize(),
            success: function (data){
                alert("회원가입 완료");
            },
            error: function (){
                alert("회원가입 실패");
            }
        })
    });
});