package com.example.usermanagementrestapi.controller;

import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.dto.UserDto;
import com.example.usermanagementrestapi.model.request.CreateUserReq;
import com.example.usermanagementrestapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @ApiOperation(value = "Lấy danh sách ", response = UserDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code=500, message = "")
    })
    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDto> result = userService.getListUser();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Lấy thông tin user theo id", response = UserDto.class)
//    Neu tra ve message khong thi la String.class
    @ApiResponses({
            @ApiResponse(code=404, message = "Không tìm thấy user"),
            @ApiResponse(code=500, message = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto result = userService.getUserById(id);
//        if (result == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no result");
//        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Tạo user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code=500, message = ""),
            @ApiResponse(code=400, message = "Email đã tồn tại trong hệ thống")
    })
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user,@PathVariable int id) {
        UserDto result = userService.updateUserById(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        List<UserDto> result = userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
