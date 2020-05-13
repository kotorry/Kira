package com.example.demo.controller;


import com.example.demo.config.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocketController
 * @author zhengkai.blog.csdn.net
 */
@RestController
public class DemoController {

//    @GetMapping("index")
//    public ResponseEntity<String> index(){
//        return ResponseEntity.ok("请求成功");
//    }
//
//    @GetMapping("page")
//    public ModelAndView page(){
//        return new ModelAndView("websocket");
//    }
    @RequestMapping("reflush")
    public void reflush()throws Exception{
        WebSocketServer.sendAll();
    }
    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws Exception {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}
