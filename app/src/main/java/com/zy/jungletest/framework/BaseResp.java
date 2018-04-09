package com.zy.jungletest.framework;

import java.util.Date;

/**
 * 返回数据基类
 * 
 * @author goach
 * 
 */
public class BaseResp<T> {
	private int resultCode;
	private String errMsg;
	private Date responseTime;
	private T data;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errmsg) {
		this.errMsg = errmsg;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
