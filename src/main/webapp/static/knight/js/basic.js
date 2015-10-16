/**
 * Created by knight on 15/10/16.
 */


$.ajaxSetup({
    //dataType:"json",
    timeout:10000,//ajax请求超时时间10秒
    beforeSend:function(){
        console.log("ajax before send");
    },
    complete:function(){
        console.log("complete");
    },
    error:function(xhr,status,error){
        console.log(xhr.status);
    }
})