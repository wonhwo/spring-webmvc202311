package com.spring.mvc.chap01;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class orderRequsetDTO {
    //크라이언트가 보내는 파라미터의 이름을 필드로 똑같이 구성한다.
    // SETTER와 기본생성자가 반드시 있어야 한다.
    private String  orderNum; //주문 번호
    private  String goodsName; // 상품 이름
    private  int amount; // 주문 수량
    private  int price; // 주문 가격

}
