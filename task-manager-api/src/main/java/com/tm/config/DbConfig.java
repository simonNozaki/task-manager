package com.tm.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * DB接続のコンフィグクラスです。
 */
@Configuration
@MapperScan(basePackages = DbConfig.BASE_PACKAGES, sqlSessionTemplateRef = "SqlSessionTemplate")
public class DbConfig {

    // ルートパッケージクラスパス
	public static final String BASE_PACKAGES = "com.tm.dao";

	// XMLのクラスパス
    public static final String MAPPER_XML_PATH = "classpath:com/tm/dao/**/*.xml";

    @Primary
    @Bean(name = "DataSource")
    @ConfigurationProperties(prefix = "tm.datasource")
    public DataSource dataSource() {
        return new DataSource();
    }

    @Primary
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("DataSource") DataSource primaryDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(primaryDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        return bean.getObject();
    }

    @Bean(name = "SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
