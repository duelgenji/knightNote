package com.knightNote.service.wx;

import com.fasterxml.jackson.databind.JsonNode;
import com.knightNote.dto.wx.WxMenuDto;
import com.knightNote.entity.wx.WxAccessToken;
import com.knightNote.repository.wx.WxAccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


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
    WxAccessTokenRepository wxAccessTokenRepository;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 微信基础接口 返回access token
     */
    public String getAccessToken(){

        String token;

        WxAccessToken wxAccessToken = wxAccessTokenRepository.findFirstByOrderByIdDesc();

        if(wxAccessToken == null || (wxAccessToken.getCreatedDate().getMillis() + wxAccessToken.getExpiresIn()*1000 < new Date().getTime())){
            JsonNode result = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wxAppId+"&secret="+wxAppSecret, JsonNode.class);

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
     * 创建菜单
     */
    public void createMenu(){

        WxMenuDto dto = new WxMenuDto();

        JsonNode result = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken(),dto, JsonNode.class);

        int errcode = result.get("access_token").asInt();
        String errmsg = result.get("access_token").asText();
    }


    /**
     * 获取菜单
     */
    public void getMenu(){

        WxMenuDto dto = new WxMenuDto();

        JsonNode result = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+getAccessToken(),dto, JsonNode.class);

        int errcode = result.get("access_token").asInt();
        String errmsg = result.get("access_token").asText();
    }

}
