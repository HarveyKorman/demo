package com.workflow.skeleton.service.impl;

import java.io.FileInputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import com.workflow.skeleton.form.CommandRequestForm;
import com.workflow.skeleton.repository.CommandRepository;
import com.workflow.skeleton.service.WorkflowService;

@Service
class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private CommandRepository commandRepository;

    private final String YAMLNAME = "application.yaml";
    
    @Override
    public void analyseCommmand(CommandRequestForm commandRequestForm) {
        //TODO analyse command 
        // read yml
        Object yamlfile = readYamlDefine();
        
        // save yml
        String workflowName = commandRequestForm.getComamndId(); //TODO plus random and userID
        //TODO save workflowName and yml
        this.commandRepository.insertCommand(workflowName, yamlfile);
        //TODO if save success
        //TODO run workflow executor
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
