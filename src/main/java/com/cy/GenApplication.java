package com.cy;

import com.cy.generator.GeneratorConstants;
import com.cy.generator.GeneratorFacade;
import com.cy.generator.GeneratorProperties;
import com.cy.generator.util.GLogger;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 *
 * @author cy
 * @git https://gitee.com/bzj/cy-generator
 */
public class GenApplication {

/*    public enum TemplateEnum{
        *//** 模版一 *//*
        TEMPLATE_1("template_1"),
        *//** 模版二 适用renren-fast项目 *//*
        TEMPLATE_2("template_2"),
        TEMPLATE_3("template_3");
        private String path;
        private TemplateEnum(String path){
            this.path = path;
        }
        public String getPath() {
            return path;
        }
    }*/

    public static void main(String[] args) throws Exception {

        GeneratorFacade g = new GeneratorFacade();
        g.getGenerator().addTemplateRootDir(g.getGenerator().getTemplateDir());
        g.getGenerator().setOutRootDir(g.getGenerator().getOutRootDir() + File.separator);

        // 通过数据库表生成文件
        g.generateByTable(GeneratorProperties.getProperty(GeneratorConstants.GENERATOR_TABLES).split(","));

        // 删除生成器的输出目录//
        // g.deleteOutRootDir();

        // 自动搜索数据库中的所有表并生成文件,template为模板的根目录
        // g.generateByAllTable();

        // 按table名字删除文件
        // g.deleteByTable("table_name", "template");
    }
}
