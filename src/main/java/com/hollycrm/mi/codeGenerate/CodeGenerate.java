package com.hollycrm.mi.codeGenerate;

import com.hollycrm.mi.codeGenerate.config.DataSourceConfig;
import com.hollycrm.mi.codeGenerate.config.GlobalConfig;
import com.hollycrm.mi.codeGenerate.po.TableField;
import com.hollycrm.mi.codeGenerate.po.TableInfo;
import com.hollycrm.mi.codeGenerate.utils.CodeGenerateUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerate{

  private List<TableInfo> tableInfoList;
  private TableInfo tableInfo;
  private GlobalConfig globalConfig;
  private DataSourceConfig dataSourceConfig;

  public CodeGenerate() {
  }

    public CodeGenerate(GlobalConfig globalConfig,DataSourceConfig dataSourceConfig)
    {
        this.globalConfig = globalConfig;
        this.dataSourceConfig = dataSourceConfig;
    }

    /**
     * 生成代码文件
     * @return
     */
    public boolean generateToFile() {
        initConfig();
        CodeFactory.globalConfig = this.globalConfig;
        for(TableInfo tableInfo : tableInfoList){
            this.tableInfo = tableInfo;//当前需要生成的表
            System.out.println("[单表模型:" + tableInfo.getName() + "]------- 生成中。。。");
            try{
                Map tableMap = this.execute();
                //CodeFactory.generateFile("entity", tableMap);
                //CodeFactory.generateFile("mapper", tableMap);
                //CodeFactory.generateFile("mapperXml", tableMap);
                //CodeFactory.generateFile("service", tableMap);
                CodeFactory.generateFile("serviceImpl", tableMap);
                /*
                CodeFactory.generateFile("controller", tableMap);
                */
                System.out.println("[单表模型：" + tableInfo.getName() + "]------ 生成完成。。。");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("[单表模型：" + tableInfo.getName() + "]------ 生成失败。。。");
                return false;
            }
        }

        return true;
    }

    private void initConfig(){
      if(dataSourceConfig == null){
          throw new RuntimeException("dataSourceConfig is null");
      }
      if(globalConfig == null){
          throw new RuntimeException("globalConfig is null");
      }
      tableInfoList = dataSourceConfig.getTablesInfo(globalConfig.getTableNames());
    }

    private Map<String, Object> execute() {
        Map data = new HashMap();
        data.put("entityPackage", globalConfig.getEntityPackage());//实体的包名
        data.put("controllerPackage", globalConfig.getControllerPackage());
        data.put("servicePackage", globalConfig.getServicePackage());
        data.put("serviceImplPackage", globalConfig.getServiceImplPackage());
        data.put("mapperPackage",globalConfig.getMapperPackage());
        //移除表前缀，表名之间的下划线，得到实体类型
        String entity = CodeGenerateUtils.formatClassName(
                CodeGenerateUtils.removePrefix(tableInfo.getName().toLowerCase(),globalConfig.getRemoveTablePrefix()));
        data.put("entity", StringUtils.capitalize(entity)); //实体名称
        data.put("author", globalConfig.getAuthor());       //创建作者
        data.put("date",  CodeGenerateUtils.getFormatTime("yyyy-MM-dd", new Date() ));//创建时间
        data.put("table", tableInfo);//表信息
        boolean isKeyflag = false;
        for (TableField field:tableInfo.getFields()) {
            if(field.isKeyIdentityFlag()){//获取主键字段信息
                data.put("tbKey", field.getName());
                data.put("tbKeyType", field.getPropertyType());
                isKeyflag = true;
                break;
            }
        }
        if(!isKeyflag){
            throw new RuntimeException(String.format("[%s]表缺少主键，不能没有主键",tableInfo.getName()));
        }
        return data;
    }
}
