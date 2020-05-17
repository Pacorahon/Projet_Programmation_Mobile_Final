package com.example.projetdevmobile;

import java.util.List;

public class RestRnMResponse {
    private Integer count;
    private List<Character> results;

    public Integer getCount(){
        return count;
    }
    public List<Character> getResults(){
        return results;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
