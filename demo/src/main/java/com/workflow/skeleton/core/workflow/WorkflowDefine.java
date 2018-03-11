package com.workflow.skeleton.core.workflow;

public class WorkflowDefine {
	private Long attempId;
	private String state;
	private String[] actions;
	private String pri;
	private String role;
	
	public Long getAttempId() {
		return attempId;
	}
	public void setAttempId(Long attempId) {
		this.attempId = attempId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String[] getActions() {
		return actions;
	}
	public void setActions(String[] actions) {
		this.actions = actions;
	}
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
