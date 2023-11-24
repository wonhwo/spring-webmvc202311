package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {
    ScoreRepository repository = new ScoreRepositoryImpl();

    //단위 테스트 (Unit Test)
    //junit 5
    // 테스트 시나리오 - A를 주면 B가 나온다
    // 단언(Assention) 기법 - ~해야 한다, ~이다. (O)
    //                       ~할 것이다, ~ 일 것같다 (X)
    // GWT패턴 - Given, When, Then 패턴
    @Test
    @DisplayName("저장소에서 findAll 메서드를 호출하면"+
            "리스트가 반활 되고, 해당 리스트에는 성적정보가 3개 들어있어야 한다.")
    void findAllTest(){
        // GWT 패턴
        // given: 테스트르 위해 주어지는 데이터 - parameter

        //when : 테스트 해봐야 할 상황
        List<Score> scoreList = repository.findAll();
        scoreList.forEach(System.out::println);
        //then : 테스트 결과 단언(결과 확인)
        assertEquals(3,scoreList.size());
        assertNotNull(scoreList);
        assertEquals("뽀로로",scoreList.get(0).getName());
    }
    @Test
    @DisplayName("저장소에서 findOne을 호출하여 학번이 2인 학생을 조회하면 그 학생의 국어점수가 50점이고 이름은 둘리이어야 한다.")
    void findOneTest(){
        //given
        int stuNum=2;
        //when
        Score score = repository.findOne(stuNum);
        //then
        assertEquals(50,score.getKor());
        assertEquals("둘리",score.getName());
    }
    @Test
    @DisplayName("학번이 -99번인 학생을 조회하면 Null이 나와야 한다.")
    void failTest(){
        //given
        int stuNum=99;
        //when
        Score score = repository.findOne(stuNum);
        //then
        assertNull(score);
    }
    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에" +
            "리스트를 전체조회보면 성적의 개수가 2개일 것이고" +
            "다시 2번학생을 조회했을 때 null이 반환되어야 한다.")
    void deleteTest() {
        //given
        int stuNum=2;
        //when

        boolean delete = repository.delete(stuNum);
        List<Score> scoreList=repository.findAll();
        Score score = repository.findOne(stuNum);
        //then
        assertTrue(delete);
        assertEquals(2,scoreList.size());
        assertNull(score);

    }

    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면" +
            "목록의 개수가 4개여야 한다.")
    void saveTest() {
        //given
        Score score = new Score("토마스",70,70,70,4,280,70, Grade.B);
        //when
        boolean save = repository.save(score);
        List<Score> scoreList = repository.findAll();
        //then
        if(save)
            assertEquals(4,scoreList.size());
    }

}