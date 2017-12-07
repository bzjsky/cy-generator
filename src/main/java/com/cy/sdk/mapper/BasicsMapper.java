package com.cy.sdk.mapper;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author cy
 * @git https://gitee.com/bzj/cy-generator
 * @param <T>
 * @param <ID>
 */
public interface BasicsMapper<T, ID extends Serializable> {

    /**
     *
     * @param t
     * @return
     */
    int insert(T t);

    /**
     *
     * @param list
     * @return
     */
    int insertBatch(List<T> list);

    /**
     *
     * @param id
     * @return
     */
    int delete(ID id);

    /**
     *
     * @param ids
     * @return
     */
    int deleteBatch(List<ID> ids);

    /**
     *
     * @param t
     * @return
     */
    int update(T t);

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
     * @param t
     * @return
     */
    int queryTotal(T t);
}
