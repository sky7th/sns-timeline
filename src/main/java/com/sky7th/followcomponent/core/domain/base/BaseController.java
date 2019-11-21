package com.sky7th.followcomponent.core.domain.base;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BaseController {

	protected static final String APPLICATION_JSON = "application/json; charset=UTF-8";

	protected HashMap<String, Object> getSuccessResult() {
		HashMap<String, Object> retMap = new HashMap<>();
		retMap.put("result", "success");
		return retMap;
	}

	protected HashMap<String, Object> getSuccessResult(String msg) {
		HashMap<String, Object> retMap = getSuccessResult();
		retMap.put("msg", msg);
		return retMap;
	}

	protected HashMap<String, Object> getSuccessResult(HashMap<String, Object> retMap, Object data) {
		retMap.put("result", "success");
		retMap.put("data", data);
		return retMap;
	}

	protected HashMap<String, Object> getSuccessResult(Object data) {
		HashMap<String, Object> retMap = getSuccessResult();
		retMap.put("data", data);
		return retMap;
	}

	protected HashMap<String, Object> getSuccessResult(Object data, String msg) {
		HashMap<String, Object> retMap = getSuccessResult(data);
		retMap.put("msg", msg);
		return retMap;
	}

	protected HashMap<String, Object> getFailResult() {
		HashMap<String, Object> retMap = new HashMap<>();
		retMap.put("result", "failed");
		return retMap;
	}

	protected HashMap<String, Object> getFailResult(String errMsg) {
		HashMap<String, Object> retMap = getFailResult();
		retMap.put("errmsg", errMsg);
		return retMap;
	}

	protected HashMap<String, Object> getFailResult(String errMsg, Object data) {
		HashMap<String, Object> retMap = getFailResult(errMsg);
		retMap.put("data", data);
		return retMap;
	}

}
