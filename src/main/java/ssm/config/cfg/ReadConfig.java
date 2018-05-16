package ssm.config.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = ReadConfig.PACKAGES, sqlSessionFactoryRef = "readSqlSessionFactory")
public class ReadConfig {
	static final String PACKAGES = "ssm.config.read.dao";

	private static final String MAPPER_LOCAL = "classpath:mapper/read/*.xml";

	// 配置文件中的数据源前缀
	@ConfigurationProperties("custom.spring.datasource.read")
	@Bean(name = "readDataSource")
	public DruidDataSource druidDataSource() {
		return new DruidDataSource();
	}

	// 事务管理器
	@Bean(name = "readTransactionManager")
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(druidDataSource());
	}

	// session
	@Bean(name = "readSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
		return sessionFactoryBean.getObject();
	}
}
