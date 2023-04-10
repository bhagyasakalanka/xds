package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;
import org.wso2.carbon.idp.mgt.IdentityProviderManagementException;
import org.wso2.carbon.idp.mgt.IdentityProviderManager;
import org.wso2.carbon.idp.mgt.IdpXDSOperationType;
import org.wso2.carbon.idp.mgt.model.IdpXDSWrapper;

/**
 * Event handler for IDP events.
 */
public class IDPEventHandler implements XDSEventHandler {
    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        IdpXDSWrapper idpXDSWrapper = gson.fromJson(request.getValue(), IdpXDSWrapper.class);
        IdentityProviderManager identityProviderManager = (IdentityProviderManager) XDSServerDataHolder
                .getIdentityProviderManager();
        switch (IdpXDSOperationType.valueOf(request.getOperation())) {

            case ADD_RESIDENT_IDP:
                identityProviderManager.addResidentIdP(
                        idpXDSWrapper.getIdentityProvider(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case UPDATE_RESIDENT_IDP:
                identityProviderManager.updateResidentIdP(
                        idpXDSWrapper.getIdentityProvider(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case ADD_IDP_WITH_RESOURCE_ID:
                identityProviderManager.addIdPWithResourceId(
                        idpXDSWrapper.getIdentityProvider(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case UPDATE_IDP_BY_RESOURCE_ID:
                identityProviderManager.updateIdPByResourceId(
                        idpXDSWrapper.getResourceId(),
                        idpXDSWrapper.getIdentityProvider(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case DELETE_IDP_BY_RESOURCE_ID:
                identityProviderManager.deleteIdPByResourceId(
                        idpXDSWrapper.getResourceId(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case FORCE_DELETE_IDP_BY_RESOURCE_ID:
                identityProviderManager.forceDeleteIdpByResourceId(
                        idpXDSWrapper.getResourceId(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case UPDATE_IDP:
                identityProviderManager.updateIdP(
                        idpXDSWrapper.getIdpName(),
                        idpXDSWrapper.getIdentityProvider(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case DELETE_IDP:
                identityProviderManager.deleteIdP(
                        idpXDSWrapper.getIdpName(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case FORCE_DELETE_IDP:
                identityProviderManager.forceDeleteIdp(
                        idpXDSWrapper.getIdpName(),
                        idpXDSWrapper.getTenantDomain());
                break;
            case DELETE_IDPS:
                identityProviderManager.deleteIdPs(
                        idpXDSWrapper.getTenantDomain());
                break;
            default:
                throw new IdentityProviderManagementException("Invalid operation type: " + request.getOperation());
        }
    }
}
