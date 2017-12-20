package com.cy.sdk.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author bzj
 * @date: 2017/12/20 11:31
 */
public class CyPageHelper extends PageHelper {

    public static <E> Page<E> startPage(Integer pageNum, Integer pageSize) {
        Page<E> page = new Page(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize, true);
        setLocalPage(page);
        return page;
    }
}
