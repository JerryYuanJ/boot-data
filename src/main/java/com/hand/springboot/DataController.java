package com.hand.springboot;

import com.hand.springboot.bean.Person;
import com.hand.springboot.dao.PersonDao;
import com.hand.springboot.dao.PersonDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joker on 2017/9/4.
 */
@RestController
public class DataController {
    @Autowired
    PersonDao personDao;

    @Autowired
    PersonDao2 personDao2;

    @GetMapping("/person")
    public Page getPageByParam(Person person, int page, int size) {
        Sort sortable = new Sort(Sort.Direction.DESC, "id", "name");
        Pageable pageable = new PageRequest(page, size, sortable);
        Page all = personDao2.findAll(new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<>();

                Expression name = root.get("name");
                Expression address = root.get("address");
                Predicate nameEqual = null;
                if (null != person && !StringUtils.isEmpty(person.getName())) {
                    nameEqual = cb.equal(name, person.getName());
                    list.add(nameEqual);
                }

                Predicate addressLike = null;
                if (null != person && !StringUtils.isEmpty(person.getAddress())) {
                    addressLike = cb.like(address, "%"+person.getAddress()+"%");
                    list.add(addressLike);
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));

            }

        }, pageable);
        return all;
    }

    @GetMapping("/person/all")
    public Page<Person> getPage(int page, int size) {
        Sort sortable = new Sort(Sort.Direction.DESC, "id", "name");
        Pageable pageable = new PageRequest(page, size, sortable);
        Page<Person> all = personDao.findAll(pageable);
        return all;
    }

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        return personDao.save(new Person(null, name, address, age));
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        return personDao.findByAddress(address);
    }

    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        return personDao.findByAddressAndName(address, name);
    }

    @RequestMapping("/q3")
    public Person q3(String address, String name) {
        return personDao.withNameAndAddressQuery(name, address);
    }

    @RequestMapping("/q4")
    public Person q4(String address, String name) {
        return personDao.withNameAndAddressNamedQuery(name, address);
    }

    @RequestMapping("/sort")
    public List<Person> sort() {
        return personDao.findAll(new Sort(Sort.Direction.ASC, "age"));
    }

    @RequestMapping("/page")
    public Page<Person> page(String address, String name) {
        return personDao.findAll(new PageRequest(1, 2));
    }
}
