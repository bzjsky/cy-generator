<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${basepackage}.service.*;
<#include "/java_imports.include">

/**
<#include "/java_description.include">
 */
@Controller
public class ${className}Controller {

	@Resource
	I${className}Service ${classNameLower}Service;
	
	
	/** 
	 * 主页  集成分页 插件 PageHelper
	 **/
	@RequestMapping("${classNameLower}_index.action")
	public String list(Model model, Integer pageNumber, Integer pageSize) {
		if(pageNumber==null||pageNumber==0){
			pageNumber = 1;
		}
		if(pageSize ==null){
			pageSize = 10;
		}
		
		//开始分页   参数:页码, 每页显示数量
        PageHelper.startPage(pageNumber,10);
        List<${className}> ${classNameLower}_lists = ${classNameLower}Service.findAll(null);
        PageInfo<${className}> ${classNameLower}s = new PageInfo<${className}>(${classNameLower}_lists);
        
		model.addAttribute("${classNameLower}s",${classNameLower}s);
		
		return "/page/${classNameLower}/index.jsp";
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping("${classNameLower}_get.action")
	public String get(int id, Model model){
		
		 List<${className}> ${classNameLower}_lists = ${classNameLower}Service.findAll(null);
		 ${className} pojo = ${classNameLower}Service.findById(id);
		   
         model.addAttribute("pojo", pojo);
         model.addAttribute("${classNameLower}_lists",${classNameLower}_lists);
         
		 return "/page/${classNameLower}/get.jsp";
	}
	
	/** 
	 * 进入新增页面
	 **/
	@RequestMapping("${classNameLower}_toinsert.action")
	public String toinsert_admin() {
		
		return "/page/${classNameLower}/add.jsp";
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping("${classNameLower}_insert.action")
	public String insert(${className} ${classNameLower}) {
		
		${classNameLower}Service.save(${classNameLower});
		
		return "redirect:${classNameLower}_index.action";
	}
	
	/**
	 * 进入更新页面
	 **/
	@RequestMapping("${classNameLower}_toupdate.action")
	public String toupdate(int id, Model model) {
		${className} po = ${classNameLower}Service.findById(id);
		
		model.addAttribute("po",po);
		
		return "/page/${classNameLower}/edit.jsp";
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping("${classNameLower}_update.action")
	public String update(int id, Model model) {
		${classNameLower}Service.findById(id);
		
		return "redirect:${classNameLower}_index.action";
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping("${classNameLower}_delete.action")
	public String delete_admin(int id) {
		
		${classNameLower}Service.deleteById(id);
		
		return "redirect:${classNameLower}_index.action";
	}
	
}
