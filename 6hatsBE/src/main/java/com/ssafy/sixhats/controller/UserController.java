package com.ssafy.sixhats.controller;

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

    @PostMapping("")
    public ResponseEntity<String> createUser(UserVO userVO){
        userService.createUser(userVO);
        return new ResponseEntity("signin success", HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity loginGeneral(UserVO userVO){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_FOUND;

        try {
            userVO = userService.loginGeneral(userVO);
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

    /*
    SocialLogin (kakao, google)
    OAUTH를 이용한 인증 필요
     */
    @PostMapping("login/kakao")
    public ResponseEntity loginKakao(){
        return new ResponseEntity("kakao login", HttpStatus.OK);
    }

    @PostMapping("login/google")
    public ResponseEntity loginGoogle(){
        return new ResponseEntity("google login", HttpStatus.OK);
    }
    
    /*
    Logout
    Social Login 과 General Login을 구분하는 로직 필요
    향후에 클라이언트에서 JWT로 삭제하기로 하면 Method 자체를 삭제 가능
     */
    @PostMapping("logout")
    public ResponseEntity logout(){
        return new ResponseEntity("logout success", HttpStatus.OK);
    }

    /*
    JWT를 받아서 유효성 확인
    userId와 같은지 확인
    같다면 service로 넘기기
     */
    @PutMapping("{userId}")
    public ResponseEntity updateUser(@PathVariable int userId, UserVO userVO){
        return new ResponseEntity("delete user success", HttpStatus.NO_CONTENT);
    }

    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable Long userId, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        UserVO userVO = userService.getUser(userId);
        userVO.setPassword(null);
        if(userVO != null){
            resultMap.put("user", userVO);
            resultMap.put("messge", "");
        } else {
            resultMap.put("messge", "");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity(resultMap, status);
    }

    @GetMapping("{userId}/rooms")
    public ResponseEntity getUserRooms(@PathVariable int userId){
        return new ResponseEntity("get user rooms", HttpStatus.NO_CONTENT);
    }

    /*
    Password 관련 Method
    Password는 따로 관리
    */
    @PostMapping("passoword")
    public ResponseEntity renewPassword(){
        // 랜덤으로 String 생성하고 email 보내줘야 함
        return new ResponseEntity("renew password", HttpStatus.NO_CONTENT);
    }

    @PatchMapping("password")
    public ResponseEntity updatePassword(){
        return new ResponseEntity("update password", HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId){
        return new ResponseEntity("delete user success", HttpStatus.NO_CONTENT);
    }
}
