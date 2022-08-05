package com.ssafy.sixhats.vo;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "room")
@Data
public class RoomVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "user_id")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "room_start_time")
    private Date roomStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "room_start_time")
    private Date roomEndTime;

    @Column(name = "opinion_file_url")
    private String opinionFileUrl;

    @Column(name = "opinion_file_valid", columnDefinition = "true")
    private boolean opinionFileValid;

    @Column(name = "is_active", columnDefinition = "true")
    private boolean isActive;

    @Builder
    public RoomVO(Long userId, Date roomStartTime){
        this.userId = userId;
        this.roomStartTime = roomStartTime;
    }








}
