package com.ssafy.sixhats.service;

import com.ssafy.sixhats.dao.BoardDAO;
import com.ssafy.sixhats.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Transactional
    public Long create(BoardDAO boardDAO) {
        return boardDAO.save(BoardVO.toEntity()).getBoardId();
    }

    @Transactional
    public int update(int boardId, BoardDAO boardDAO) {
        BoardVO boardVO = boardDAO.findById(boardId)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        boardVO.update(boardDAO.getTitle(),
                boardDAO.getBoard_contents());

        return boardId;
    }

    //개별 게시글
    @Transactional(readOnly = true)
    public BoardVO searchById(int boardId) {
        BoardVO boardVO = boardDAO.findById(boardId).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new BoardResponse(entity);
    }

    //전체 게시글
    @Transactional(readOnly = true)
    public List<BoardListResponse> searchAllDesc() {
        return boardDAO.findAllDesc().stream()
                .map(BoardListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(int boardId){
        BoardVO boardVO = boardDAO.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        boardDAO.delete(boardVO);
    }


}
