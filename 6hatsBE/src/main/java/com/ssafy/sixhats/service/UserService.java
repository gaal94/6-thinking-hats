package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.UserDAO;
import com.ssafy.sixhats.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public UserVO createUser(UserVO userVO){
        return userDAO.save(userVO);
    }
    public UserVO getUser(Long userId) {
        return userDAO.findById(userId).orElse(null);
    }
    @Transactional
    public UserVO updateUser(UserVO newUserVO) {
        UserVO userVO = getUser(newUserVO.getUserId());
        if(userVO != null){
            userVO.setName(newUserVO.getName());
            userVO.setBirth(newUserVO.getBirth());
            userVO.setJob(newUserVO.getJob());
            userVO.setProfileImageUrl(newUserVO.getProfileImageUrl());
            userVO = userDAO.save(userVO);
        }
        return userVO;
    }
    public UserVO loginGeneral(UserVO userVO){
        String email = userVO.getEmail();
        String password = userVO.getPassword();
        return userDAO.findByEmailAndPassword(email, password);
    }
}
