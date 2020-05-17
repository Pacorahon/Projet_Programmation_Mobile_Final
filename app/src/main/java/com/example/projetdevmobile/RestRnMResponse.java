package com.example.projetdevmobile;

import java.util.List;

public class RestRnMResponse {
    private Integer count;
    private Integer pages;
    private String next;
    private List<Character> results;

    public Integer getCount(){
        return count;
    }
    public Integer getPages(){
        return pages;
    }
    public String getNext(){
        return next;
    }
    public List<Character> getResults(){
        return results;
    }
}
