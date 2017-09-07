package com.cy.sdk.service.impl;

import com.cy.sdk.mapper.BasicsMapper;
import com.cy.sdk.service.BasicsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cy
 * @email bzjsky@sina.com
 * @param <T>
 * @param <ID>
 */
public class BasicsServiceImpl<T, ID extends Serializable> implements BasicsService<T, ID> {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    BasicsMapper<T, ID> basicsMapper;

    @Override
    public boolean insert(T t) {
        return basicsMapper.insert(t) > 0;
    }

    @Override
    public boolean insertBatch(List<T> list) {
        return basicsMapper.insertBatch(list) > 0;
    }

    @Override
    public boolean delete(ID id) {
        return basicsMapper.delete(id) > 0;
    }

    @Override
    public boolean deleteBatch(List<ID> ids) {
        return basicsMapper.deleteBatch(ids) > 0;
    }

    @Override
    public boolean update(T t) {
        return basicsMapper.update(t) > 0;
    }

    @Override
    public T getObjectByPK(ID id) {
        return basicsMapper.getObjectByPK(id);
    }

    @Override
    public List<T> queryList(T t) {
        return basicsMapper.queryList(t);
    }

    @Override
    public PageInfo<T> queryListPage(Integer pageNum, Integer pageSize, T t) {
        if(pageNum == null) pageNum = 1;
        if(pageSize == null) pageSize = 10;
        return new PageInfo<T>(PageHelper.startPage(pageNum, pageSize).doSelectPage(()-> basicsMapper.queryList(t)));
    }

    @Override
    public int queryTotal(T t) {
        return basicsMapper.queryTotal(t);
    }

}
