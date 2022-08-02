package com.ssafy.sixhats.vo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class UserVO {
    // 원활한 테스트를 위해서 우선 userid,email,password,name으로만 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //개별 엔티티별로 키값 증가
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    String email;

    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false, length = 100)
    String name;

    /*
    String job;

    @Column(name = "login_type")
    String loginType;

    @Column(name = "user_type")
    String userType;

    private Date birth;

    @Column(length = 1)
    char gender;

    @Column(name = "is_active")
    boolean isActive;
    */
}
