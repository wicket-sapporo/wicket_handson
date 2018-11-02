package com.example.beans;

import java.io.Serializable;
import java.util.List;

public class Header implements Serializable {
	private static final long serialVersionUID = 1L;

	private String headerName;
	private List<String> subHeaders;

	public Header() {
		this("", null);
	}

	public Header(String headerName, List<String> subHeaders) {
		this.headerName = headerName;
		this.subHeaders = subHeaders;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public List<String> getSubHeaders() {
		return subHeaders;
	}

	public void setSubHeaders(List<String> subHeaders) {
		this.subHeaders = subHeaders;
	}

}
