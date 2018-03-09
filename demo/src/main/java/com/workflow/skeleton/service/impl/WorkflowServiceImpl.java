package com.workflow.skeleton.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflow.skeleton.form.CommandRequestForm;
import com.workflow.skeleton.repository.CommandRepository;
import com.workflow.skeleton.service.WorkflowService;

@Service
class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private CommandRepository commandRepository;

    @Override
    public void analyseCommmand(CommandRequestForm commandRequestForm) {
        //TODO analyse command 
        //TODO read yml
        //TODO save yml
        String yml = "";
        String workflowName = commandRequestForm.getComamndId(); //TODO plus random and userID
        //TODO save workflowName and yml
        this.commandRepository.insert(workflowName, yml);
        //TODO if save success
        //TODO run workflow executor
    }
}
