package com.ssafy.sixhats.dao;

import com.ssafy.sixhats.vo.TestVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestVO, Long> {
}
