/*
 * Powered By Auto-generated
 *  
 * Since 2017 - 2017
 */

package com.cy.business.test.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.sdk.controller.BasicsController;
import com.cy.business.test.entity.GoodsExt;
import com.cy.business.test.service.GoodsExtService;

/**
 * 扩展商品
 * @author Auto-generated
 * @version 1.0
 * @since 1.0
 * @createTime:2017-09-07 10:47:29
 * */
@RestController
@RequestMapping("goodsExt")
public class GoodsExtController extends BasicsController<GoodsExt, Long>{

	@Resource
	GoodsExtService goodsExtService;

}
