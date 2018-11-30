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
 * @Description: mybatis批量插入
 */
public class BatchInsertPlugin extends PluginAdapter {
	
	public BatchInsertPlugin() {
		super();
	}

	public boolean validate(List<String> paramList) {
		return true;
	}
	

	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
								   IntrospectedTable introspectedTable) {
		generateBatchInsert(interfaze, introspectedTable);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名  
		
		List<IntrospectedColumn> columns = new ArrayList<IntrospectedColumn>();
		
		XmlElement parentElement = document.getRootElement();

		XmlElement batchInsertElement = new XmlElement("insert");
		batchInsertElement.addAttribute(new Attribute("id", "batchInsert"));
		batchInsertElement.addAttribute(new Attribute("parameterType", "list"));
		
		StringBuilder sql = new StringBuilder("insert into "+ tableName +"\n");
		OutputUtilities.xmlIndent(sql, 3);
		sql.append("(");
		StringBuilder cols = new StringBuilder("");
		StringBuilder vals = new StringBuilder("");
		columns = introspectedTable.getAllColumns();
		
		Iterator<IntrospectedColumn> iterator = columns.iterator();
		int i = 0;
		//OutputUtilities.xmlIndent(cols,3);
		//OutputUtilities.xmlIndent(vals, 3);
		while(iterator.hasNext()){
			IntrospectedColumn column = iterator.next();
			cols.append(column.getActualColumnName()).append(",");	
			vals.append("#{ item."+column.getJavaProperty()+", jdbcType="+column.getJdbcTypeName()+"}");
			vals.append(",");
			i++;
			if(i==3){				
				cols.append("\n");
				vals.append("\n");
				OutputUtilities.xmlIndent(cols,3);
				OutputUtilities.xmlIndent(vals, 3);
				i=0;
			}		
			
		}
		cols = cols.deleteCharAt(cols.lastIndexOf(","));
		vals = vals.deleteCharAt(vals.lastIndexOf(","));
		sql.append(cols.toString()).append(")\n");
		OutputUtilities.xmlIndent(sql,2);
		sql.append("values \n");
		OutputUtilities.xmlIndent(sql,2);
		sql.append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\" > \n");
		OutputUtilities.xmlIndent(sql,3);
		sql.append("( "+vals.toString()+") \n");
		
		OutputUtilities.xmlIndent(sql,2);
		sql.append("</foreach>");
		
		batchInsertElement.addElement(
				new TextElement(
						sql.toString()
				));
		
		parentElement.addElement(batchInsertElement);


		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	/**
	 * 自定义批量插入方法
	 * @param interfaze
	 * @param introspectedTable
	 */
	private void generateBatchInsert(Interface interfaze, IntrospectedTable introspectedTable) {
		
		Method m = new Method("batchInsert");
		
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

