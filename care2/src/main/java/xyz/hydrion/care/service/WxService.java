package xyz.hydrion.care.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.json.GsonJsonParser;
import xyz.hydrion.wxhelper.AccessTokenUtil;
import xyz.hydrion.wxhelper.bean.SendMsg;


import java.util.Map;

@Service
public class WxService {

    @Value("${wx.at.api}")
    private String api;
    @Value("${wx.at.appid}")
    private String appId;
    @Value("${wx.at.appsecret}")
    private String secret;
    @Value("${wx.at.grant-type}")
    private String grantType;
    @Value("${wx.send.api}")
    private String sendApi;

    void updateAccessToken(){
        AccessTokenUtil.update(appId,secret,grantType);
    }

    public String getAccessToken(){
        return AccessTokenUtil.getAccessToken();
    }

    public void sendMsg(SendMsg msg){
        String url = sendApi + getAccessToken();
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(url, msg, String.class);
        System.out.println(responseEntity.getBody());

    }

    private Map<String,Object> getMapFromApi(String api){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(api,String.class);
        JsonParser parser = new GsonJsonParser();
        return parser.parseMap(responseEntity.getBody());
    }

}
