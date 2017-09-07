/*
 * Powered By Auto-generated
 *  
 * Since 2017 - 2017
 */

package com.cy.business.test.entity;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;
import com.cy.sdk.entity.BasicsEntity;

/**
 * 扩展商品
 * @author Auto-generated
 * @version 1.0
 * @since 1.0
 * @createTime:2017-09-07 10:47:29
 * */
public class GoodsExt extends BasicsEntity{

	private static final long serialVersionUID = 1L;

    /**
     * 编号
     */	
	@Max(9223372036854775807L)
	private Long id;
    /**
     * 标题
     */	
	@NotBlank @Length(max=40)
	private String title;
    /**
     * 内容
     */	
	@NotBlank @Length(max=200)
	private String content;
    /**
     * 创建者
     */	
	@NotNull @Max(9223372036854775807L)
	private Long createId;
    /**
     * 创建时间
     */	
	@NotNull 
	private Date createDate;
    /**
     * 更新时间
     */	
	
	private Date modifyDate;

	public GoodsExt(){
	}
	/**
	 * 设置 编号
	 */
	public void setId(Long value) {
		this.id = value;
	}

	/**
	 * 获取 编号
	 */
	public Long getId() {
		return this.id;
	}
	/**
	 * 设置 标题
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * 获取 标题
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * 设置 内容
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * 获取 内容
	 */
	public String getContent() {
		return this.content;
	}
	/**
	 * 设置 创建者
	 */
	public void setCreateId(Long value) {
		this.createId = value;
	}

	/**
	 * 获取 创建者
	 */
	public Long getCreateId() {
		return this.createId;
	}
	/**
	 * 设置 创建时间
	 */
	public void setCreateDate(Date value) {
		this.createDate = value;
	}

	/**
	 * 获取 创建时间
	 */
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 设置 更新时间
	 */
	public void setModifyDate(Date value) {
		this.modifyDate = value;
	}

	/**
	 * 获取 更新时间
	 */
	public Date getModifyDate() {
		return this.modifyDate;
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof GoodsExt)) return false;
		if(this == obj) return true;
		GoodsExt other = (GoodsExt)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

