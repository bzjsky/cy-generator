/*
 * Powered By Auto-generated
 *  
 * Since 2017 - 2017
 */
package com.cy.biz.member.controller;

import javax.annotation.Resource;

import com.cy.biz.member.entity.Navigation;
import com.cy.biz.member.service.NavigationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.sdk.controller.BasicsController;

/**
 * 导航
 * @author Auto-generated
 * @version 1.0
 * @since 1.0
 * @createTime:2017-09-21 17:58:31
 * */
@RestController
@RequestMapping("navigation")
public class NavigationController extends BasicsController<Navigation, Long>{

	@Resource
	NavigationService navigationService;

}
