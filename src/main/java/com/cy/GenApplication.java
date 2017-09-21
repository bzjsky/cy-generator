package com.cy;

import com.cy.generator.GeneratorFacade;

import java.io.File;

/**
 *
 * @author cy
 * @email bzjsky@sina.com
 */
public class GenApplication {

    private static final String TEMPLATE_PATH =  "templates";
    private static final String OUTROOT_PATH =  "outRoot";

    public enum TemplateEnum{
        /** 模版一 */
        TEMPLATE_1("template_1"),
        /** 模版二 适用renren-fast项目 */
        TEMPLATE_2("template_2");
        private String path;
        private TemplateEnum(String path){
            this.path = path;
        }
        public String getPath() {
            return path;
        }
    }

    public static void main(String[] args) throws Exception {

        GeneratorFacade g = new GeneratorFacade();
        TemplateEnum template = TemplateEnum.TEMPLATE_2;
        g.getGenerator().addTemplateRootDir(g.getGenerator().getOutRootDir() + File.separator + TEMPLATE_PATH
                + File.separator + template.getPath());

        g.getGenerator().setOutRootDir(g.getGenerator().getOutRootDir() + File.separator + OUTROOT_PATH
                + File.separator + template.getPath());

        // 通过数据库表生成文件
        g.deleteByTable("cy_navigation");
        g.generateByTable("cy_navigation");

        // 删除生成器的输出目录//
        // g.deleteOutRootDir();

        // 自动搜索数据库中的所有表并生成文件,template为模板的根目录
        // g.generateByAllTable();

        // 按table名字删除文件
        // g.deleteByTable("table_name", "template");
    }
}
