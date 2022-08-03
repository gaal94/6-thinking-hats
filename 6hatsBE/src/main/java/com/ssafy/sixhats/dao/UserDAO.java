package com.ssafy.sixhats.dao;

import com.ssafy.sixhats.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserVO, Long> {
    UserVO findByEmailAndPassword(String email, String password);

}
