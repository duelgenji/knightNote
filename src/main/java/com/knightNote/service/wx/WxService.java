package com.knightNote.service.wx;

import com.fasterxml.jackson.databind.JsonNode;
import com.knightNote.dto.wx.WxMenuDto;
import com.knightNote.entity.wx.WxAccessToken;
import com.knightNote.entity.wx.WxMenu;
import com.knightNote.other.ServiceException;
import com.knightNote.repository.wx.WxAccessTokenRepository;
import com.knightNote.repository.wx.WxMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * Created by knight on 16/5/18.
 */
@Service
public class WxService {

    @Value("${WX_APP_ID}")
    private String wxAppId;

    @Value("${WX_APP_SECRET}")
    private String wxAppSecret;

    @Value("${WX_APP_TOKEN}")
    private String wxAppToken;

    @Autowired
    private WxAccessTokenRepository wxAccessTokenRepository;

    @Autowired
    private WxMenuRepository wxMenuRepository;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 微信基础接口 返回access token
     */
    public String getAccessToken(){

        String token;

        WxAccessToken wxAccessToken = wxAccessTokenRepository.findFirstByOrderByIdDesc();

        if(wxAccessToken == null || (wxAccessToken.getCreatedDate().getMillis() + wxAccessToken.getExpiresIn()*1000 < new Date().getTime())){
            JsonNode result = requestForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wxAppId+"&secret="+wxAppSecret);

            token = result.get("access_token").asText();

            wxAccessToken = new WxAccessToken();
            wxAccessToken.setAccessToken(token);
            wxAccessToken.setExpiresIn(result.get("expires_in").asDouble());
            wxAccessTokenRepository.save(wxAccessToken);

        }else{
            token = wxAccessToken.getAccessToken();
        }
        return token;
    }


    /**
     * 数据库里的menu更新至微信
     */
    public JsonNode createMenu(){
        return requestForObject("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken(),entityToDto());
    }

    /**
     * 获取数据库里的菜单
     */
    public Object getMenu(){
        Map<String, WxMenuDto> map = new HashMap<>();
        map.put("menu",entityToDto());
        return map;
    }

    /**
     * 从微信拉取菜单 更新至数据库
     */
    public Object fetchMenu(){
        JsonNode result = requestForObject("https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+getAccessToken());
        wxMenuRepository.deleteAll();
        Iterator<JsonNode> it, sub_it;
        JsonNode button, sub_button;
        WxMenu wxMenu, sub_wxMenu;
        it = result.get("menu").get("button").iterator();

        //两级菜单
        while(it.hasNext()){
            button = it.next();
            wxMenu = new WxMenu();

            if(button.get("name")!=null){
                wxMenu.setName(button.get("name").asText());
            }
            if(button.get("type")!=null){
                wxMenu.setType(button.get("type").asText());
            }
            if(button.get("key")!=null){
                wxMenu.setKey(button.get("key").asText());
            }
            if(button.get("url")!=null){
                wxMenu.setUrl(button.get("url").asText());
            }
            if(button.get("media_id")!=null){
                wxMenu.setMedia_id(button.get("media_id").asText());
            }
            wxMenuRepository.save(wxMenu);

            if(button.get("sub_button")!=null){
                sub_it = button.get("sub_button").iterator();
                while(sub_it.hasNext()){
                    sub_button = sub_it.next();
                    sub_wxMenu = new WxMenu();
                    if(sub_button.get("name")!=null){
                        sub_wxMenu.setName(sub_button.get("name").asText());
                    }
                    if(sub_button.get("type")!=null){
                        sub_wxMenu.setType(sub_button.get("type").asText());
                    }
                    if(sub_button.get("key")!=null){
                        sub_wxMenu.setKey(sub_button.get("key").asText());
                    }
                    if(sub_button.get("url")!=null){
                        sub_wxMenu.setUrl(sub_button.get("url").asText());
                    }
                    if(sub_button.get("media_id")!=null){
                        sub_wxMenu.setMedia_id(sub_button.get("media_id").asText());
                    }
                    sub_wxMenu.setWxMenu(wxMenu);
                    wxMenuRepository.save(sub_wxMenu);
                }
            }
        }

        return entityToDto();
    }

    public Object sendTemplate(){
        return requestForObject("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccessToken(),entityToDto());
    }


    private WxMenuDto entityToDto(){
        List<WxMenu> parentList = wxMenuRepository.findByWxMenuIsNull();
        List<WxMenu> childList ;
        WxMenuDto dto = new WxMenuDto();
        List<WxMenuDto.Button> list = new ArrayList<>();

        WxMenuDto.Button b;
        WxMenuDto.Button sub;

        //两级菜单
        for (WxMenu parent: parentList) {
            b = new WxMenuDto.Button();
            b.id = parent.getId();
            b.name = parent.getName();
            b.type = parent.getType();
            b.key = parent.getKey();
            b.url = parent.getUrl();
            b.media_id = parent.getMedia_id();
            childList = wxMenuRepository.findByWxMenu(parent);
            if(childList!=null && childList.size()>0){
                b.sub_button = new ArrayList<>();
                for (WxMenu child: childList) {
                    sub = new WxMenuDto.Button();
                    sub.id = child.getId();
                    sub.name = child.getName();
                    sub.type = child.getType();
                    sub.key = child.getKey();
                    sub.url = child.getUrl();
                    sub.media_id = child.getMedia_id();
                    b.sub_button.add(sub);
                }
            }
            list.add(b);
        }
        dto.button = list;

        return dto;
    }

    private JsonNode requestForObject(String url){
        //微信在成功时 不一定有errcode; 所以当判定没有errcode或者errcode为0时即为请求成功.
        JsonNode result = restTemplate.getForObject(url, JsonNode.class);
        JsonNode jCode = result.get("errcode");
        if(jCode!=null && jCode.asInt() != 0){
            throw new ServiceException(jCode.asInt(),result.get("errmsg").asText());
        }
        return result;
    }

    private JsonNode requestForObject(String url, Object obj){
        JsonNode result = restTemplate.postForObject(url, obj,  JsonNode.class);
        JsonNode jCode = result.get("errcode");
        if(jCode!=null && jCode.asInt() != 0){
            throw new ServiceException(jCode.asInt(),result.get("errmsg").asText());
        }
        return result;
    }
}
