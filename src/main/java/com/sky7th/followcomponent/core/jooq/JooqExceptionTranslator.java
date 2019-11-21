package com.sky7th.followcomponent.core.jooq;

import org.jooq.DSLContext;
import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

public class JooqExceptionTranslator extends DefaultExecuteListener {

	@Override
	public void exception(ExecuteContext context) {
		SQLDialect dialect = context.configuration().dialect();
		SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(dialect.name());

		context.exception(translator.translate("Access database using jOOQ", context.sql(), context.sqlException()));
	}

	@Override
	public void executeStart(ExecuteContext ctx) {
		DSLContext create = DSL.using(ctx.dialect(), new Settings().withRenderFormatted(true));

		if (ctx.query() != null) {
			System.out.println(create.renderInlined(ctx.query()));
		} else if (ctx.routine() != null) {
			System.out.println(create.renderInlined(ctx.routine()));
		}
	}

}
