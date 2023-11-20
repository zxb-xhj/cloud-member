package com.xhj.member.member.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xhj
 * @Date: 2023/10/19/23:55
 * @Description:
 */
//@FeignClient("cloud-auth")
public interface AuthServiceFiegn {

//    @GetMapping("/token/getToken1")
    String token();
}
