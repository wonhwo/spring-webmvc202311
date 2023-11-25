package com.spring.mvc.chap04.DTO;

import lombok.*;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class ScoreRequstDTO {
    private  String name;
    private  int kor,eng,math;
}
