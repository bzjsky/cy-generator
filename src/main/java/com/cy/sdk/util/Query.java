package com.cy.sdk.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 * @author cy<2017年7月27日>
 */
public class Query extends LinkedHashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	//当前页码
    private int pageNum;
    //每页条数
    private int pageSize;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.pageNum = params.get("pageNum") != null ? Integer.parseInt(params.get("pageNum").toString()) : 1;
        this.pageSize = params.get("pageSize") != null ? Integer.parseInt(params.get("pageSize").toString()) : 10;
        this.put("offset", (pageNum - 1) * pageSize);
        this.put("page", pageNum);
        this.put("pageSize", pageSize);
    }

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
