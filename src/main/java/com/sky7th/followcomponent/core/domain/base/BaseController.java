package com.sky7th.followcomponent.core.domain.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BaseController {

	protected static final String APPLICATION_JSON = "application/json; charset=UTF-8";

	protected Map<String, Object> getSuccessResult() {
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("result", "success");
		return retMap;
	}

	protected Map<String, Object> getSuccessResult(String msg) {
		Map<String, Object> retMap = getSuccessResult();
		retMap.put("msg", msg);
		return retMap;
	}

	protected Map<String, Object> getSuccessResult(Map<String, Object> retMap, Object data) {
		retMap.put("result", "success");
		retMap.put("data", data);
		return retMap;
	}

	protected Map<String, Object> getSuccessResult(Object data) {
		Map<String, Object> retMap = getSuccessResult();
		retMap.put("data", data);
		return retMap;
	}

	protected Map<String, Object> getSuccessResult(Object data, String msg) {
		Map<String, Object> retMap = getSuccessResult(data);
		retMap.put("msg", msg);
		return retMap;
	}

	protected Map<String, Object> getFailResult() {
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("result", "failed");
		return retMap;
	}

	protected Map<String, Object> getFailResult(String errMsg) {
		Map<String, Object> retMap = getFailResult();
		retMap.put("errmsg", errMsg);
		return retMap;
	}

	protected Map<String, Object> getFailResult(String errMsg, Object data) {
		Map<String, Object> retMap = getFailResult(errMsg);
		retMap.put("data", data);
		return retMap;
	}

}
