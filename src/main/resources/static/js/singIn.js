$(function () {
    singIn.init();
});


var singIn={

    init:function () {
        singIn.clickInit();
    },
    clickInit:function () {
        $("#singIn_btn").on("click",singIn.singIn);
    },
    singIn:function () {
        if(!singIn.checkAccount($("#account").val())) return;
        if(!singIn.checkPassWord($("#password").val())) return;

        var url="singin";
        var data={};
        data.account=$("#account").val();
        data.password=$("#password").val();
        $.ajax({
            url:url,
            data:data,
            dataType:"json",
            type:"post",
            success:function (result) {
                if(result.code==1){
                    alert("注册的成功,请登陆!");
                    window.location.href="/singuppage"
                }else{
                    alert(result.message);
                }

            },
            error:function () {
                alert("通讯失败");
            }

        })
    },
    
    checkAccount:function (data) {
        var length=data.length;
        if(data==null||data=="") {
            alert("请输入正确的账号");
            return false;
        }else if(length>15){
            alert("账号长度不能超过15位");
            return false;
        }

        return true;
    },
    checkPassWord:function (data) {
        var length=data.length;
        if(data==null||data=="") {
            alert("请输入正确的密码");
            return false;
        }else if(length>15){
            alert("密码长度不能超过15位");
            return false;
        }

        return true;
    }
}