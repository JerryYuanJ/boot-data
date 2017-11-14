package com.hand.springboot.bean;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Joker on 2017/9/4.
 */
@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery",
            query = "select p from Person p where p.name=?1 and p.address=?2")
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Person(){}

    public Person(Long id, String name, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
