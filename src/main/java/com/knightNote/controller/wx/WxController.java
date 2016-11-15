package com.knightNote.controller.wx;

import com.knightNote.entity.system.WxPostMessage;
import com.knightNote.repository.system.WxPostMessageRepository;
import com.knightNote.service.wx.WxService;
import com.knightNote.utils.SignUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.knightNote.utils.WxUtils.doc2String;

/**
 * Created by knight on 16/5/19.
 */
@RestController
@RequestMapping("wx")
public class WxController {

    @Autowired
    private WxService wxService;

    @Autowired
    private WxPostMessageRepository wxPostMessageRepository;

    @Transactional
    @RequestMapping("token")
    public Map<String ,Object> wx(){
        Map<String, Object> res = new HashMap<>();

        res.put("success", 1);
        res.put("token", wxService.getAccessToken());
        return res;
    }



    @RequestMapping(value = "message",method = RequestMethod.POST)
    public Map<String ,Object> message(
            @RequestBody String body
    ){
        Map<String, Object> res = new HashMap<>();

        try {
            Document document = DocumentHelper.parseText(body);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                res.put(elm.getName(), elm.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        res.put("success", "post");
        return res;
    }

    @RequestMapping(value = "retrieveMessage",method = RequestMethod.GET)
    public String retrieveMessage(
            @RequestParam String signature,
            @RequestParam String timestamp,
            @RequestParam String nonce,
            @RequestParam String echostr
    ){
        Map<String, Object> res = new HashMap<>();

        if(SignUtil.checkSignature("genji",signature,timestamp,nonce)){
            res.put("echostr", echostr);
        }

        return echostr;
    }

    @RequestMapping("retrieveMessage")
    public String retrieveMessage(
            @RequestBody String body
    ){
        Map<String, String> res = new HashMap<>();

        try {

            WxPostMessage wxPostMessage = new WxPostMessage();

            Document document = DocumentHelper.parseText(body);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                res.put(elm.getName(), elm.getText());

            }
            wxPostMessage.setToUserName(res.get("ToUserName"));
            wxPostMessage.setFromUserName(res.get("FromUserName"));
            wxPostMessage.setCreateTime(res.get("CreateTime"));
            wxPostMessage.setMsgType(res.get("MsgType"));
            wxPostMessage.setContent(res.get("Content"));
            wxPostMessage.setMsgId(res.get("MsgId"));
            wxPostMessageRepository.save(wxPostMessage);

            Map<String,String> map = new HashMap<>();

            map.put("ToUserName",res.get("FromUserName"));
            map.put("FromUserName",res.get("ToUserName"));
            map.put("CreateTime",""+new Date().getTime());
            map.put("MsgType","text");
            map.put("Content",res.get("Content"));

            document = DocumentHelper.createDocument();
            nodeElement = document.addElement("xml");
            for (Object obj : map.keySet()) {
                Element keyElement = nodeElement.addElement(String.valueOf(obj));
//            keyElement.addAttribute("label", String.valueOf(obj));
                keyElement.setText(String.valueOf(map.get(obj)));
            }

            return doc2String(document);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }



    @RequestMapping("localTest")
    public String localTest(){
        Map<String,String> map = new HashMap<>();

        map.put("ToUserName","123");
        map.put("FromUserName","321");
        map.put("CreateTime","12312312321");
        map.put("MsgType","asdasd12312asd");
        map.put("Content","asdas12312gg");

        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("xml");
        for (Object obj : map.keySet()) {
            Element keyElement = nodeElement.addElement(String.valueOf(obj));
//            keyElement.addAttribute("label", String.valueOf(obj));
            keyElement.setText(String.valueOf(map.get(obj)));
        }
        System.out.println(document);
        System.out.println(doc2String(document));
        return doc2String(document);

    }
}
