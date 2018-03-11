package com.workflow.skeleton.service.impl;

import org.springframework.stereotype.Service;

import com.workflow.skeleton.core.workflow.ExternalActionRun;
import com.workflow.skeleton.core.workflow.HubotCommandRun;
import com.workflow.skeleton.core.workflow.WorkflowRun;
import com.workflow.skeleton.form.CommandRequestForm;
import com.workflow.skeleton.service.WorkflowService;

@Service
class WorkflowServiceImpl implements WorkflowService {
    @Override
    public void analyseCommmand(CommandRequestForm commandRequestForm) {
        //TODO analyse command
    	WorkflowRun run = null;
    	if (commandRequestForm.getRequestType().equals("command")) {
    		run = new HubotCommandRun();
    		
    	} else if (commandRequestForm.getRequestType().equals("actionResponse")) {
    		run = new ExternalActionRun();
    	}
    	
    	if (run != null) {
    		run.runWorkflow(commandRequestForm);
    	}
    }
}
