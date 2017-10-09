/*
 * Powered By Auto-generated
 *  
 * Since 2017 - 2017
 */
package com.cy.biz.member.entity;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.cy.sdk.entity.BasicsEntity;

/**
 * 导航
 * @author Auto-generated
 * @version 1.0
 * @since 1.0
 * @createTime:2017-09-21 17:58:31
 * */
public class Navigation extends BasicsEntity{

	private static final long serialVersionUID = 1L;
    /**
    * 名称
    */
	@NotBlank @Length(max=50)
    private String name;

    /**
    * 位置
    */
	@Length(max=50)
    private String position;

    /**
    * 链接地址
    */
	@Length(max=50)
    private String url;

    /**
    * 是否新窗口打开
    */
	@NotNull 
    private Integer isBlankTarget;

    /**
    * 排序
    */
	@NotNull 
    private Integer orders;

	public Navigation(){
	}
	/**
	 * 设置 名称
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * 获取 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 位置
	 */
	public void setPosition(String value) {
		this.position = value;
	}

	/**
	 * 获取 位置
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 * 设置 链接地址
	 */
	public void setUrl(String value) {
		this.url = value;
	}

	/**
	 * 获取 链接地址
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 设置 是否新窗口打开
	 */
	public void setIsBlankTarget(Integer value) {
		this.isBlankTarget = value;
	}

	/**
	 * 获取 是否新窗口打开
	 */
	public Integer getIsBlankTarget() {
		return this.isBlankTarget;
	}

	/**
	 * 设置 排序
	 */
	public void setOrders(Integer value) {
		this.orders = value;
	}

	/**
	 * 获取 排序
	 */
	public Integer getOrders() {
		return this.orders;
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Navigation)) return false;
		if(this == obj) return true;
		Navigation other = (Navigation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

