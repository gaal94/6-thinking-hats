package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.UserDAO;
import com.ssafy.sixhats.vo.UserVO;
import com.ssafy.sixhats.vo.form.UserLoginForm;
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

    public UserVO loginGeneral(UserLoginForm userLoginForm){
        String email = userLoginForm.getEmail();
        String password = userLoginForm.getPassword();
        return userDAO.findByEmailAndPassword(email, password);
    }
}
