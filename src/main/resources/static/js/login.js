$(function () {
    login.init();
});

var login={

    init:function () {
        login.clickInit();
    },
    
    clickInit:function () {
        $("#login_btn").on("click",login.singUp);
        $("#singIn_btn").on("click",function () {
            window.location.href="singinpage";
        })
        login.enterEvent();
    },
    singUp:function () {

        var url="singup";
        var data={};
        data.account=$("#accounts").val();
        data.password=$("#password").val();
        $.ajax({
            url:url,
            data:data,
            dataType:"json",
            type:"post",
            success:function (result) {
                if(result.code==1){
                    console.log("token:"+result.data)
                    login.h5Storage(result.data);
                    window.location.href="/";
                }else{
                    alert(result.message);
                }


            },
            error:function () {

            }

        })
    },

    enterEvent:function () {
        $(document).keyup(function (e) {//捕获文档对象的按键弹起事件
            if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了
                //此处编写用户敲回车后的代码
                login.singUp();
            }
        });
    },

    
    h5Storage:function (data) {

        if(window.sessionStorage){
           // alert('ok');
        }else{
            alert('fail');
        }
        // 设置值
        sessionStorage.setItem('token', data);
        // 取值
        var token = sessionStorage.getItem('token');
        console.log(token);
    }
}