package com.knightNote.utils;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 16/6/14.
 */
public class HttpUtils {

    public static String request(String url){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> res = new HashMap<>();
        String result;
        result = restTemplate.getForObject(url , String.class);

        return result;
    }

    public static void homePage(String url){
        String str;
        //创建一个webclient
        WebClient webClient = new WebClient();
//        //htmlunit 对css和javascript的支持不好，所以请关闭之
//        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//        webClient.waitForBackgroundJavaScript(10000);
        //获取页面
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
            synchronized ( page ){
                page.wait(10000);

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        //获取页面的TITLE
//        str = page.getTitleText();
//        System.out.println(str);
//        //获取页面的XML代码
        System.out.println(page.asXml());
//        //获取页面的文本
//        str = page.asText();
//        System.out.println(str);
    }

    public static void main(String[] args) {
//        System.out.println(request("http://www.douyu.com/32892"));

        homePage("http://www.douyu.com/32892");


    }

}
