package com.workflow.skeleton.entity;

public class WorkflowDefinitionEntity {
    private String workflowName;
    private Object yamlDefine;

    public String getWorkflowName() {
        return workflowName;
    }
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
    public Object getYamlDefine() {
        return yamlDefine;
    }
    public void setYamlDefine(Object yamlDefine) {
        this.yamlDefine = yamlDefine;
    }
}
