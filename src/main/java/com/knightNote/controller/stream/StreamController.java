package com.knightNote.controller.stream;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.knightNote.entity.stream.Streamer;
import com.knightNote.repository.stream.StreamerLiveRepository;
import com.knightNote.repository.stream.StreamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by knightNote on 15/10/9.
 */
@RestController
@RequestMapping("stream")
public class StreamController {

    @Autowired
    StreamerRepository streamerRepository;

    @Autowired
    StreamerLiveRepository streamerLiveRepository;


    /**
     * 注册
     */
    @Transactional
    @RequestMapping("retrieveStreamerList")
    public Map<String, Object> retrieveStreamerList() {

        Map<String, Object> res = new HashMap<>();

        List<Streamer> streamerList = streamerRepository.findAll();

        res.put("success",1);
        res.put("list",streamerList);
        return res;
    }


    @RequestMapping("test")
    public void test() {

        String url = "http://www.douyu.com/312410";
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
//        webClient.waitForBackgroundJavaScript(1000);
//        webClient.waitForBackgroundJavaScriptStartingBefore(1000);


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
    }

}

