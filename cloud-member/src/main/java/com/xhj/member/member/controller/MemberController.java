package com.xhj.member.member.controller;

import com.alibaba.fastjson.JSON;
import com.xhj.auth.api.AuthFiegn;
import com.xhj.member.member.Utils.WebSocketServer;
import com.xhj.member.member.fiegn.AuthServiceFiegn;
import com.xhj.member.member.service.MemberService;
import com.xhj.result.R;
import com.xhj.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: xhj
 * @Date: 2023/10/19/22:37
 * @Description:
 */
@RestController
//@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

//    @Autowired
//    private final AuthServiceFiegn authServiceFiegn;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private AuthFiegn authFiegn;
    @Autowired
    private MemberService memberService;

    private List<Map<String,Object>> list = new ArrayList<>();

    public MemberController(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",this.list.size()+1);
        map.put("message","你好2");
        map.put("sender","李四");
        map.put("name","张三");
        map.put("date", LocalDateTime.now());
        map.put("read",0);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id",this.list.size()+1);
        map2.put("message","你好2");
        map2.put("sender","张三");
        map2.put("name","李四");
        map2.put("date", LocalDateTime.now());
        map2.put("read",0);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("id",this.list.size()+1);
        map3.put("message","你好2");
        map3.put("sender","张三");
        map3.put("name","王五");
        map3.put("date", LocalDateTime.now());
        map3.put("read",0);
        list.add(map);
        list.add(map2);
        list.add(map3);
    }

    //    @Autowired
//    public MemberController(AuthFiegn authFiegn) {
//        this.authFiegn = authFiegn;
//    }
    @GetMapping("/token/getToken1")
    public R token(HttpServletRequest request, HttpServletResponse response){
        R r = authFiegn.token();
//        response.addHeader("token",token);
//        request.setAttribute("Authorization",token);
//        request.getSession().setAttribute("token",token);
//        response.addCookie(new Cookie("token",token));
        System.out.println(r.getData());
        return r;
    }

    @GetMapping("/getToken")
    public R getToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (authorization==""||authorization==null){
            return R.failed(ResultCode.TOKEN_INVALID_OR_EXPIRED);
        }
        R r = authFiegn.analyzeToken(authorization);

        return r;
    }

    @GetMapping("/getMessage/{sender}")
    public R getMessage(@PathVariable("sender") String sender){
        List<Map<String,Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (map.get("sender").equals(sender)||map.get("name").equals(sender)){
                list1.add(map);
            }
        }
        System.out.println(list1);
        return R.ok(list1);
    }

    @PostMapping("/webSocket")
    public R webSocket(@RequestBody Map<String,Object> objectMap){
//        Map<String, Object> map = new HashMap<>();
//        map.put("id",objectMap.get("id"));
//        map.put("message",objectMap.);
//        map.put("sender",sender);
//        map.put("name",name);
        objectMap.put("date", LocalDateTime.now());
        objectMap.put("read",0);
        //基于WebSocket实现发送消息
        webSocketServer.sendToAllClient(objectMap.get("id")+"", objectMap.get("message")+"");
        objectMap.put("id",list.size()+1);
        list.add(objectMap);
        return R.ok(list);
    }


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1:2","1-你好,1-6,2-你好,1-6,");
        String o = map.get("1:2");
        for (String s : o.split(",")) {
//            System.out.println(s);
            List<String> list = Arrays.asList(s.split("-"));
            if (list.get(0).equals("1")){
                System.out.println(list.get(1) + " 左");
            }else{
                System.out.println(list.get(1) + " 右");
            }
        }

    }
}
