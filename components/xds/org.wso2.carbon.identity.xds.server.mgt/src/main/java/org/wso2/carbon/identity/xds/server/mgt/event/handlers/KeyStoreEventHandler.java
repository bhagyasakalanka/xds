package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;
import org.wso2.carbon.security.KeyStoreXDSOperationType;
import org.wso2.carbon.security.keystore.KeyStoreManagementService;
import org.wso2.carbon.security.model.KeyStoreXDSWrapper;

/**
 * KeyStoreEventHandler class.
 */
public class KeyStoreEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        KeyStoreXDSWrapper keyStoreXDSWrapper = gson.fromJson(request.getValue(), KeyStoreXDSWrapper.class);
        KeyStoreManagementService keyStoreManagementService = XDSServerDataHolder.getKeyStoreManagementService();

        switch (KeyStoreXDSOperationType.valueOf(request.getOperation())) {
            case ADD_CERTIFICATE:
                keyStoreManagementService.addCertificate(
                        keyStoreXDSWrapper.getTenantDomain(),
                        keyStoreXDSWrapper.getAlias(),
                        keyStoreXDSWrapper.getCertificate());
                break;
            case DELETE_CERTIFICATE:
                keyStoreManagementService.deleteCertificate(
                        keyStoreXDSWrapper.getTenantDomain(),
                        keyStoreXDSWrapper.getAlias());
                break;
            default:
            throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
