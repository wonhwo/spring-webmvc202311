package com.spring.mvc.chap05.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@EqualsAndHashCode@ToString
public class Search extends Page{
    private String type, keyword;

    public Search() {
        this.type="";
        this.keyword="";
    }
}
