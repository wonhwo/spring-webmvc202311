package com.spring.mvc.chap05.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class PageMaker {
    //한 화면에 보여질 페이지 수
    private static final int PAGE_COUNT=10;
    // 페이지 첫 번호랑 끝번호 (1~10, 11~20)
    private  int begin, end,finalPage;
    private boolean prev, next;
    //현재0 페이지 정보
    private  Page page;
    //페이지 생성에 필요한 데이터를 만드는 알고리즘
    // 총 게시물 수
    private int totalCount;
    public PageMaker(Page page,int totalCount){
        this.page=page;
        this.totalCount=totalCount;

        makePageInfo();
    }
    private void makePageInfo(){
        //1. end값 계산
        /*
        지금 사용자가 7페이지에 있당
        ->1~10구간을 만들어야 함
        지금 사용자가 24페이지에 있다.
        -> 21~30구간

        // 5개씩 쪼개는 경우
        현제 13페이지 11~15 (13/5) * 5
        현재 38페이지 36~40

         */
        //공식 : 올림((현재 사용자가 위치한 페이지 넘버 / 한 화면에 보여줄 페이지 수)
        // * 한 화면에 보여줄 페이지 수)
        this.end = (int)Math.ceil((double) page.getPageNo()/PAGE_COUNT)*PAGE_COUNT;
        //2. begin 구하기
        this.begin = this.end - PAGE_COUNT + 1 ;
        //. 3. prev 활성화 여부
        /*
                1~10구간에는 prev가 안보여도됨
                11~20구간에는 1~10구간으로 이동가능 함으로 prev를 활성화
                41~50구간에도 31~40구간으로 이동가능하므로 활성화
         */
        this.prev = begin>1;
        /*
                # end 값 보정
                총 게시물이 237개이고 한 화면에 게시물을 10개씩 배치하고 있다면
                1~10페이지 구간 : 게시물 총 100개
                근댕 21~30페이지는 끝 페이지가 24임!!!

                - 마지막 구간 보정 공식
                Math.ceil(총 게시물 수 / 한 페이지에 배치할 게시물의 수)
                ex)237 / 10 => 올림 24
         */
        this.finalPage=(int) Math.ceil((double) totalCount / page.getAmount());

        //마지막 페이지 구간에서 end값을 final페이지 값으로 변경
        if(this.finalPage<this.end){
            this.end=this.finalPage;
        }
        // 4. next활성화 여부

        //next활성화 여부 - 마지막 페이지 구간에서만 비활성화
        this.next=this.end<this.finalPage;
    }
}
