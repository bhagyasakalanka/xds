package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.template.mgt.TemplateManager;
import org.wso2.carbon.identity.template.mgt.TemplateXDSOperationType;
import org.wso2.carbon.identity.template.mgt.model.TemplateXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for Template related events.
 */
public class TemplateXDSEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        TemplateXDSWrapper templateXDSWrapper = gson.fromJson(request.getValue(), TemplateXDSWrapper.class);
        TemplateManager templateManager = XDSServerDataHolder.getTemplateManager();

        switch (TemplateXDSOperationType.valueOf(request.getOperation())) {
            case ADD_TEMPLATE:
            case ADD_TEMPLATE_USING_TEMPLATE_MGT_DAO:
                templateManager.addTemplate(templateXDSWrapper.getTemplate());
                break;
            case DELETE_TEMPLATE:
                templateManager.deleteTemplate(templateXDSWrapper.getTemplateName());
                break;
            case UPDATE_TEMPLATE:
                templateManager.updateTemplate(templateXDSWrapper.getTemplateName(), templateXDSWrapper.getTemplate());
                break;
            case DELETE_TEMPLATE_BY_ID:
                templateManager.deleteTemplateById(templateXDSWrapper.getTemplateId());
                break;
            case UPDATE_TEMPLATE_BY_ID:
                templateManager
                        .updateTemplateById(templateXDSWrapper.getTemplateId(), templateXDSWrapper.getTemplate());
                break;
            default:
                throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
