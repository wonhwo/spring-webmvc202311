package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repsitory.BoardMapper;
import com.spring.mvc.chap05.repsitory.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardMapper boardRepository;

    //public BoardService(@Qualifier("dbRepo1") BoardRepository boardRepository) {
    //    this.boardRepository = boardRepository;
    //}

    // 목록 조회 중간처리
    public List<BoardListResponseDTO> getList(Page page) {
        return boardRepository.findAll(page)
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList())
                ;
    }

    public void register(BoardWriteRequestDTO dto) {
        //dto를 엔터티로 변환
        Board board = new Board(dto);
        boardRepository.save(board);
    }

    public void delete(int bno) {
        boardRepository.deleteByNo(bno);
    }

    public BoardDetailResponseDTO getDetail(int bno) {
        Board board = boardRepository.findOne(bno);

        // 조회수 상승처리
//        board.upViewCount();
        boardRepository.updateViewCount(bno);

        return new BoardDetailResponseDTO(board);
    }

    public int getCount() {
        return boardRepository.count();
    }
}
