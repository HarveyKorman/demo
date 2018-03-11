package com.workflow.skeleton.core.workflow;

import java.io.FileInputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.Yaml;

import com.workflow.skeleton.form.CommandRequestForm;
import com.workflow.skeleton.repository.CommandRepository;

public class HubotCommandRun implements WorkflowRun {
	@Autowired
    private CommandRepository commandRepository;
	@Autowired
	private WorkflowExecutor workflowExecutor;

    private final String YAMLNAME = "application.yaml";
    
	@Override
	public void runWorkflow(CommandRequestForm commandRequestForm) {
        // read yml
        Object yamlfile = readYamlDefine();
        String workflowName = commandRequestForm.getComamndId(); //TODO plus random and userID
        
        // save yml
        WorkflowDefine workflowDefine = storeLocalWorkflowsWithoutSchedule(workflowName, yamlfile);
        
        submitWorkflow(workflowDefine);
        //TODO run workflow executor
        // runUntilDone
	}
	
	private WorkflowDefine storeLocalWorkflowsWithoutSchedule(String workflowName, Object yamlfile) {
		//TODO save workflowName and yml
		//TODO get workflow.attempId
		WorkflowDefine workflowDefine = this.commandRepository.insertCommand(workflowName, yamlfile);
		
		//TODO get workflow params by workflow.id
        return workflowDefine;
	}
	
	private void submitWorkflow(WorkflowDefine workflowDefine) {
		this.workflowExecutor.submitWorkTasks(workflowDefine);
	}
	
	private Object readYamlDefine() {
        try {
            URL url = this.getClass().getClassLoader().getResource(YAMLNAME);
            Yaml yaml = new Yaml();
            if (url != null) {
                Object obj = yaml.load(new FileInputStream(url.getFile()));
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
