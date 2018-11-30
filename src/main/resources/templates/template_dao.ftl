package com.ch.one.project.dao.${moduleName};

import java.util.List;
import java.util.Map;
import com.ch.one.project.mapper.${className}Mapper;

/**
 * 功能描述:
 * 作者:${createPerson}
 * 邮箱:${createEmail}
 * 创建时间:${createDate}
 */

public interface ${className}Dao  extends ${className}Mapper{

    //根据条件查询${tableZh}列表
    List<Map<String, String>> list${className}(Map<String, String> map);
	
}
