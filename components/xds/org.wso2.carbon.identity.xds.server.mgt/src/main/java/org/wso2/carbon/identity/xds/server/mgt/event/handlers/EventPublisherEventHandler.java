package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import discovery.service.api.Event;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.event.publisher.core.EventPublisherService;
import org.wso2.carbon.event.publisher.core.EventPublisherXDSOperationType;
import org.wso2.carbon.event.publisher.core.config.OutputMapping;
import org.wso2.carbon.event.publisher.core.config.OutputMappingInstanceCreator;
import org.wso2.carbon.event.publisher.core.model.EventPublisherXDSWrapper;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for event publisher events.
 */
public class EventPublisherEventHandler implements XDSEventHandler {
    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(OutputMapping.class, new OutputMappingInstanceCreator());
        Gson gson = builder.create();
        EventPublisherXDSWrapper eventPublisherXDSWrapper = gson.fromJson(request.getValue(),
                EventPublisherXDSWrapper.class);
        EventPublisherService eventPublisherService = XDSServerDataHolder.getEventPublisherService();

        switch (EventPublisherXDSOperationType.valueOf(request.getOperation())) {
            case SET_TRACE_ENABLED:
                eventPublisherService.setTraceEnabled(
                        eventPublisherXDSWrapper.getEventPublisherName(),
                        eventPublisherXDSWrapper.getTraceEnabled());
                break;
            case SET_PROCESS_ENABLED:
                eventPublisherService.setProcessEnabled(
                        eventPublisherXDSWrapper.getEventPublisherName(),
                        eventPublisherXDSWrapper.getProcessEnabled());
                break;
            case SET_STATISTICS_ENABLED:
                eventPublisherService.setStatisticsEnabled(
                        eventPublisherXDSWrapper.getEventPublisherName(),
                        eventPublisherXDSWrapper.getStatisticsEnabled());
                break;
            case ADD_EVENT_PUBLISHER_CONFIGURATION:
                eventPublisherService.addEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getEventPublisherConfiguration());
                break;
            case DEPLOY_EVENT_PUBLISHER_CONFIGURATION:
                eventPublisherService.deployEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getEventPublisherConfiguration());
                break;
            case ADD_EVENT_PUBLISHER_CONFIGURATION_FILE:
                eventPublisherService.addEventPublisherConfigurationFile(
                        eventPublisherXDSWrapper.getEventPublisherConfigurationFile(),
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId());
                break;
            case UNDEPLOY_ACTIVE_EVENT_PUBLISHER_CONFIGURATION:
                eventPublisherService.undeployActiveEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getEventPublisherName());
                break;
            case REMOVE_EVENT_PUBLISHER_CONFIGURATION_FILE:
                eventPublisherService.removeEventPublisherConfigurationFile(eventPublisherXDSWrapper.getFileName(),
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId());
                break;
            case DEPLOY_EVENT_PUBLISHER_CONFIGURATION_WITH_XML:
                eventPublisherService.deployEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getEventPublisherConfigXml());
                break;
            case UNDEPLOY_INACTIVE_EVENT_PUBLISHER_CONFIGURATION:
                eventPublisherService.undeployInactiveEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getFileName());
                break;
            case EDIT_ACTIVE_EVENT_PUBLISHER_CONFIGURATION:
                eventPublisherService.editActiveEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getEventPublisherConfigurationName(),
                        eventPublisherXDSWrapper.getEventPublisherName());
                break;
            case EDIT_INACTIVE_EVENT_PUBLISHER_CONFIGURATION:
                eventPublisherService.editInactiveEventPublisherConfiguration(
                        eventPublisherXDSWrapper.getEventPublisherConfigurationName(),
                        eventPublisherXDSWrapper.getEventPublisherName());
                break;
            default:
            throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
