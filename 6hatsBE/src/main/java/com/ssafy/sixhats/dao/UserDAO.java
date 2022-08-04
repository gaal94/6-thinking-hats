package com.ssafy.sixhats.dao;

import com.ssafy.sixhats.vo.UserVO;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserVO, Long> {
    UserVO findByEmailAndPassword(String email, String password);
}
