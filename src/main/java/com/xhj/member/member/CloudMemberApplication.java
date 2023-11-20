package com.xhj.member.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.xhj.auth.api","com.xhj.member.member.fiegn"})
public class CloudMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMemberApplication.class, args);
    }

}
