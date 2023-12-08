package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {

    List<Score> findAll(String sort);
    boolean save(Score score);

    //성적 정보 삭제 - 1개 삭제
    boolean delete(int stuNum);

    //성적 정보 개별 조회
    Score findOne(int stuNum);
}
