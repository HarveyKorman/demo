package com.workflow.skeleton.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.skeleton.form.CommandRequestForm;
import com.workflow.skeleton.service.WorkflowService;

@RestController()
@RequestMapping("/workflow")
public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;
    
    @RequestMapping(value="/push/command", method= RequestMethod.POST)
    public void pushCommmand(@RequestBody @Valid CommandRequestForm commandRequestForm,
            Errors errors) {	  
//        if (errors.hasErrors()) {
//            throw new BusinessFailureException(errors);
//        }
        
        this.workflowService.analyseCommmand(commandRequestForm);

//        if (errors.hasErrors()) {
//            throw new BusinessFailureException(errors);
//        }
        
    }
}
