package com.knightNote.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by knight on 16/5/18.
 */
public class WxUtils {

    private static final String WX_URL = "https://api.weixin.qq.com/cgi-bin/";

    public static final String WX_APP_ID = "wxc3a33fcf4b575200";
    public static final String WX_APP_SECRET = "751907d0e410636041a63d1b9e3f1624";

    public enum RequestType{
        GET,POST
    }


    public static Map<String, String> request(RequestType requestType, String url){
        return request(requestType,url,null);
    }

    public static Map<String, String> request(RequestType requestType, String url, Map<String,String> map){
        RestTemplate restTemplate = new RestTemplate();
        JSONObject json;
        Map<String, String> res = new HashMap<>();
        String result;
        String key;
        try {

            if(requestType.equals(RequestType.GET)){
                result = restTemplate.getForObject(WX_URL + url , String.class);

            }else{
                HttpHeaders headers = new HttpHeaders();

                HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);
                result = restTemplate.postForObject(WX_URL + url , request, String.class);
            }

            json = new JSONObject(result);

            Iterator it = json.keys();

            while(it.hasNext()){
                key = it.next().toString();
                res.put(key,json.get(key).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();


//        String now = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
//        String sig = DigestUtils.md5Hex(SUB_ACCOUNT_ID + SUB_ACCOUNT_KEY + now);


        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json;charset=utf-8");
//        List<MediaType> mediaTypeList = new ArrayList<>();
//        mediaTypeList.add(MediaType.APPLICATION_JSON);
//        headers.setAccept(mediaTypeList);
//        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>();
        map.put("from", "13774322959");
        map.put("to", "13774296561");
        map.put("maxCallTime", "90");
        map.put("countDownTime", "90");
        map.put("needRecord", "1");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);


//        String result = restTemplate.getForObject(WX_URL + "token?grant_type=client_credential&appid="+WX_APP_ID+"&secret="+WX_APP_SECRET, String.class);
//        System.out.println(result);
//        JSONObject json = new JSONObject(result);
//
//        System.out.println("token:"+ json.get("access_token") + ", time"+json.get("expires_in"));

        Map<String,String> res = new HashMap<>();
        res.put("asd","asd");

        System.out.println(res.get("asd"));
        System.out.println(res.get("asd1"));


    }

}
