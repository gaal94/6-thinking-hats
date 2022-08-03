package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.UserDAO;
import com.ssafy.sixhats.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public UserVO createUser(UserVO userVO){
        return userDAO.save(userVO);
    }

}
