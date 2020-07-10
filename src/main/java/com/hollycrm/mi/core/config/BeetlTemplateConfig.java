package com.hollycrm.mi.core.config;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class BeetlTemplateConfig {

    @Value("${beetl.templatesPath:templates}")
    String templatesPath;

    @Value("${beetl.suffix:btl}")
    String suffix;

    @Value("${beetl.dev:true}")
    boolean dev;
    @Autowired(
            required = false
    )
    public BeetlTemplateConfig() {
    }

    @Bean(name = {"beetlConfig"})
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        try {
            Properties extProperties = new Properties();
            /*
            if (this.dev) {
                extProperties.put("RESOURCE.autoCheck", "true");
            } else {
                extProperties.put("RESOURCE.autoCheck", "false");
            }
            */
            extProperties.put("RESOURCE.autoCheck", "true");
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                loader = BeetlTemplateConfig.class.getClassLoader();
            }

            beetlGroupUtilConfiguration.setConfigProperties(extProperties);
            ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, this.templatesPath);
            beetlGroupUtilConfiguration.setResourceLoader(cploder);
            beetlGroupUtilConfiguration.init();
            beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);

            return beetlGroupUtilConfiguration;
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }

    @Bean(name = {"beetlViewResolver"})
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setViewNames(new String[]{"*." + suffix});
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }

    @Bean( name = {"groupTemplate"})
    @ConditionalOnMissingBean({GroupTemplate.class})
    public GroupTemplate getGroupTemplate(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        GroupTemplate gt = beetlGroupUtilConfiguration.getGroupTemplate();
        return gt;
    }
}
