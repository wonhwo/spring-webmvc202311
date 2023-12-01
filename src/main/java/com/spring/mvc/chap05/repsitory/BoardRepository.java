package com.spring.mvc.chap05.repsitory;
import com.spring.mvc.chap05.entity.Board;
import java.util.List;
public interface BoardRepository {
    //전체찾기
    List<Board> findAll();
    //게시물 찾기
    Board findOne(int boardNo);
    //게시물 등록
    boolean save(Board board);

    // 게시물 삭제
    boolean deleteByNo(int boardNo);


}
