package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.UserDAO;
import com.ssafy.sixhats.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public UserVO createUser(UserVO userVO){
        return userDAO.save(userVO);
    }
    public UserVO loginGeneral(UserVO userVO){
        String email = userVO.getEmail();
        String password = userVO.getPassword();
        return userDAO.findByEmailAndPassword(email, password);
    }
    public UserVO getUser(Long userId) {
        return userDAO.findById(userId).orElse(null);
    }
}
