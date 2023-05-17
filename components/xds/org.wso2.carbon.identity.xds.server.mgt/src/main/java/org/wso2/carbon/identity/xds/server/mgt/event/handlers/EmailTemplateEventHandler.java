package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.email.mgt.EmailTemplateManager;
import org.wso2.carbon.email.mgt.EmailTemplateXDSOperationType;
import org.wso2.carbon.email.mgt.model.EmailTemplateXDSWrapper;
import org.wso2.carbon.identity.cors.mgt.core.exception.CORSManagementServiceException;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for Email Template related events.
 */
public class EmailTemplateEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        EmailTemplateXDSWrapper emailTemplateXDSWrapper = gson.fromJson(
                request.getValue(),
                EmailTemplateXDSWrapper.class);
        EmailTemplateManager emailTemplateManager = XDSServerDataHolder.getEmailTemplateManager();

        switch (EmailTemplateXDSOperationType.valueOf(request.getOperation())) {

            case DELETE_EMAIL_TEMPLATE:
                emailTemplateManager.deleteEmailTemplate(
                        emailTemplateXDSWrapper.getTemplateTypeName(),
                        emailTemplateXDSWrapper.getLocaleCode(),
                        emailTemplateXDSWrapper.getTenantDomain());
                break;
            case DELETE_EMAIL_TEMPLATE_TYPE:
                emailTemplateManager.deleteEmailTemplateType(
                        emailTemplateXDSWrapper.getDisplayName(),
                        emailTemplateXDSWrapper.getTenantDomain());
                break;
            case ADD_EMAIL_TEMPLATE:
                emailTemplateManager.addEmailTemplate(
                        emailTemplateXDSWrapper.getEmailTemplate(),
                        emailTemplateXDSWrapper.getTenantDomain());
                break;
            case ADD_EMAIL_TEMPLATE_TYPE:
                emailTemplateManager.addEmailTemplateType(
                        emailTemplateXDSWrapper.getTemplateTypeName(),
                        emailTemplateXDSWrapper.getTenantDomain());
                break;
            case ADD_DEFAULT_EMAIL_TEMPLATES:
                emailTemplateManager.addDefaultEmailTemplates(
                        emailTemplateXDSWrapper.getTenantDomain());
                break;
            default:
            throw new CORSManagementServiceException("Invalid operation type: " + request.getOperation(),
                    "XDS-60001");

        }
    }
}
