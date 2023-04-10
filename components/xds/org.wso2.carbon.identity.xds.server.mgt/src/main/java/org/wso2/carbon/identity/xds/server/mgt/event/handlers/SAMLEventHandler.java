package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.sso.saml.SAMLSSOConfigServiceImpl;
import org.wso2.carbon.identity.sso.saml.SAMLXDSOperationType;
import org.wso2.carbon.identity.sso.saml.model.SAMLXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for SAML related events.
 */
public class SAMLEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws IdentityException {

        Gson gson = new Gson();
        SAMLXDSWrapper samlxdsWrapper = gson.fromJson(request.getValue(), SAMLXDSWrapper.class);
        SAMLSSOConfigServiceImpl samlSSOConfigService = XDSServerDataHolder.getSamlSSOConfigService();

        switch (SAMLXDSOperationType.valueOf(request.getOperation())) {
            case ADD_RP_SERVICE_PROVIDER:
                samlSSOConfigService.addRPServiceProvider(samlxdsWrapper.getSsoServiceProviderDTO());
                break;
            case CREATE_SERVICE_PROVIDER:
                    samlSSOConfigService.createServiceProvider(samlxdsWrapper.getSsoServiceProviderDTO());
                break;
            case UPLOAD_RP_SERVICE_PROVIDER:
                samlSSOConfigService.uploadRPServiceProvider(samlxdsWrapper.getMetadata());
                break;
            case CREATE_SERVICE_PROVIDER_WITH_METADATA_URL:
                samlSSOConfigService.createServiceProviderWithMetadataURL(samlxdsWrapper.getMetadataUrl());
                break;
            case REMOVE_SERVICE_PROVIDER:
                samlSSOConfigService.removeServiceProvider(samlxdsWrapper.getIssuer());
                break;
            default:
               throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
