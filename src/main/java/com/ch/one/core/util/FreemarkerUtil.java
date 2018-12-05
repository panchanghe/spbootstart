package com.ch.one.core.util;

import cn.hutool.core.date.DateUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreemarkerUtil {

	// 生成代码后台模板路径
	private static final String TEMPLATE_PATH = "src/main/resources/templates";

	// 生成代码后台exMapper层路径
	private static final String XML_PATH = "src/main/resources/sql/";

	// 生成代码后台dao层路径
	private static final String DAO_PATH = "src/main/java/com/ch/one/project/dao/";

	// 生成代码后台service层路径
	private static final String SERVICE_PATH = "src/main/java/com/ch/one/project/service/";

	// 生成代码后台controller层路径
	private static final String CONTROLLER_PATH = "src/main/java/com/ch/one/project/controller/";


	// 配置类
	private static Configuration configuration;

	/**
	 * 初始化FreeMarker配置
	 * 
	 * @throws IOException
	 */
	public static void init() throws IOException {
		// 创建一个Configuration实例
		configuration = new Configuration();
		// 设置FreeMarker的模版文件夹位置
		configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
		// 设置编码为UTF-8
		configuration.setDefaultEncoding("UTF-8");
	}

	/**
	 * 根据模板生成代码
	 * 
	 * @param moduleName
	 *            第一个参数是模块名
	 * @param fullName
	 *            第二个参数是全类名
	 * @param createPerson
	 *            第三个参数是创建者姓名
	 * @param createEmail
	 *            第四个参数是个人邮箱
	 * @throws Exception
	 */
	public static void process(String moduleName, String fullName, String tableZh, String createPerson, String createEmail)
			throws Exception {

		// 获取当前类的Class
		Class<?> clazz = Class.forName(fullName);

		Map<String, Object> map = new HashMap<String, Object>();
		
		// 获取所在模块
		map.put("moduleName", moduleName);
		
        // 获取表的中文名称
		map.put("tableZh", tableZh);
		
		// 获取类名
		String className = clazz.getSimpleName();
		map.put("className", className);

		// 获取当前日期
		String createDate = DateUtil.now();
		map.put("createDate", createDate);

		// 获得创建人的姓名
		map.put("createPerson", createPerson);

		// 获得创建人的邮箱
		map.put("createEmail", createEmail);

		// 对象属性集合
		List<String> propertyList = new ArrayList<String>();

		// 获取父类Class
		Class<?> superClazz = clazz.getSuperclass();

		// 获取父类所有私有字段
		Field[] superFields = superClazz.getDeclaredFields();

		for (Field superField : superFields) {

			// 消除serialVersionUID字段
			if ("serialVersionUID".equals(superField.getName())) {
				continue;
			}

			propertyList.add(superField.getName());

		}

		// 获取所有私有字段
		Field[] fields = clazz.getDeclaredFields();

		// 遍历所有字段名
		for (Field field : fields) {

			// 消除serialVersionUID字段
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}

			propertyList.add(field.getName());
		}

		map.put("propertyList", propertyList);
		
		//获取类的主键
		String primaryKey = propertyList.get(0);
		map.put("primaryKey", primaryKey);
		

		// 创建exMapper层模版对象
		Template templateXml = configuration.getTemplate("template_xml.ftl");
		// 自动生成exMapper层代码
		templateXml.process(map, new FileWriter(XML_PATH + className + "ExMapper.xml"));

		// 创建dao层模版对象
		Template templateDao = configuration.getTemplate("template_dao.ftl");
		// 自动生成dao层代码
		templateDao.process(map, new FileWriter(DAO_PATH + moduleName + "/" + className + "Dao.java"));

		// 创建service层模版对象
		Template templateService = configuration.getTemplate("template_service.ftl");
		// 自动生成service层代码
		templateService.process(map, new FileWriter(SERVICE_PATH + moduleName + "/" + className + "Service.java"));

		// 创建impl层模版对象
		Template templateImpl = configuration.getTemplate("template_impl.ftl");
		// 自动生成impl层代码
		templateImpl.process(map, new FileWriter(SERVICE_PATH + moduleName + "/impl/" + className + "ServiceImpl.java"));

		// 创建controller层模版对象
		Template templateController = configuration.getTemplate("template_controller.ftl");
		// 自动生成controller层代码
		templateController.process(map, new FileWriter(CONTROLLER_PATH + moduleName + "/" + className + "Controller.java"));


	}

	/**
	 * 程序入口
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// 配置freemarker的相关参数
		FreemarkerUtil.init();

		/* 根据pojo类名生成代码 
		 * 第一个参数是模块名     system:系统设置
		 * 第二个参数是全类名 
		 * 第三个参数是表的中文名
		 * 第四个参数是创建者姓名
		 * 第五个参数是创建者邮箱
		*/
		FreemarkerUtil.process("archive", "com.ch.one.project.entity.Person", "证明申请", "潘长河", "panch@bhaps.cn");

	}

}
