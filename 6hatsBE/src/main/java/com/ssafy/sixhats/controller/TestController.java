package com.ssafy.sixhats.controller;

import com.ssafy.sixhats.vo.TestVO;
import com.ssafy.sixhats.dao.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @PostMapping("")
    public TestVO postTest(TestVO testVO) {
        System.out.println(testVO);
        return testRepository.save(testVO);
    }

    @GetMapping("")
    public List<TestVO> getTestAll() {
        return testRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<TestVO> getTest(@PathVariable Long id) {
        // null값이 올 수 있는 값을 감싸서 NPE 발생 방지
        Optional<TestVO> testVO = testRepository.findById(id);
        return testVO;
    }

    @PutMapping("{id}")
    public TestVO putTest(@PathVariable Long id, TestVO tmp) {
        TestVO testVO = testRepository.findById(id).get();
        testVO.setA(tmp.getA());
        testVO.setB(tmp.getB());
        testVO.setC(tmp.getC());
        testRepository.save(testVO);
        return testRepository.findById(id).get();
    }

    @DeleteMapping("{id}")
    public String deleteTest(@PathVariable Long id) {
        testRepository.deleteById(id);
        return "Delete Success";
    }
}
