package com.ssafy.sixhats.controller;

import com.ssafy.sixhats.dto.UserCreateRequestDTO;
import com.ssafy.sixhats.dto.UserLoginRequestDTO;
import com.ssafy.sixhats.exception.UnAuthorizedException;
import com.ssafy.sixhats.service.JwtService;
import com.ssafy.sixhats.service.UserService;
import com.ssafy.sixhats.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    // User Create (POST)
    @PostMapping("")
    public ResponseEntity<String> postUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO){
        userService.postUser(userCreateRequestDTO);
        return new ResponseEntity("signin success", HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity loginGeneral(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_FOUND;

        try {
            UserVO userVO = userService.loginGeneral(userLoginRequestDTO);
            if(userVO != null){
                String token = jwtService.createToken(userVO);
                resultMap.put("access-token", token);
                resultMap.put("message", "Login Success");
                status = HttpStatus.OK;
            } else {
                resultMap.put("message", "User Not Found");
            }
        } catch (Exception e) {
            resultMap.put("message", "Sever Error");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(resultMap, status);
    }

}
