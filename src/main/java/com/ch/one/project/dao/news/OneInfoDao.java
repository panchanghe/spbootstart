package com.ch.one.project.dao.news;

import com.ch.one.project.entity.OneInfo;
import com.ch.one.project.mapper.OneInfoMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/26 18:39
 * @Description:
 */
@Mapper
public interface OneInfoDao extends OneInfoMapper {

	/*查询 一个 信息*/
	List<OneInfo> getOneInfo();
}
