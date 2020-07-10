package com.hollycrm.mi.codeGenerate.config;

/**
 * 描述
 */
public class GlobalConfig {

    private String system_encoding = "utf-8";       //文件编码
    private String templatepath = "/template";      //模板
    private String outputDir;                       //文件输出路径，不配置的话默认输出当前项目的resources/code目录下

    private String[] tableNames;    //表名
    private String basePackage;     //基础包路径

    private String entityPackage = "entity";        //实体包
    private String mapperPackage = "mapper";        //dao层包名
    private String serviceImplPackage = "service.impl";//serviceImpl层包名
    private String servicePackage = "service";      //service层包名
    private String controllerPackage = "rest";//控制层包名

    private String removeTablePrefix;        //表前缀
    private String author="";       //作者

    public String getEntityPackage() {
        return basePackage + "." + entityPackage;
    }

    public String getMapperPackage() {
        return basePackage + "." + mapperPackage;
    }

    public String getServiceImplPackage() {
        return basePackage + "." + serviceImplPackage;
    }

    public String getServicePackage() {
        return basePackage + "." + servicePackage;
    }

    public String getControllerPackage() {
        return basePackage + "." + controllerPackage;
    }

    public String getSystem_encoding() {
        return system_encoding;
    }

    public void setSystem_encoding(String system_encoding) {
        this.system_encoding = system_encoding;
    }

    public String getTemplatepath() {
        return templatepath;
    }

    public void setTemplatepath(String templatepath) {
        this.templatepath = templatepath;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getRemoveTablePrefix() {
        return removeTablePrefix;
    }

    public void setRemoveTablePrefix(String removeTablePrefix) {
        this.removeTablePrefix = removeTablePrefix;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
