package com.workflow.skeleton.core.workflow;

import java.util.Optional;

public abstract class Task
{
    public abstract Optional<Long> getParentId();

    public abstract String getFullName();

    public abstract TaskType getTaskType();

    public abstract TaskStateCode getState();

}
