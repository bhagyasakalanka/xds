package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.application.common.IdentityApplicationManagementException;
import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.application.mgt.ApplicationXDSOperationType;
import org.wso2.carbon.identity.application.mgt.model.ApplicationXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for application events.
 */
public class ApplicationEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws IdentityApplicationManagementException {

        Gson gson = new Gson();
        ApplicationXDSWrapper applicationXDSWrapper = gson.fromJson(request.getValue(), ApplicationXDSWrapper.class);
        ApplicationManagementService applicationManagementService = XDSServerDataHolder
                .getApplicationManagementService();
        switch (ApplicationXDSOperationType.valueOf(request.getOperation())) {
            case ADD_APPLICATION:
                applicationManagementService.addApplication(
                        applicationXDSWrapper.getServiceProvider(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername());
                break;
            case CREATE_APPLICATION:
                applicationManagementService.createApplication(
                        applicationXDSWrapper.getServiceProvider(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername());
                break;
            case CREATE_APPLICATION_WITH_TEMPLATE:
                applicationManagementService.createApplicationWithTemplate(
                        applicationXDSWrapper.getServiceProvider(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername(),
                        applicationXDSWrapper.getTemplateName());
                break;
            case UPDATE_APPLICATION:
                applicationManagementService.updateApplication(
                        applicationXDSWrapper.getServiceProvider(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername());

                break;
            case DELETE_APPLICATION:
                applicationManagementService.deleteApplication(
                        applicationXDSWrapper.getApplicationName(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername());
                break;
            case DELETE_APPLICATIONS:
                applicationManagementService.deleteApplications(
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId());

                break;
            case CREATE_APPLICATION_TEMPLATE:
                applicationManagementService.createApplicationTemplate(
                        applicationXDSWrapper.getSpTemplate(),
                        applicationXDSWrapper.getTenantDomain());
                break;
            case UPDATE_APPLICATION_TEMPLATE:
                applicationManagementService.updateApplicationTemplate(
                        applicationXDSWrapper.getTemplateName(),
                        applicationXDSWrapper.getSpTemplate(),
                        applicationXDSWrapper.getTenantDomain());
                break;
            case DELETE_APPLICATION_TEMPLATE:
                applicationManagementService.deleteApplicationTemplate(
                        applicationXDSWrapper.getTemplateName(),
                        applicationXDSWrapper.getTenantDomain());
                break;
            case UPDATE_APPLICATION_BY_RESOURCE_ID:
                applicationManagementService.updateApplicationByResourceId(
                        applicationXDSWrapper.getResourceId(),
                        applicationXDSWrapper.getServiceProvider(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername());
                break;
            case CREATE_APPLICATION_TEMPLATE_FROM_SP:
                applicationManagementService.createApplicationTemplateFromSP(
                        applicationXDSWrapper.getServiceProvider(),
                        applicationXDSWrapper.getSpTemplate(),
                        applicationXDSWrapper.getTenantDomain());
                break;
            case DELETE_APPLICATION_BY_RESOURCE_ID:
                applicationManagementService.deleteApplicationByResourceId(
                        applicationXDSWrapper.getResourceId(),
                        applicationXDSWrapper.getTenantDomain(),
                        applicationXDSWrapper.getUsername());
                break;
            default:
                throw new IdentityApplicationManagementException("Invalid operation type: " + request.getOperation());
        }
    }
}
