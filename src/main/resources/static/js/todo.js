$(function () {
    todo.init();
});

var todo={

    init:function () {
        todo.clickInit();
        todo.taskListInit();
    },

    taskListInit:function () {
        var url="login/task";
        var data={};
        data.token=todo.getToken();
        $.ajax({
            url:url,
            data:data,
            dataType:"json",
            type:"get",
            success:function (result) {
                if(result.code==1){
                    todo.drawList(result.data);
                }else{
                    window.location.href="singuppage";
                }
            },error:function () {
                alert("通信失败!");
            }
        });
    },
    drawList:function (data) {
        var html="";
        for(var x=0;x<data.length;x++){
             html+='  <li class="list-group-item" data="'+data[x].id+'"><button type="button" class="close" aria-label="close" >' +
                '<span class="sr-only">Close</span></button><div class="checkbox"><label>' +
                '<span class="private_hover">'+data[x].des+'</span>' +
                '</label></div></li><hr>';
        }
        $("#size").text(data.length);
        $("#content_id").html(html);
        $(".list-group-item").on("click",todo.taskDelete);
    },

    clickInit:function () {
        $("#add_btn").on("click",todo.taskCreate);
        todo.enterEvent();
    },

    taskCreate:function () {
        if(!todo.checkInput($("#des_id").val())) return;

        var url="login/task";
        var data={};
        data.des=$("#des_id").val();
        data.token=todo.getToken();
        console.log(data);
        $.ajax({
            url:url,
            data:data,
            dataType:"json",
            type:"post",
            cache:false,
            success:function (result) {
                if(result.code==1){
                    $("#des_id").val("");
                    todo.taskListInit();
                }else{
                    alert("请登陆");
                    window.location.href="singuppage";
                }
            },error:function (XMLHttpRequest) {
                console.log(XMLHttpRequest);
                alert("通信失败");
            }
        });
        
    },
    taskDelete:function () {
        var id=$(this).attr("data");
        var data={};
        data.id=id;
        data.token=todo.getToken();
        console.log(data);
        var url="login/task?id="+id+"&token="+todo.getToken();
        $.ajax({
            url:url,
            dataType:"json",
            data:"",
            type:"delete",
            headers: {'Content-Type': 'application/json'},
            success:function (result) {
                if(result.code==1){
                    todo.taskListInit();
                }else{

                    alert("请登陆");
                    window.location.href="singuppage";
                }
            },error:function () {
                alert("通信失败!");
            }
        });
    },

    getToken:function () {
        if(window.sessionStorage){
           // alert('ok');
        }else{
            alert('fail');
        }
        // 取值
        var token = sessionStorage.getItem('token');
        console.log(token);
        return token;
    }
    ,
    enterEvent:function () {
        $(document).keyup(function (e) {//捕获文档对象的按键弹起事件
            if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了
                //此处编写用户敲回车后的代码
                todo.taskCreate();
            }
        });
    },
    checkInput:function (data) {
        if(data==null||data.trim()=="") return false;
        return true;
    }

}