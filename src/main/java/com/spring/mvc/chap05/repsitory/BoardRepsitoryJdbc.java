package com.spring.mvc.chap05.repsitory;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("dbRepo1")
@RequiredArgsConstructor
public class BoardRepsitoryJdbc implements BoardRepository{
    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql="select * from tbl_board";
        return template.query(sql,(rs,rn)->new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String sql="SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql,(rs,rn)->new Board(rs),boardNo);
    }

    @Override
    public boolean save(Board board) {
        String sql="INSERT INTO tbl_board "+ "(title,content) " +
                "VALUES (?,?)";

        return template.update(sql,board.getTitle(),board.getContent())==1;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        String sql="DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql,boardNo)==1;
    }
}
