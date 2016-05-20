package com.knightNote.service.wx;

import com.knightNote.entity.system.WxAccessToken;
import com.knightNote.repository.system.WxAccessTokenRepository;
import com.knightNote.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by knight on 16/5/18.
 */
@Service
public class WxService {

    @Autowired
    WxAccessTokenRepository wxAccessTokenRepository;

    public String getAccessToken(){

        String token;

        WxAccessToken wxAccessToken = wxAccessTokenRepository.findFirstByOrderByIdDesc();

        if(wxAccessToken == null || (wxAccessToken.getCreatedDate().getMillis() + wxAccessToken.getExpiresIn()*1000 < new Date().getTime())){
            Map<String, String> res = WxUtils.request(WxUtils.RequestType.GET,"token?grant_type=client_credential&appid="+WxUtils.WX_APP_ID+"&secret="+WxUtils.WX_APP_SECRET);
            token = res.get("access_token");

            wxAccessToken = new WxAccessToken();
            wxAccessToken.setAccessToken(token);
            wxAccessToken.setExpiresIn(Double.parseDouble(res.get("expires_in")));
            wxAccessTokenRepository.save(wxAccessToken);

        }else{
            token = wxAccessToken.getAccessToken();
        }
        return token;

    }

}
