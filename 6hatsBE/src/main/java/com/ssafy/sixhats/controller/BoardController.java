package com.ssafy.sixhats.controller;

import com.ssafy.sixhats.dao.BoardDAO;
import com.ssafy.sixhats.domain.Board;
import com.ssafy.sixhats.service.BoardService;
import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    //게시글 입력
    @PostMapping("/board")
    public int postBoard(@RequestBody BoardPostRequestDto requestDto) {
        return boardService.post(requestDto);
    }
    //게시글 수정
    @PatchMapping("/board/{board_id}")
    public int patchBoard(@PathVariable int boardId, @RequestBody BoardPatchRequestDto requestDto) {
        return boardService.patchBoard(boardId, requestDto);
    }

    //개별 조회
    @GetMapping("/board/{board_Id}")
    public BoardResponseDto searchById(@PathVariable int boardId) {
        return boardService.searchById(boardId);
    }

    //전체 조회(목록)
    @GetMapping("/board")
    public List<BoardListResponseDto> searchAllDesc() {
        return boardService.searchAllDesc();
    }

    @DeleteMapping("/board/{board_id}")
    public void deleteBoard(@PathVariable int boardId){
        boardService.deleteBoard(boardId);
    }


}




