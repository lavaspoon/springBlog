$('#Members-addMember-submit').on('click', function (){
    var userID = $('#userID').val();
    if(userID == ""){
        $("#id_check").text("아이디를 입력해주세요");
    } else {
        $.ajax({
            url: "/html/Members/addMember",
            type: "POST",
            data: $("#Members-addMember").serialize(),
            success: function (data){
                alert(data);
            },
            error: function (){
                alert("server error");
            }
        });
    }
});