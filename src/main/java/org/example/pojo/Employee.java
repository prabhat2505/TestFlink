package org.example.pojo;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","age"})
public  class Employee {
    public int id;
    public String name;
    public String age;
}
