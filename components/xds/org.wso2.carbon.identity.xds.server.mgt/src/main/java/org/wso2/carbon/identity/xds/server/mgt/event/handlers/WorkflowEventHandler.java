package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.workflow.impl.WorkflowImplServiceImpl;
import org.wso2.carbon.identity.workflow.impl.WorkflowXDSOperationType;
import org.wso2.carbon.identity.workflow.impl.model.WorkflowXDSWrapper;

/**
 * Event handler for Workflow related events.
 */
public class WorkflowEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        WorkflowXDSWrapper workflowXDSWrapper = gson.fromJson(request.getValue(), WorkflowXDSWrapper.class);
        WorkflowImplServiceImpl workflowImplService = new WorkflowImplServiceImpl();

        switch (WorkflowXDSOperationType.valueOf(request.getOperation())) {
            case ADD_BPS_PROFILE:
                workflowImplService.addBPSProfile(workflowXDSWrapper.getBpsProfile(),
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId());
                break;
            case DELETE_HUMAN_TASK:
                workflowImplService.deleteHumanTask(workflowXDSWrapper.getWorkflowRequest());
                break;
            case REMOVE_BPS_PACKAGE:
                workflowImplService.removeBPSPackage(workflowXDSWrapper.getWorkflow());
                break;
            case REMOVE_BPS_PROFILE:
                workflowImplService.removeBPSProfile(workflowXDSWrapper.getProfileName());
                break;
            case UPDATE_BPS_PROFILE:
                workflowImplService.updateBPSProfile(workflowXDSWrapper.getBpsProfile(),
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId());
                break;
            case REMOVE_BPS_PROFILES:
                workflowImplService.removeBPSProfiles(
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId());
                break;
            default:
            throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
