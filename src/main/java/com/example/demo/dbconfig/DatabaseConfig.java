package com.example.demo.dbconfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

//import lombok.experimental.var;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo"}, sqlSessionFactoryRef = "routeSqlSessionFactory")
public class DatabaseConfig {
	
//	Connection conn = null;
	
//    @Primary
    @Bean(name = "masterDataSource")
//    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
        		.driverClassName("org.postgresql.Driver")
        		.url("jdbc:postgresql://131.186.27.99:5432/postgres")
        		.username("postgres")
        		.password("new1234!")
                .type(HikariDataSource.class)
                .build();
    }
    
    
    @Bean(name = "slaveDataSource")
//    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
        		.driverClassName("org.postgresql.Driver")
        		.url("jdbc:postgresql://131.186.27.99:5432/cm_park")
        		.username("pcm01")
        		.password("mook")
                .type(HikariDataSource.class)
                .build();
    }
    
    @Bean(name = "routingDataSource")
//    @Bean
    @DependsOn({"masterDataSource", "slaveDataSource"})
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {
   

    	RoutingDataSource routingDataSource = new RoutingDataSource();

       	log.info("=========== routingDataSource : {}", routingDataSource.toString());

        Map<Object, Object> dataSourceMap = new HashMap<>();
       	
        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave", slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);    	
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        routingDataSource.afterPropertiesSet();
        
        log.info("==========size :{}", routingDataSource.getResolvedDataSources().size());
       	
//        try {
//			conn=	dynamicRoutingDataSource.getConnection();
//	       	log.info("=========== conn user  : {}", conn.getMetaData().getUserName());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
               
 
        return routingDataSource;
        
    }

//    @Primary
//    @Bean(name = "finDataSource")
    @Bean
    @DependsOn("routingDataSource")
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
 
//    -------------- JPA EntityManager -------------------
//    @Primary
    @Bean(name = "routeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean routeEntityManagerFactory(
    		@Qualifier("dataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.demo");
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
//        properties.put("hibernate.hbm2ddl.auto", SpringPhysicalNamingStrategy.class.getName());
        
        em.setJpaPropertyMap(properties);
               
        return em;
    }
//
////    @Primary
    @Bean(name = "routeTransactionManager")
    public PlatformTransactionManager routeTransactionManager(
            @Qualifier("routeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);

        return tm;
    }
    
    
//    ------------------SQL SessionFactory -----------------------
//    @Bean
//    public PlatformTransactionManager transactionManager(LazyConnectionDataSourceProxy lazyDataSource) {
//        return new DataSourceTransactionManager(lazyDataSource);
//    }
    
//    @Primary
    @Bean(name = "routeSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
 
    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

    	sqlSessionFactoryBean.setDataSource(dataSource);
//    	sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:common/mybatis/config.xml"));
//    	sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:common/mybatis/mappers/**/*.xml"));
//    	sqlSessionFactoryBean.setTypeHandlersPackage("com.example.sample.config.mybatis");
    	sqlSessionFactoryBean.setTypeAliasesPackage("com.example");
    	sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/routingtest01.xml"));
    	
        return sqlSessionFactoryBean.getObject();
    }   

}
