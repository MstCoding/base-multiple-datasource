package ssm.config.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置一个主库(写入)
 * 
 * @author AaronLee
 *
 * @version v1.0
 */
@Configuration
@MapperScan(basePackages = WriteConfig.PACKAGES, sqlSessionFactoryRef = "writeSqlSessionFactory")
public class WriteConfig {
	static final String PACKAGES = "ssm.config.write.dao";

	private static final String MAPPER_LOCAL = "classpath:mapper/write/*.xml";

	// 配置文件中的数据源前缀
	@ConfigurationProperties("spring.datasource.write")
	@Primary
	@Bean(name = "writeDataSource")
	public DruidDataSource druidDataSource() {
		return new DruidDataSource();
	}

	// 事务管理器
	@Bean(name = "writeTransactionManager")
	@Primary
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(druidDataSource());
	}

	// session
	@Bean(name = "writeSqlSessionFactory")
	@Primary
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("writeDataSource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
		return sessionFactoryBean.getObject();
	}
}
