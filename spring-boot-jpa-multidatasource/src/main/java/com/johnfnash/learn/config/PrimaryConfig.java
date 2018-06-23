package com.johnfnash.learn.config;

import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary", transactionManagerRef = "transactionManagerPrimary", basePackages = {
		"com.johnfnash.learn.dao.primary" })
public class PrimaryConfig {

	@Resource
	@Qualifier("primaryDataSource")
	private DataSource primaryDataSource;

	@Resource
	private JpaProperties jpaProperties;

	@Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;
	
	private Map<String, Object> getVendorProperties() {
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}

	@Primary
	@Bean(name = "entityManagerFactoryBuilderPrimary")
	public EntityManagerFactoryBuilder entityManagerFactoryBuilderPrimary() {
		AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return new EntityManagerFactoryBuilder(adapter, getVendorProperties(), persistenceUnitManager);
	}
	
	@Primary  
    @Bean(name = "entityManagerPrimary")  
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {  
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}

	/** 
     * 设置实体类所在位置 
     */  
    @Primary  
    @Bean(name = "entityManagerFactoryPrimary")  
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) { 
		return builder  
                .dataSource(primaryDataSource)  
                .packages("com.johnfnash.learn.entity.primary")  
                .persistenceUnit("primaryPersistenceUnit")  
                .properties(getVendorProperties())  
                .build();  
	}
    
    @Primary  
    @Bean(name = "transactionManagerPrimary")  
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
    	return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());  
    }

}
