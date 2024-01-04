package com.elgunsh.courseerpbackend.controller;

import com.elgunsh.courseerpbackend.model.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public static String hello(){
        return "hello app";
    }

    @GetMapping("/test")
    public static BaseResponse<String> test(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.success(userDetails.getUsername());
    }
    @GetMapping("/test/no-auth")
    public static BaseResponse<String> testNoAuth(){
        return BaseResponse.success("test data for no auth");
    }
}
