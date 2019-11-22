package com.sky7th.followcomponent;

import javax.sql.DataSource;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import com.sky7th.followcomponent.core.jooq.JooqExceptionTranslator;

@SpringBootApplication
public class FollowcomponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FollowcomponentApplication.class, args);
	}

	@Bean
	public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
		return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
	}

	@Bean
	public DefaultDSLContext dsl(DataSource dataSource) {
		DefaultConfiguration configuration = new DefaultConfiguration();
		configuration.set(connectionProvider(dataSource));
		configuration.set(new DefaultExecuteListenerProvider(new JooqExceptionTranslator()));
		configuration.set(SQLDialect.MYSQL);

		return new DefaultDSLContext(configuration);
	}

}
