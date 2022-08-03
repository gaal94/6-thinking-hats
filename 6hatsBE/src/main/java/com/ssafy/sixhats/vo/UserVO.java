package com.ssafy.sixhats.vo;

import com.ssafy.sixhats.vo.type.Gender;
import com.ssafy.sixhats.vo.type.Job;
import com.ssafy.sixhats.vo.type.LoginType;
import com.ssafy.sixhats.vo.type.UserType;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@DynamicInsert
@DynamicUpdate
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
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Job job;

    @Column(name = "login_type", length = 10)
    @ColumnDefault("'GENERAL'")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(name = "user_type", length = 5)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private UserType userType;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="profile_image_url", length = 100)
    @ColumnDefault("'NOT'")
    private String profileImageUrl;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private boolean isActive;
}
