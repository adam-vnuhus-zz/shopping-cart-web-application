package com.example.usermanagementrestapi.controller.frontend.client;

import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.request.view_model.ProductViewModel;
import com.example.usermanagementrestapi.model.request.view_model.UserViewModel;
import com.example.usermanagementrestapi.security.CustomUserDetails;
import com.example.usermanagementrestapi.security.JwtUserDetailsService;
import com.example.usermanagementrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/detail")
    public String getUserDetail(@Valid @ModelAttribute("productname") ProductViewModel productName,
                                Model model,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                final Principal principal) {


        this.checkCookie(response,request,principal);

        UserViewModel userViewModel = new UserViewModel();

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomUserDetails user = (CustomUserDetails) jwtUserDetailsService.loadUserByUsername(username);

        userViewModel.setAddress(user.getUser().getAddress());

        userViewModel.setEmail(user.getUser().getEmail());
        userViewModel.setName(user.getUser().getFullName());
        userViewModel.setPhoneNumber(user.getUser().getPhone());

        model.addAttribute("userViewModel",userViewModel);

        return "client/user-detail";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            User userEntity = userService.findByUseremail(username);

            userEntity.setAddress(user.getAddress());
//            userEntity.setAvatar(user.getAvatar());
            userEntity.setEmail(user.getEmail());
            userEntity.setFullName(user.getFullName());
            userEntity.setPhone(user.getPhone());

            userService.updateUser(userEntity);

            return "redirect:/user/detail?updateSuccess";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/detail?updateFail";
    }

    /*@GetMapping("/change-password")
    public String changePassWord(@Valid @ModelAttribute("productname") ProductViewModel productName,
                                 Model model) {

        ChangePasswordVM changePasswordVM = new ChangePasswordVM();
        model.addAttribute("changePassword", changePasswordVM);
        return "client/change-password";
    }*/

    /*@PostMapping("change-password")
    public String changePassWordPost(@Valid @ModelAttribute("changePassword") ChangePasswordVM password) {

        try {
            if(password.getCurrentPassword() != null && password.getNewPassword() != null && password.getConfirmPassword() != null) {
                String  username = SecurityContextHolder.getContext().getAuthentication().getName();

                User userEntity = userService.findByUseremail(username);

                if(passwordEncoder.matches(password.getCurrentPassword(),userEntity.getPassword()) == true) {
                    if(password.getNewPassword().equals(password.getConfirmPassword())) {
                        userEntity.setPassword(passwordEncoder.encode(password.getNewPassword()));
                        userService.updateUser(userEntity);
                        return "redirect:/user/change-password?success";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/change-password?fail";

    }*/
}
