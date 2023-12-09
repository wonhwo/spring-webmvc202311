package com.spring.mvc.chap05.repsitory;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //전체찾기
    List<Board> findAll(Page page);
    //게시물 찾기
    Board findOne(int boardNo);
    //게시물 등록
    boolean save(Board board);

    // 게시물 삭제
    boolean deleteByNo(int boardNo);
    //조회수 상승
    boolean updateViewCount(int boardNo);
}
