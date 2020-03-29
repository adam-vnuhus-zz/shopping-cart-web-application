package com.example.shoppingcart.controller.frontend.api;

import com.example.shoppingcart.entity.User;
import com.example.shoppingcart.model.api.BaseApiResult;
import com.example.shoppingcart.model.request.CreateUserReq;
import com.example.shoppingcart.security.JwtTokenUtil;
import com.example.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody User user,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        BaseApiResult result = new BaseApiResult();
        try {
            // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gen token
            String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

            result.setSuccess(true);
            result.setMessage("Login success!");

            Cookie jwtToken = new Cookie("jwt_token", token);
            jwtToken.setMaxAge(60 * 60 * 24);
            jwtToken.setPath("/");
            response.addCookie(jwtToken);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Email or password not correct!");
        }


        return ResponseEntity.ok(result);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {

        BaseApiResult result = new BaseApiResult();
        try {
            userService.createUser(req);
            result.setSuccess(true);
            result.setMessage("Register success!");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

}
