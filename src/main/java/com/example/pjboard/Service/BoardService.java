package com.example.pjboard.Service;

import com.example.pjboard.model.Board;
import com.example.pjboard.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }


    // baord 조회
    public List< Board> findAllBoards(){
        return boardRepository.findAll();
    }

    // 특정 게시글 상세보기
    public Board view(Long id) {
        return boardRepository.findById(id)
                .orElseThrow() ;
    }
//
//    //삭제
//    public void deleteBoard(Long id) {
//        boardRepository.deleteById(id);
//    }


}

