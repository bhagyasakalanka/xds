package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.base.IdentityException;
import org.wso2.carbon.identity.user.store.configuration.UserStoreConfigService;
import org.wso2.carbon.identity.user.store.configuration.UserStoreXDSOperationType;
import org.wso2.carbon.identity.user.store.configuration.model.UserStoreXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;


/**
 * Event handler for UserStore related events.
 */
public class UserStoreXDSEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        UserStoreXDSWrapper userStoreXDSWrapper = gson.fromJson(request.getValue(), UserStoreXDSWrapper.class);
        UserStoreConfigService userStoreConfigService = XDSServerDataHolder.getUserStoreConfigService();

        switch (UserStoreXDSOperationType.valueOf(request.getOperation())) {
            case ADD_USER_STORE:
                userStoreConfigService.addUserStore(userStoreXDSWrapper.getUserStoreDTO());
                break;
            case DELETE_USER_STORE:
                userStoreConfigService.deleteUserStore(userStoreXDSWrapper.getDomain());
                break;
            case UPDATE_USER_STORE:
                userStoreConfigService.updateUserStore(
                        userStoreXDSWrapper.getUserStoreDTO(),
                        userStoreXDSWrapper.isStateChanged());
                break;
            case DELETE_USER_STORE_SET:
                userStoreConfigService.deleteUserStoreSet(
                        userStoreXDSWrapper.getDomains());
                break;
            case MODIFY_USER_STORE_STATE:
                userStoreConfigService.modifyUserStoreState(
                        userStoreXDSWrapper.getDomain(),
                        userStoreXDSWrapper.isDisabled(),
                        userStoreXDSWrapper.getRepositoryClass());
                break;
            case UPDATE_USER_STORE_BY_DOMAIN_NAME:
                userStoreConfigService.updateUserStoreByDomainName(
                        userStoreXDSWrapper.getPreviousDomainName(),
                        userStoreXDSWrapper.getUserStoreDTO());
                break;
            default:
                throw new IdentityException("Invalid operation type: " + request.getOperation());
        }
    }
}
