
package com.workflow.skeleton.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository {
    
    int insertCommand(String workflowName, Object yml);
}
