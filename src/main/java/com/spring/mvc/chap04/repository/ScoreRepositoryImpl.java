package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Repository //빈 등록
public class ScoreRepositoryImpl implements ScoreRepository{

    //인메모리 저장공간 해시맵
    //key : 학번, value : 성적정보
    private static final Map<Integer,Score>scoreMap;
    //객체 초기화는 직접하는 것보다 주입받거나 생성자를 통해 처리하는 것이 좋음
    static {
        scoreMap=new HashMap<>();
        Score s1 = new Score("뽀로로", 100, 100, 100, 1, 300, 100.0, Grade.A);
        Score s2 = new Score("둘리", 50, 50, 50, 2, 150, 50, Grade.C);
        Score s3 = new Score("타요", 80, 80, 80, 3, 240, 80, Grade.B);

        scoreMap.put(s1.getStuNum(),s1);
        scoreMap.put(s2.getStuNum(),s2);
        scoreMap.put(s3.getStuNum(),s3);
    }
    @Override
    public List<Score> findAll() {
        //맵에 있는 모든 성적정보를 꺼내서 리스트에 담아라
//        List<Score> temp=new ArrayList<>();
//        for (Integer key : scoreMap.keySet() ){
//            Score score = scoreMap.get(key);
//            temp.add(score);
//        }
        return new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(Comparator.comparing(s->s.getStuNum()))
                .collect(toList());
    }

    @Override
    public boolean save(Score score) {
        if(scoreMap.containsKey(score.getStuNum())) return false;
        scoreMap.put(score.getStuNum(),score);
        return true;
    }

    @Override
    public boolean delete(int stuNum) {
        if(!scoreMap.containsKey(stuNum))
            return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }
}
