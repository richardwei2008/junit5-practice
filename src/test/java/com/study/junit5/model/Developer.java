package com.study.junit5.model;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 上午8:58 18/4/2
 * @Modified: by 
 */
public class Developer {


    private String firstName;
    private String lastName;

    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }
}
