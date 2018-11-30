package com.ch.one.core.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/23 13:40
 * @Description: mybatis批量更新
 */
public class BatchUpdatePlugin extends PluginAdapter {

	public BatchUpdatePlugin(){
		super();
	}
	
	public boolean validate(List<String> paramList) {
		return true;
	}

	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
								   IntrospectedTable introspectedTable) {
		
		if(!introspectedTable.getNonPrimaryKeyColumns().isEmpty()){
			generateBatchUpdate(interfaze, introspectedTable);
		}
	
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名  
		
		List<IntrospectedColumn> columns = new ArrayList<IntrospectedColumn>();
		
		List<IntrospectedColumn> primeKeys = new ArrayList<IntrospectedColumn>();
		
		XmlElement parentElement = document.getRootElement();
		
		columns = introspectedTable.getNonPrimaryKeyColumns();
		primeKeys = introspectedTable.getPrimaryKeyColumns();
		
		if(!columns.isEmpty()){
			XmlElement batchUpdateElement = new XmlElement("update");
			batchUpdateElement.addAttribute(new Attribute("id", "batchUpdate"));
			batchUpdateElement.addAttribute(new Attribute("parameterType", "list"));
			
			StringBuilder sql = new StringBuilder("");
			sql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\";\" > \n");
			OutputUtilities.xmlIndent(sql, 3);
			sql.append("update " + tableName+" \n");
			OutputUtilities.xmlIndent(sql, 3);
			sql.append("<set>\n");
			
			StringBuilder sets = new StringBuilder("");
			StringBuilder keys = new StringBuilder("");
			
			Iterator<IntrospectedColumn> iterator = columns.iterator();
			while(iterator.hasNext()){
				IntrospectedColumn column = iterator.next();
				OutputUtilities.xmlIndent(sets, 4);
				sets.append("<if test=\"item." + column.getJavaProperty() + " != null\"> \n");
				OutputUtilities.xmlIndent(sets, 5);
				sets.append(column.getActualColumnName() + " = #{item."+column.getJavaProperty()+", "
						+ "jdbcType = "+column.getJdbcTypeName()+"},\n");
				OutputUtilities.xmlIndent(sets, 4);
				sets.append("</if> \n");
			}
			OutputUtilities.xmlIndent(sets, 3);
			sets.append("</set>"+"\n");
			
			Iterator<IntrospectedColumn> key = primeKeys.iterator();
			OutputUtilities.xmlIndent(keys, 3);
			keys.append("<where>"+"\n");
			while(key.hasNext()){
				IntrospectedColumn keycol = key.next();
				OutputUtilities.xmlIndent(keys, 4);
				keys.append("<if test=\"item." + keycol.getJavaProperty() + " != null\">\n");
				OutputUtilities.xmlIndent(keys, 5);
				keys.append(" and " +keycol.getActualColumnName() + " = #{item."+keycol.getJavaProperty()+","
						+ "jdbcType = "+keycol.getJdbcTypeName()+"}\n");
				OutputUtilities.xmlIndent(keys, 4);
				keys.append("</if>\n");
			}
			OutputUtilities.xmlIndent(keys, 3);
			keys.append("</where>"+"\n");
			sql.append(sets.toString());
			sql.append(keys.toString());
			OutputUtilities.xmlIndent(sql, 2);
			sql.append("</foreach>");
				
			
			batchUpdateElement.addElement(
					new TextElement(
							sql.toString()
					));
			
			parentElement.addElement(batchUpdateElement);
			
		}

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	/**
	 * 自定义批量插入方法
	 * @param interfaze
	 * @param introspectedTable
	 */
	private void generateBatchUpdate(Interface interfaze, IntrospectedTable introspectedTable) {
		
		Method m = new Method("batchUpdate");
		
		m.setVisibility(JavaVisibility.PUBLIC);
		
		m.setReturnType(FullyQualifiedJavaType.getIntInstance());
		
		String type = "";
		if(!introspectedTable.getRules().generatePrimaryKeyClass()){
			type = introspectedTable.getBaseRecordType();
		}
		else{
			type = introspectedTable.getPrimaryKeyType();
		}
				
		m.addParameter(new Parameter(new FullyQualifiedJavaType("List<"+type+">"), "list"));

		context.getCommentGenerator().addGeneralMethodComment(m,
				introspectedTable);
		
		interfaze.addImportedType(FullyQualifiedJavaType.getNewListInstance());
		interfaze.addImportedType(new FullyQualifiedJavaType(type));
		interfaze.addMethod(m);
	}

	
	
}
