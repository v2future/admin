package com.hollycrm.mi.codeGenerate;

import com.hollycrm.mi.codeGenerate.config.DataSourceConfig;
import com.hollycrm.mi.codeGenerate.config.GlobalConfig;

public class MyBatisGenerate {

    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig();//全局配置
        globalConfig.setTemplatepath("/codeGenerate");//自定义模板路径
        globalConfig.setAuthor("");
        globalConfig.setBasePackage("com.hollycrm.mi.core");
        globalConfig.setTableNames(new String[]{"tbl_sys_user"});//需要生成的实体
        globalConfig.setRemoveTablePrefix("tbl_");//生成的实体移除前缀
        globalConfig.setOutputDir("E://target/");//文件输出路径，不配置的话默认输出当前项目的resources/code目录下

        DataSourceConfig dsc = new DataSourceConfig();//数据库配置
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/mi?useUnicode=true&amp;characterEncoding=UTF-8");
        dsc.setUsername("root");//填写自己的数据库账号
        dsc.setPassword("123456");//填写自己的数据库密码
        CodeGenerate codeGenerate = new CodeGenerate(globalConfig, dsc);
        //生成代码
        codeGenerate.generateToFile();
    }
}
