package com.hand.springboot.dao;

import com.hand.springboot.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

/**
 * 封装复杂查询的一个类
 */
public interface PersonDao2 extends JpaSpecificationExecutor<Person>,JpaRepository<Person,Long>  {
}
