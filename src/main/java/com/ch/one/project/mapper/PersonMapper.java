package com.ch.one.project.mapper;

import com.ch.one.project.entity.Person;
import com.ch.one.project.entity.PersonExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    long countByExample(PersonExample example);

    int deleteByExample(PersonExample example);

    int deleteByPrimaryKey(String name);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(PersonExample example);

    Person selectByPrimaryKey(String name);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    int batchInsert(List<Person> list);

    int batchUpdate(List<Person> list);
}