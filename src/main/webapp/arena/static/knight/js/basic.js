/**
 * Created by knight on 15/10/16.
 */
/** @namespace obj.createDate */
/** @namespace $ */


$.ajaxSetup({
    //dataType:"json",
    timeout:10000,//ajax请求超时时间10秒
    beforeSend:function(){
        //console.log("ajax before send");
    },
    complete:function(result){
        //console.log("complete");
        var res = eval("("+result.responseText+")");
        if(res.success == "0" && res.error =="000"){
            self.location = "index.html"
        }
    },
    error:function(xhr,status,error){
        //console.log(xhr.status);
    }
});

var ContextUrl=window.location.protocol+"//"+window.location.host+"/knight";
if(window.location.host.match("localhost")){
    ContextUrl=window.location.protocol+"//"+window.location.host;
}
function postRequest(url,data,successFunction){
    $.ajax({
        type:"post",
        headers: {accessToken: localStorage.accessToken ? localStorage.accessToken:""},
        data:data,
        url:ContextUrl+url,
        success:successFunction
    });
}