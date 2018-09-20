package com.sky.auth.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//import com.github.pagehelper.PageHelper;

/**
 * @author yangfan
 * @Desc MyBatis基础配置
 * 
 */
@Deprecated   //使用mybatis-spring-boot-starter,不再需要配置
//@Configuration
//@AutoConfigureAfter(DataSource.class)
public class MyBatisConfig implements TransactionManagementConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Autowired
    DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        logger.info("SqlSessionFactory is initializing");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            logger.info("SqlSessionFactory is initialized");
        }
    }

    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
    
//    @Bean
//    public PageHelper pageHelper(){
//    	PageHelper pageHelper = new PageHelper();
//    	Properties properties = new Properties();
//    	properties.setProperty("offsetAsPageNum","true");
//    	properties.setProperty("rowBoundsWithCount","true");
//    	properties.setProperty("reasonable","true");
//    	properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
//    	pageHelper.setProperties(properties);
//    	return pageHelper;
//    }

}
