package com.ch.one.project.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 15:00
 * @Description:
 */
@Getter
@Setter
public class OneReaderJOList {
	private String month;
	private List<OneReaderJO> list;
}
