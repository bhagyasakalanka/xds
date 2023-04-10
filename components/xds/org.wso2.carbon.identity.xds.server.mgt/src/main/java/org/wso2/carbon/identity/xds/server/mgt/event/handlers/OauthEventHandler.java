package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.oauth.IdentityOAuthAdminException;
import org.wso2.carbon.identity.oauth.OAuthAdminServiceImpl;
import org.wso2.carbon.identity.oauth.OAuthXDSOperationType;
import org.wso2.carbon.identity.oauth.OauthXDSWrapper;
import org.wso2.carbon.identity.oauth.dto.OAuthConsumerAppDTO;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for Oauth events.
 */
public class OauthEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws IdentityOAuthAdminException {

        Gson gson = new Gson();
        OauthXDSWrapper oauthXDSWrapper = gson.fromJson(request.getValue(), OauthXDSWrapper.class);
        OAuthAdminServiceImpl oauthAdminService = XDSServerDataHolder.getOAuthAdminService();

        switch (OAuthXDSOperationType.valueOf(request.getOperation())) {
            case UPDATE_AND_RETRIEVE_OAUTH_SECRET_KEY:
                oauthAdminService.updateAndRetrieveOauthSecretKey(
                        oauthXDSWrapper.getConsumerKey(),
                        oauthXDSWrapper.getSecretKey());
                break;
            case REGISTER_AND_RETRIEVE_OAUTH_APPLICATION_DATA:
                OAuthConsumerAppDTO application = oauthXDSWrapper.getoAuthConsumerAppDTO();
                application.setOauthConsumerKey(oauthXDSWrapper.getConsumerKey());
                application.setOauthConsumerSecret(oauthXDSWrapper.getSecretKey());
                oauthAdminService.registerAndRetrieveOAuthApplicationData(application);
                break;
            case UPDATE_CONSUMER_APPLICATION:
                oauthAdminService.updateConsumerApplication(oauthXDSWrapper.getoAuthConsumerAppDTO());
                break;
            case UPDATE_APPROVE_ALWAYS_FOR_APP_CONSENT_BY_RESOURCE_OWNER:
                oauthAdminService.updateApproveAlwaysForAppConsentByResourceOwner(
                        oauthXDSWrapper.getAppName(),
                        oauthXDSWrapper.getState());
                break;
            case UPDATE_SCOPE:
                oauthAdminService.updateScope(
                        oauthXDSWrapper.getScope(),
                        oauthXDSWrapper.getClaims(),
                        oauthXDSWrapper.getDeleteClaims());
                break;
            case ADD_SCOPE:
                oauthAdminService.addScope(oauthXDSWrapper.getScope(), oauthXDSWrapper.getClaims());
                break;
            case DELETE_SCOPE:
                oauthAdminService.deleteScope(oauthXDSWrapper.getScope());
                break;
            case UPDATE_SCOPE_DTO:
                oauthAdminService.updateScope(oauthXDSWrapper.getScopeDTOs());
                break;
            case ADD_SCOPE_DTO:
                oauthAdminService.addScope(oauthXDSWrapper.getScopeDTOs());
                break;
            case REMOVE_ALL_OAUTH_APPLICATION_DATA:
                oauthAdminService.removeAllOAuthApplicationData(
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId()
                );
                break;
            case REGISTER_OAUTH_CONSUMER:
                oauthAdminService.registerOAuthConsumer();
                break;
            case REVOKE_AUTHZ_FOR_APPS_BY_RESOURCE_OWNER:
                oauthAdminService.revokeAuthzForAppsByResourceOwner(oauthXDSWrapper.getoAuthRevocationRequestDTO());
                break;
            case REVOKE_ISSUED_TOKENS_BY_APPLICATION:
                oauthAdminService.revokeIssuedTokensByApplication(oauthXDSWrapper.getoAuthAppRevocationRequestDTO());
                break;
            case UPDATE_CONSUMER_APP_STATE:
                oauthAdminService.updateConsumerAppState(oauthXDSWrapper.getConsumerKey(), oauthXDSWrapper.getState());
                break;
            case REMOVE_OAUTH_APPLICATION_DATA:
                oauthAdminService.removeOAuthApplicationData(oauthXDSWrapper.getConsumerKey());
                break;
            default:
                throw new IdentityOAuthAdminException("Invalid operation type: " + request.getOperation());
        }
    }
}
