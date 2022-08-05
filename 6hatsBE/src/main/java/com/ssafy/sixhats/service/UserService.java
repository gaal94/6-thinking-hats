package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.UserDAO;
import com.ssafy.sixhats.dto.UserCreateRequestDTO;
import com.ssafy.sixhats.dto.UserLoginRequestDTO;
import com.ssafy.sixhats.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public UserVO postUser(UserCreateRequestDTO userCreateRequestDTO){
        return userDAO.save(userCreateRequestDTO.toEntity());
    }
    public UserVO getUser(Long userId) {
        return userDAO.findById(userId).orElse(null);
    }
    public UserVO loginGeneral(UserLoginRequestDTO userLoginRequestDTO){
        String email = userLoginRequestDTO.getEmail();
        String password = userLoginRequestDTO.getPassword();
        return userDAO.findByEmailAndPassword(email, password);
    }
}
