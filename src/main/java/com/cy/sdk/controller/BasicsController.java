package com.cy.sdk.controller;

import com.cy.sdk.entity.BasicsEntity;
import com.cy.sdk.service.BasicsService;
import com.cy.sdk.util.DateEditor;
import com.cy.sdk.util.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author cy
 * @git https://gitee.com/bzj/cy-generator
 * @param <T>
 * @param <ID>
 */
public abstract class BasicsController<T extends BasicsEntity, ID extends Serializable> {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private BasicsService<T, ID> basicsService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    @RequestMapping("save")
    public Message save(@RequestBody T t){
        try {
            t.setCreateDate(new Date());
            t.setModifyDate(t.getCreateDate());
            return Message.status(basicsService.insert(t));
        } catch (Exception e){
            logger.error("保存异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("saveBatch")
    public Message saveBatch(List<T> list){
        try {
            return Message.status(basicsService.insertBatch(list));
        } catch (Exception e){
            logger.error("批量保存异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("remove")
    public Message remove(ID id){
        try {
            return Message.status(basicsService.delete(id));
        } catch (Exception e){
            logger.error("删除异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("removeBatch")
    public Message removeBatch(@RequestBody List<ID> ids){
        try {
            if(ids.size() == 1)
                return remove(ids.get(0));
            else {
                return Message.status(basicsService.deleteBatch(ids));
            }
        } catch (Exception e){
            logger.error("批量删除异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("modify")
    public Message modify(@RequestBody T t){
        try {
            t.setModifyDate(new Date());
            return Message.status(basicsService.update(t));
        } catch (Exception e){
            logger.error("更新异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("getById")
    public Message getById(ID id){
        try {
            return Message.success(basicsService.getObjectByPK(id));
        } catch (Exception e){
            logger.error("查询详情异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("queryList")
    public Message queryList(T t){
        try {
            return Message.success(basicsService.queryList(t));
        } catch (Exception e){
            logger.error("查询列表异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }

    @RequestMapping("queryListPage")
    public Message queryListPage(Integer pageNum, Integer pageSize, T t){
        try {
            return Message.success(basicsService.queryListPage(pageNum, pageSize, t));
        } catch (Exception e){
            logger.error("分页查询异常：===》 " + e);
            return Message.exception(e.getMessage());
        }
    }
}
