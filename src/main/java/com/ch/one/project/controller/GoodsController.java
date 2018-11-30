package com.ch.one.project.controller;

import com.ch.one.core.base.BaseController;
import com.ch.one.project.dao.GoodsDao;
import com.ch.one.project.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/17 14:58
 * @Description:
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	@Autowired
	private GoodsDao goodsDao;

	@RequestMapping("/getGoods")
	@ResponseBody
	public Object getGoods(){
		List<Goods> goods = goodsDao.getAllGoods();
		return goods;
	}

}
