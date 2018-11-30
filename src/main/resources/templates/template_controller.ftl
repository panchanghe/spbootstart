package com.ch.one.project.controller.${moduleName};

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.one.core.base.BaseController;
import com.ch.one.core.constant.SystemConstant;
import com.ch.one.core.support.QueryResult;
import com.ch.one.core.util.UUIDGenerator;
import com.ch.one.project.entity.${className};
import com.ch.one.project.entity.SfUser;
import com.ch.one.project.service.${moduleName}.${className}Service;

/**
 * 功能描述:
 * 作者:${createPerson} 
 * 邮箱:${createEmail}
 * 创建时间:${createDate}
 */

@RestController
@RequestMapping("/${className?uncap_first}")
public class ${className}Controller extends BaseController {
	private static Logger LOG = LoggerFactory.getLogger(${className}Controller.class);
	
	@Autowired
	private ${className}Service ${className?uncap_first}Service;
	
	
	/**
	 * 根据条件查询${tableZh}列表
	 * @param map  前端传过来的查询条件的map集合
	 * @param page 前端grid的当前页面
	 * @param size 前端grid每页显示的条数
	 * @author ${createPerson}
	 * @date ${createDate}
	 * @return Object
	 */
	@RequestMapping("/get${className}List")
	public Object get${className}List(@RequestParam Map<String, String> map, int page, int size) {
		LOG.info("查询${tableZh}信息开始...");
		
		try {
			SfUser user = (SfUser) getSession().getAttribute(SystemConstant.ONLINE_USER);
			QueryResult<Map<String, String>> qr = ${className?uncap_first}Service.get${className}List(user.getPlatId(), map, page, size);
			LOG.info("查询${tableZh}信息结束.");
			return qr;
		} catch (Exception e) {
			LOG.error("查询${tableZh}信息失败", e);
			LOG.info("查询${tableZh}信息结束.");
			return renderError("查看${tableZh}信息失败");
		}
		
	}
	
	
	/**
	 * 增加一条${tableZh}记录
	 * @param ${className?uncap_first} ***
	 * @return
	 */
	@RequestMapping("/save")
	public Object save${className}(${className} ${className?uncap_first}) {
		LOG.info("增加${tableZh}信息开始...");
		
		SfUser user = (SfUser) getSession().getAttribute(SystemConstant.ONLINE_USER);
		${className?uncap_first}.set${primaryKey?cap_first}(UUIDGenerator.getUUID());		
		${className?uncap_first}.setCreateUser(user.getUserCode());
		${className?uncap_first}.setCreateTime(new Date());
		${className?uncap_first}.setUpdateUser(user.getUserCode());
		${className?uncap_first}.setUpdateTime(new Date());
		${className?uncap_first}.setIsDel("0");
		
		try {
			${className?uncap_first}Service.insert${className}(${className?uncap_first});
		} catch (Exception e) {
			LOG.error("添加${tableZh}信息失败", e);
			LOG.info("添加${tableZh}信息结束.");
			return renderError("添加${tableZh}信息失败");
		}
		
		LOG.info("添加${tableZh}信息结束.");
		return renderSuccess(${className?uncap_first}.get${primaryKey?cap_first}());
	}
	
	
	/**
	 * 根据主键获取一条${tableZh}记录
	 * @param ${primaryKey} 主键
	 * @return
	 */
	@RequestMapping("/getById")
	public Object get${className}ById(String ${primaryKey}) {
		LOG.info(" 根据主键查询${tableZh}信息开始...");
		${className} ${className?uncap_first} = null;
		
		try {
			 ${className?uncap_first} = ${className?uncap_first}Service.getById(${primaryKey});
			LOG.info("根据主键查询${tableZh}信息结束.");
			return renderSuccess(${className?uncap_first});
		} catch (Exception e) {
			LOG.error("根据主键查询${tableZh}信息失败", e);
			LOG.info("根据主键查询${tableZh}信息结束.");
			return renderError("查询${tableZh}信息失败");
		}
		
	}
	
	
	/**
	 * 修改一条${tableZh}记录
	 * @param ${className?uncap_first}
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update${className}(${className} ${className?uncap_first}) {
		LOG.info("修改${tableZh}信息开始...");
		
		SfUser user = (SfUser) getSession().getAttribute(SystemConstant.ONLINE_USER);
		${className?uncap_first}.setUpdateUser(user.getUserId());
		${className?uncap_first}.setUpdateTime(new Date());
		
		try {
			${className?uncap_first}Service.update${className}(${className?uncap_first});
		} catch (Exception e) {
			LOG.error("修改${tableZh}信息失败", e);
			LOG.info("修改${tableZh}信息结束.");
			return renderError(${className?uncap_first}.get${primaryKey?cap_first}());
		}
		
		LOG.info("修改${tableZh}信息结束.");
		return renderSuccess("修改${tableZh}信息成功");
	}
	
	
	/**
	 * 逻辑删除一条${tableZh}记录
	 * @param ${className?uncap_first}
	 * @return
	 */
	@RequestMapping("/delete")
	public Object delete${className}(String id) {
		LOG.info("删除${tableZh}信息开始...");
		
		try {
			${className?uncap_first}Service.delete${className}(id);
			LOG.info("删除${tableZh}信息结束.");
			return renderSuccess("删除${tableZh}信息成功");
		} catch (Exception e) {
			LOG.error("删除${tableZh}信息失败", e);
			LOG.info("删除${tableZh}信息结束.");
			return renderError("删除${tableZh}信息失败");
		}
		
	}
	
}
