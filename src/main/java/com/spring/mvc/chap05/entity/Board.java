package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴으로 객체생성 가능
public class Board {

    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일자시간

    public Board(int boardNo, String title, String content) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.regDateTime = LocalDateTime.now();
    }
    public Board(BoardWriteRequestDTO dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
        this.regDateTime=LocalDateTime.now();
    }

    public Board(ResultSet rs) throws SQLException {
        this.boardNo=rs.getInt("board_no");
        this.title=rs.getString("title");
        this.content=rs.getString("content");
        Timestamp timestamp = rs.getTimestamp("reg_date_time");
        this.regDateTime= timestamp.toLocalDateTime();
        this.viewCount=rs.getInt("view_count");
    }

    public void upViewCount() {
        this.viewCount++;
    }
}
