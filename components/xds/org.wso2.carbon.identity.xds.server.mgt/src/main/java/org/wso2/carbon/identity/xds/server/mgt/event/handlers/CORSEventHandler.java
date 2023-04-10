package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.cors.mgt.core.CORSManagementService;
import org.wso2.carbon.identity.cors.mgt.core.CorsXDSOperationType;
import org.wso2.carbon.identity.cors.mgt.core.exception.CORSManagementServiceException;
import org.wso2.carbon.identity.cors.mgt.core.model.CORSXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for CORS related events.
 */
public class CORSEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws CORSManagementServiceException {

        Gson gson = new Gson();
        CORSXDSWrapper corsXDSWrapper = gson.fromJson(request.getValue(), CORSXDSWrapper.class);
        CORSManagementService corsManagementService = XDSServerDataHolder.getCORSManagementService();

        switch (CorsXDSOperationType.valueOf(request.getOperation())) {
            case ADD_CORS_ORIGINS:
                corsManagementService.addCORSOrigins(
                        corsXDSWrapper.getApplicationId(),
                        corsXDSWrapper.getOrigins(),
                        corsXDSWrapper.getTenantDomain());
                break;
            case DELETE_CORS_ORIGINS:
                corsManagementService.deleteCORSOrigins(
                        corsXDSWrapper.getApplicationId(),
                        corsXDSWrapper.getOrigins(),
                        corsXDSWrapper.getTenantDomain());
                break;
            case SET_CORS_ORIGINS:
                corsManagementService.setCORSOrigins(
                        corsXDSWrapper.getApplicationId(),
                        corsXDSWrapper.getOrigins(),
                        corsXDSWrapper.getTenantDomain());
                break;
            case SET_CORS_CONFIGURATIONS:
                corsManagementService.setCORSConfiguration(
                        corsXDSWrapper.getCORSConfiguration(),
                        corsXDSWrapper.getTenantDomain());
                break;
            default:
                throw new CORSManagementServiceException("Invalid operation type: " + request.getOperation(),
                        "XDS-60001");

        }
    }
}
