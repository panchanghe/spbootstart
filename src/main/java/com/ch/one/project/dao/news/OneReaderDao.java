package com.ch.one.project.dao.news;

import com.ch.one.project.entity.OneReader;
import com.ch.one.project.mapper.OneReaderMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 15:41
 * @Description:
 */
@Mapper
public interface OneReaderDao extends OneReaderMapper {
	// 分页查询阅读
	List<OneReader> pageOneReader();

}
