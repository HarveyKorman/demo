package com.workflow.skeleton.form;

public class CommandRequestForm {
    private String comamndId;
    private String token;
    private String requestType;
    private String requestURL;
    
    
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getComamndId() {
        return comamndId;
    }
    public void setComamndId(String comamndId) {
        this.comamndId = comamndId;
    }
    public String getRequestURL() {
        return requestURL;
    }
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }    
}
