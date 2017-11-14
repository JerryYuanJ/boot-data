package com.hand.springboot.dao;

import com.hand.springboot.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Joker on 2017/9/4.
 */
public interface PersonDao extends JpaRepository<Person, Long> {

    List<Person> findByAddress(String address);

    Person  findByAddressAndName(String address, String name);

    @Query("select p from Person p where p.name=:name and p.address=:address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    Person withNameAndAddressNamedQuery(String name, String address);
}
