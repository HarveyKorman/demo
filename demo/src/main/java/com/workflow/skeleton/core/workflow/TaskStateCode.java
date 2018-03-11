package com.workflow.skeleton.core.workflow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum TaskStateCode
{
    BLOCKED(0),
    READY(1),
    RETRY_WAITING(2),
    GROUP_RETRY_WAITING(3),
    RUNNING(4),
    PLANNED(5),
    GROUP_ERROR(6),
    SUCCESS(7),
    ERROR(8),
    CANCELED(9);

    public static final short BLOCKED_CODE = (short) 0;
    public static final short READY_CODE = (short) 1;
    public static final short RETRY_WAITING_CODE = (short) 2;
    public static final short GROUP_RETRY_WAITING_CODE = (short) 3;
    public static final short RUNNING_CODE = (short) 4;
    public static final short PLANNED_CODE = (short) 5;
    public static final short GROUP_RETRY_ERROR_CODE = (short) 6;
    public static final short SUCCESS_CODE = (short) 7;
    public static final short ERROR_CODE = (short) 8;
    public static final short CANCELED_CODE = (short) 9;
    private boolean error;

    // state:
    //   retry_blocked -> blocked: every time full search
    //     1. search retry_blocked tasks
    //     2. for each tasks:
    //       a. if retry_at is past, go to ready
    //
    //   blocked -> ready: search if parent or upstream is recently changed
    //     1. search recently changed tasks
    //     2. for each task:
    //       a. lock it
    //       b. check children of it. if it founds runnable task, set the state to ready
    //     3. for each task:
    //       a. lock parent
    //       b. check siblings of it (tasks that have the same parent_id). if it found runnable task, set the state to ready
    //
    //   ready -> running / error
    //     1. lock a ready task
    //     2. run it and set its state to running
    //       a. if it fails, set state to error
    //
    //   running -> planned / error
    //     1. api callback
    //
    //   planned -> success / error: search if one of children is recently changed
    //     1. search recently changed tasks
    //     2. for each task:
    //       a. lock parent
    //       b. check children of it. if it found all of them are done, set the state to success or error
    //
    //   blocked (including retry blocked), planned, done

    //public static TaskStateCode[] waitingDependencyStates()
    //{
    //    return new TaskStateCode[] {
    //        BLOCKED,
    //        RETRY_WAITING, GROUP_RETRY_WAITING,
    //    };
    //}

    public static TaskStateCode[] canRunChildrenStates()
    {
        return new TaskStateCode[] {
            PLANNED, SUCCESS,
        };
    }

    public static TaskStateCode[] canRunDownstreamStates()
    {
        return new TaskStateCode[] {
            SUCCESS,
        };
    }

    public static TaskStateCode[] doneStates()
    {
        return new TaskStateCode[] {
            SUCCESS,
            GROUP_ERROR,
            ERROR,
            CANCELED,
        };
    }

    public static TaskStateCode[] notDoneStates()
    {
        return new TaskStateCode[] {
            BLOCKED,
            READY,
            RETRY_WAITING,
            GROUP_RETRY_WAITING,
            RUNNING,
            PLANNED,
        };
    }

    public static TaskStateCode[] progressingStates()
    {
        return new TaskStateCode[] {
            READY,
            RETRY_WAITING,
            GROUP_RETRY_WAITING,
            RUNNING,
            PLANNED,
        };
    }


    public static TaskStateCode[] errorStates()
    {
        return new TaskStateCode[] {
            ERROR, GROUP_ERROR
        };
    }

    @JsonCreator
    public static TaskStateCode fromString(String name)
    {
        switch(name) {
        case "blocked":
            return BLOCKED;
        case "ready":
            return READY;
        case "retry_waiting":
            return RETRY_WAITING;
        case "group_retry_waiting":
            return GROUP_RETRY_WAITING;
        case "running":
            return RUNNING;
        case "planned":
            return PLANNED;
        case "group_error":
            return GROUP_ERROR;
        case "success":
            return SUCCESS;
        case "error":
            return ERROR;
        case "canceled":
            return CANCELED;
        default:
            throw new IllegalStateException("Unknown task status name: " + name);
        }
    }

    public static TaskStateCode of(int code)
    {
        switch(code) {
        case 0:
            return BLOCKED;
        case 1:
            return READY;
        case 2:
            return RETRY_WAITING;
        case 3:
            return GROUP_RETRY_WAITING;
        case 4:
            return RUNNING;
        case 5:
            return PLANNED;
        case 6:
            return GROUP_ERROR;
        case 7:
            return SUCCESS;
        case 8:
            return ERROR;
        case 9:
            return CANCELED;
        default:
            throw new IllegalStateException("Unknown task status code");
        }
    }

    private final short code;

    private TaskStateCode(int code)
    {
        this.code = (short) code;
    }

    public short get()
    {
        return code;
    }

    @JsonValue
    public String toString()
    {
        return name().toLowerCase();
    }

    public boolean isError()
    {
        return Stream.of(errorStates()).anyMatch(errorState -> errorState == this);
    }
}
