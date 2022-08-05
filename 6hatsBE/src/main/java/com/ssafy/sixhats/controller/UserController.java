package com.ssafy.sixhats.controller;

import com.ssafy.sixhats.dto.UserGetResponseDTO;
import com.ssafy.sixhats.dto.UserPostRequestDTO;
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
    public ResponseEntity<String> postUser(@RequestBody UserPostRequestDTO userPostRequestDTO){
        userService.postUser(userPostRequestDTO);
        // email 확인 로직과 관련해서 고민중
        return new ResponseEntity("signin success", HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable Long userId, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        // 유저가 다른 유저의 정보를 요청했을 때
        if(jwtService.getUserId(request) != userId){
            throw new UnAuthorizedException();
        }

        UserGetResponseDTO userGetResponseDTO = userService.getUser(userId);
        if(userGetResponseDTO != null){
            resultMap.put("messge", "get user info success");
            resultMap.put("user", userGetResponseDTO);
        } else {
            resultMap.put("messge", "user not found");
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity(resultMap, status);
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
