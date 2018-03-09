
package com.workflow.skeleton.repository;


public interface CommandRepository {
    
    int insert(String workflowName, String yml);
}
