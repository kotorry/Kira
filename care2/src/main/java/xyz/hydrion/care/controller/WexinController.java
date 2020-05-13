package xyz.hydrion.care.controller;


import xyz.hydrion.wxhelper.WxHelper;
import xyz.hydrion.wxhelper.bean.WxEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.hydrion.wxhelper.bean.SendMsg;
import xyz.hydrion.care.service.WxService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/v1/wx")
public class WexinController {

    @Autowired
    WxService wxService;

    private static final String TOKEN = "seeLight";

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public void doGet(
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) throws IOException {

        WxHelper.respond(response.getWriter(),TOKEN,
                signature,timestamp,nonce,echostr);
    }

    //该接口用于接收微信发送的事件推送
    @RequestMapping(value = "/check", method = RequestMethod.POST, consumes = "text/xml")
    @ResponseBody
    public void doPost(@RequestBody WxEvent event){
        System.out.println(event.getEvent() + " " + event.getCreateTime());
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public void test(){
        SendMsg msg = new SendMsg();
        msg.setUrl("");
        msg.setToUser("oWafF0wSXRWGasOJXUeYxViqXyqo");
        msg.setTemplateId("GwPvwbNgKo-HhpGp0U7vWxqaZl3f-zDCg61dI8tDh80");
        msg.setTopColor("#FF0000");
        Map<String, SendMsg.DataValue> map = new HashMap<>();
        map.put("status",new SendMsg.DataValue("normal","#173177"));
        msg.setData(map);
        wxService.sendMsg(msg);
    }
}
