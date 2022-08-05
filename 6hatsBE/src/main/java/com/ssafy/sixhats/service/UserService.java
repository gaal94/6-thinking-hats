package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.UserDAO;
import com.ssafy.sixhats.dto.UserGetResponseDTO;
import com.ssafy.sixhats.dto.UserPostRequestDTO;
import com.ssafy.sixhats.dto.UserLoginRequestDTO;
import com.ssafy.sixhats.dto.UserPutRequestDTO;
import com.ssafy.sixhats.vo.UserVO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public UserVO postUser(UserPostRequestDTO userPostRequestDTO) {
        return userDAO.save(userPostRequestDTO.toEntity());
    }
    public UserGetResponseDTO getUser(Long userId) {
        UserVO userVO = userDAO.findById(userId).orElse(null);
        if(userVO != null){
            UserGetResponseDTO userGetResponseDTO = new UserGetResponseDTO().builder()
                    .email(userVO.getEmail())
                    .name(userVO.getName())
                    .job(userVO.getJob())
                    .birth(userVO.getBirth())
                    .gender(userVO.getGender())
                    .build();
            return userGetResponseDTO;
        } else {
            throw new NullPointerException("User Not Found");
        }
    }
    @Transactional
    public UserGetResponseDTO putUser(Long userId, UserPutRequestDTO userPutRequestDTO) {
        UserVO userVO = userDAO.findById(userId).orElse(null);
        if(userVO != null){
            // update를 바로 실행
            userVO.update(userPutRequestDTO);
            UserGetResponseDTO userGetResponseDTO = new UserGetResponseDTO().builder()
                    .email(userVO.getEmail())
                    .name(userVO.getName())
                    .job(userVO.getJob())
                    .birth(userVO.getBirth())
                    .gender(userVO.getGender())
                    .build();
            return userGetResponseDTO;
        } else {
            throw new NullPointerException("User Not Found");
        }
    }
    public UserVO loginGeneral(UserLoginRequestDTO userLoginRequestDTO){
        String email = userLoginRequestDTO.getEmail();
        String password = userLoginRequestDTO.getPassword();
        return userDAO.findByEmailAndPassword(email, password);
    }

}
