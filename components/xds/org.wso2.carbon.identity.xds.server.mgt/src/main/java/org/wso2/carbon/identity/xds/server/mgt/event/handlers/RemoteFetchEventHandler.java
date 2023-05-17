package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchConfigurationService;
import org.wso2.carbon.identity.remotefetch.core.RemoteFetchXDSOperationType;
import org.wso2.carbon.identity.remotefetch.core.model.RemoteFetchXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * RemoteFetchEventHandler class.
 */
public class RemoteFetchEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        RemoteFetchXDSWrapper remoteFetchXDSWrapper = gson.fromJson(request.getValue(), RemoteFetchXDSWrapper.class);
        RemoteFetchConfigurationService remoteFetchConfigurationService = XDSServerDataHolder
                .getRemoteFetchConfigurationService();

        switch (RemoteFetchXDSOperationType.valueOf(request.getOperation())) {
            case ADD_REMOTE_FETCH_CONFIGURATION:
                remoteFetchConfigurationService.addRemoteFetchConfiguration(
                        remoteFetchXDSWrapper.getRemoteFetchConfiguration());
                break;
            case DELETE_REMOTE_FETCH_CONFIGURATION:
                remoteFetchConfigurationService.deleteRemoteFetchConfiguration(
                        remoteFetchXDSWrapper.getFetchConfigurationId());
                break;
            case UPDATE_REMOTE_FETCH_CONFIGURATION:
                remoteFetchConfigurationService.updateRemoteFetchConfiguration(
                        remoteFetchXDSWrapper.getRemoteFetchConfiguration());
                break;
            default:
                throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
