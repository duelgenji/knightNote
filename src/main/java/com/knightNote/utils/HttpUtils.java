package com.knightNote.utils;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.web.client.RestTemplate;

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

//        webClient.getWebConsole().setLogger(null);
//        //htmlunit 对css和javascript的支持不好，所以请关闭之
        webClient.getOptions().setCssEnabled(false);
//        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(1000);
        webClient.waitForBackgroundJavaScriptStartingBefore(1000);


        //获取页面
        HtmlPage page = null;
        int times =10;
        try {
            page = webClient.getPage(url);

            for (int i = 0; i < times ; i++) {
                synchronized ( page ){
                    page.wait(2000);
                }
                if(page.getByXPath("//object")!=null){
                    System.out.println("***************************yes**********************");
                    for (int j = 0; j < page.getByXPath("//object").size(); j++) {
                        System.out.println(page.getByXPath("//object").get(j));
                    }
                    break;
                }
                System.out.println("***************************no***************************");

            }

        } catch (Exception  e) {
            e.printStackTrace();
        }
        //获取页面的TITLE
//        str = page.getTitleText();
//        System.out.println(str);
//        //获取页面的XML代码
//        System.out.println("***************************start***************************");
//        System.out.println(page.asXml());


//        System.out.println("***************************end***************************");

//        //获取页面的文本
//        str = page.asText();
//        System.out.println(str);
    }

    public static void main(String[] args) {
//        System.out.println(request("http://www.douyu.com/32892"));



        homePage("http://www.douyu.com/312410");



    }

}
