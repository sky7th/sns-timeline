package com.sky7th.followcomponent.core.domain.base;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

	@Autowired
	protected DSLContext jooq;

}
