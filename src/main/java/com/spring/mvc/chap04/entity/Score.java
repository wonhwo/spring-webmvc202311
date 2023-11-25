package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.DTO.ScoreRequstDTO;
import lombok.*;

/***
 * 엔터티 클래스
 * - 데이터베이스에 저장할 데이터를 자바클래스에 매칭
 */
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Score {
    private String name; //학생 이름
    private  int kor,eng,math; //국영수 점수

    private int stuNum; //학번
    private  int total; //총점
    private  double average; //평균
    private Grade grade; //학점

    public Score(ScoreRequstDTO scoreRequstDTO) {
        convertInputData(scoreRequstDTO);
        calculateTotalAndAverage();
        makeGrade();
    }

    private void makeGrade() {
        if(average>=90) this.grade=Grade.A;
        else if(average>=80) this.grade=Grade.B;
        else if(average>=70) this.grade=Grade.C;
        else this.grade=Grade.D;
    }

    private void calculateTotalAndAverage() {
        this.total=kor+math+eng;
        this.average=total/3.0;
    }

    private void convertInputData(ScoreRequstDTO scoreRequstDTO) {
        this.name= scoreRequstDTO.getName();
        this.kor= scoreRequstDTO.getKor();
        this.eng= scoreRequstDTO.getEng();
        this.math= scoreRequstDTO.getMath();
    }
}
