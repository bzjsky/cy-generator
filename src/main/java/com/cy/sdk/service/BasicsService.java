package com.cy.sdk.service;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cy
 * @git https://gitee.com/bzj/cy-generator
 * @param <T>
 * @param <ID>
 */
public interface BasicsService<T, ID extends Serializable>  {
    /**
     *
     * @param t
     * @return
     */
    boolean insert(T t);

    /**
     *
     * @param list
     * @return
     */
    boolean insertBatch(List<T> list);

    /**
     *
     * @param id
     * @return
     */
    boolean delete(ID id);

    /**
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(List<ID> ids);

    /**
     *
     * @param t
     * @return
     */
    boolean update(T t);

    /**
     *
     * @param id
     * @return
     */
    T getObjectByPK(ID id);

    /**
     *
     * @param t
     * @return
     */
    List<T> queryList(T t);

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param t
     * @return
     */
    PageInfo<T> queryListPage(Integer pageNum, Integer pageSize, T t);

    /**
     *
     * @param t
     * @return
     */
    int queryTotal(T t);

}
