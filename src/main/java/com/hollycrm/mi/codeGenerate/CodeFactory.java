/**
 * @author 张新林
 * 时间 2017年7月7日下午2:43:31
 * 包名 com.xinhuo.codegenerator.generator
 * 类名 CodeFactory
 *
 */
package com.hollycrm.mi.codeGenerate;

import com.hollycrm.mi.codeGenerate.config.GlobalConfig;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Map;

public class CodeFactory
{
  public static GlobalConfig globalConfig;

  /**
   * 把配置数据注入模版，生成代码文件
   * @param type
   * @param data
   * @throws IOException
   */
  public static void generateFile(String type, Map data) throws IOException
  {
    //初始化代码
    ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
    Configuration cfg = Configuration.defaultConfiguration();
    GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
    //获取模板
    Template t = gt.getTemplate("/codeGenerate/" + type + ".btl");
    t.binding(data);
    //渲染结果
    String entityName = data.get("entity").toString();
    String fileNamePath = getCodePath(type, entityName);//获取生成的文件路径
    File fileDir = new File(fileNamePath.substring(0, fileNamePath.lastIndexOf( "/")));
    if ( !fileDir.exists()) {
      fileDir.mkdirs();
    }
    File targetFile = new File(fileNamePath);
    if ( targetFile.exists()) {
      targetFile.delete();
    }
    Writer out = new OutputStreamWriter(
            new FileOutputStream(fileNamePath), globalConfig.getSystem_encoding());//生成的文件编码
    t.renderTo( out);
    out.close();
  }

  /**
   * 获取代码生成的文件路径
   * @param type
   * @param entityName
   * @return
   */
  public static String getCodePath(String type, String entityName)
  {
    StringBuilder path = new StringBuilder();
    if ( !StringUtils.isEmpty(type)) {
      String codeType = type;
      //开头，项目路径
      if(StringUtils.isEmpty(globalConfig.getOutputDir())){
        String projectPath = getProjectPath();//没有设置outputDir的话默认用当前项目resources/code路径下
        path.append(projectPath+"src/main/resources/code");//项目名
      }else{
        path.append(globalConfig.getOutputDir());//项目名
      }
      path.append("/");
      if("entity".equals(codeType)){
        //包名 package.path
        path.append(globalConfig.getEntityPackage());
        path.append("/");
        //文件名
        path.append(entityName).append(".java");
      }else if("mapper".equals(codeType) ){
        //包名 package.path
        path.append(globalConfig.getMapperPackage());
        path.append("/");
        //文件名
        path.append(entityName).append("Mapper").append(".java");
      }else if ( "mapperXml".equals(codeType)){
        path.append(globalConfig.getMapperPackage());
        path.append("/");
        //文件名
        path.append(entityName).append("Mapper").append(".xml");
      }else if("service".equals(codeType)){
        //包名 package.path
        path.append(globalConfig.getServicePackage());
        path.append("/");
        //文件名
        path.append(entityName).append("Service").append(".java");
      }else if("serviceImpl".equals(codeType)){
        //包名 package.path
        path.append(globalConfig.getServiceImplPackage());
        path.append("/");
        //文件名
        path.append(entityName).append("ServiceImpl").append(".java");
      }else if("controller".equals(codeType)){
        //包名 package.path
        path.append(globalConfig.getControllerPackage());
        path.append("/");
        //文件名
        path.append(entityName).append("Controller").append(".java");
      }else{
        throw new IllegalArgumentException("type is not found");
        //其他类型文件生成
      }

    } else {
      throw new IllegalArgumentException("type is null");
    }
    //String result = path.toString();
    //result = result.replaceAll("\\.", "/");

    String name = path.toString().substring(0, path.lastIndexOf("."));
    String suffix = path.substring( path.toString().lastIndexOf("."));

    name = name.replaceAll("\\.", "/");
    return name + suffix;
  }

  public static String getProjectPath()
  {
    String path = System.getProperty("user.dir").replace("\\", "/") + "/";
    return path;
  }
}
