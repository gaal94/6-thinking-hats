package com.ssafy.sixhats.repository;

import com.ssafy.sixhats.model.TestVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestVO, Long> {
}
