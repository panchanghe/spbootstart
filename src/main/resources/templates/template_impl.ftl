package com.ch.one.project.service.${moduleName}.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.one.core.base.BaseService;
import com.ch.one.core.support.QueryResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ch.one.project.entity.${className};
import com.ch.one.project.dao.${moduleName}.${className}Dao;
import com.ch.one.project.mapper.${className}Mapper;
import com.ch.one.project.service.${moduleName}.${className}Service;

/**
 * 功能描述:
 * 作者:${createPerson}
 * 邮箱:${createEmail}
 * 创建时间:${createDate}
 */
 
@Service
public class ${className}ServiceImpl extends BaseService implements ${className}Service {
	private static Logger LOG = LoggerFactory.getLogger(${className}ServiceImpl.class);
	@Autowired
	private ${className}Dao ${className?uncap_first}Dao;

	@Autowired
	private ${className}Mapper ${className?uncap_first}Mapper;
	
	/**
	 *根据条件获取${tableZh}列表
	 */
	@Override
	public QueryResult<Map<String, String>> get${className}List(String platId, Map<String, String> map, int page, int size) {
		LOG.info("service查询${tableZh}信息开始...");
		Page<Map<String, String>> result = PageHelper.startPage(page, size);
		${className?uncap_first}Dao.list${className}(map);
		QueryResult<Map<String, String>> qr = new QueryResult<>();
		qr.setResultList(list);
		qr.setTotalCount(result.getTotal());
		LOG.info("service查询${tableZh}信息结束.");
		return qr;
	}
	
	
	/**
	 * 增加一条${tableZh}记录
	 */
	@Override
	public void insert${className}(${className} ${className?uncap_first}) {
		LOG.info("service增加${tableZh}信息开始...");
		${className?uncap_first}Mapper.insert(${className?uncap_first});
		LOG.info("service添加${tableZh}信息结束.");
	}

	/**
	 * 根据主键获取一条${tableZh}记录
	 */
	@Override
	public ${className} getById(String ${primaryKey}) {
		LOG.info("service层 --- getById()开始...");
		${className} ${className?uncap_first} = ${className?uncap_first}Mapper.selectByPrimaryKey(${primaryKey});
		LOG.info("service层 --- getById()结束.");
		return ${className?uncap_first};
	}

	/**
	 * 修改一条${tableZh}记录
	 */
	@Override
	public int update${className}(${className} ${className?uncap_first}) {
		LOG.info("service层 --- update${className}()开始...");
		int i = ${className?uncap_first}Mapper.updateByPrimaryKeySelective(${className?uncap_first});
		LOG.info("service层 --- update${className}()结束.");
		return i;
	}

	/**
	 * 逻辑删除一条${tableZh}记录
	 */
	@Override
	public int delete${className}(String id) {
		LOG.info("service层 --- delete${className}()开始...");
		
		LOG.info("service层 --- delete${className}()结束.");
		return 0;
	}

}
