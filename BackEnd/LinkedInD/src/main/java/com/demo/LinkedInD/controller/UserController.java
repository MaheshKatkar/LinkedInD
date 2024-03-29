package com.demo.LinkedInD.controller;

import com.demo.LinkedInD.Services.JwtUtil;
import com.demo.LinkedInD.Services.UserInfoService;
import com.demo.LinkedInD.dto.AuthReq;
import com.demo.LinkedInD.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthReq authReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUserName(), authReq.getPassWord()));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(authReq.getUserName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
