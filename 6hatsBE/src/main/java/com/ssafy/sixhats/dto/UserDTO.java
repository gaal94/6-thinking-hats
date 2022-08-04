package com.ssafy.sixhats.dto;

import com.ssafy.sixhats.vo.type.Gender;
import com.ssafy.sixhats.vo.type.Job;
import com.ssafy.sixhats.vo.type.LoginType;
import com.ssafy.sixhats.vo.type.UserType;

import java.time.LocalDate;

public class UserDTO {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private Job job;
    private LoginType loginType;
    private UserType userType;
    private LocalDate birth;
    private Gender gender;
    private String profileImageUrl;
    private boolean isActive;
}
