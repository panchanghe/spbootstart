package com.ch.one.project.service.${moduleName};

import java.util.Map;

import com.ch.one.core.support.QueryResult;
import com.ch.one.project.entity.${className};

/**
 * 功能描述:
 * 作者:${createPerson}
 * 邮箱:${createEmail}
 * 创建时间:${createDate}
 */

public interface ${className}Service {

    //根据条件查询${tableZh}列表
	QueryResult<Map<String, String>> get${className}List(String platId, Map<String, String> map, int page, int size);

    //增加一条${tableZh}记录
    void insert${className}(${className} ${className?uncap_first});
	
	//根据主键获取一条${tableZh}记录
	${className} getById(String ${primaryKey});
	
	//修改一条${tableZh}记录
    int update${className}(${className} ${className?uncap_first});
	
	//逻辑删除一条${tableZh}记录
	int delete${className}(String id);
	
}
