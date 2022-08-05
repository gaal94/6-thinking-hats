package com.ssafy.sixhats.dao;

import com.ssafy.sixhats.vo.RoomVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDAO extends JpaRepository<RoomVO, Long> {
}
