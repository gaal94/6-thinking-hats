package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.RoomDAO;
import com.ssafy.sixhats.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RoomService {

    @Autowired
    RoomDAO roomDAO;

    public RoomVO createRoom(Long userId) {
        RoomVO roomVO = RoomVO.builder().userId(userId).roomStartTime(new Date()).build();
        return roomDAO.save(roomVO);
    }

    public RoomVO updateRoom(RoomVO roomVO) {

    }


}
