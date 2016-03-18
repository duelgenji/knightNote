/**
 * Created by knight on 16/3/18.
 */


function timeago(date){
    var now = new Date();
    var target = new Date();
    target.setYear(parseInt(date.substring(0,4)));
    target.setMonth(parseInt(date.substring(5,7))-1);
    target.setDate(parseInt(date.substring(8,10)));
    target.setHours(parseInt(date.substring(11,13)));
    target.setMinutes(parseInt(date.substring(14,16)));
    target.setSeconds(parseInt(date.substring(17,19)));

    now = now.getTime();
    target = target.getTime();

    var differ = (now - target) / 1000;


    if( differ < 3600){
        return "刚才";
    }
    else if( differ < 3600 * 24 ){
        return parseInt(differ / 3600) + "小时前";
    }else if ( 3600 * 24 <= differ){
        return parseInt(differ/(3600*24))+ "天前";
    }



}