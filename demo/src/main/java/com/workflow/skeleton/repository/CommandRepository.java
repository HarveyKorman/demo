
package com.workflow.skeleton.repository;

import org.springframework.stereotype.Repository;

import com.workflow.skeleton.core.workflow.WorkflowDefine;

@Repository
public interface CommandRepository {
    
	WorkflowDefine insertCommand(String workflowName, Object yml);
}
