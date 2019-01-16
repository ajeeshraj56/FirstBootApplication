package com.ajeres.com.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseObj<T> {

	private List<T> data;
	private String message;
	private Status status;
	public List<T> getData() {
		if(data==null)
		{
			data = new ArrayList<>();
		}
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
