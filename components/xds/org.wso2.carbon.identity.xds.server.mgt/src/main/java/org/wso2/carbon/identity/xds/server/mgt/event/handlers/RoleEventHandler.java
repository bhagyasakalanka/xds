package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import org.wso2.carbon.identity.role.mgt.core.IdentityRoleManagementException;
import org.wso2.carbon.identity.role.mgt.core.RoleXDSOperationType;
import org.wso2.carbon.identity.role.mgt.core.modal.RoleXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;


/**
 * Event handler for role events.
 */
public class RoleEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(discovery.service.api.Event.ReceivedEvent request) throws IdentityRoleManagementException {

        Gson gson = new Gson();
        RoleXDSWrapper roleXDSWrapper = gson.fromJson(request.getValue(), RoleXDSWrapper.class);
        switch (RoleXDSOperationType.valueOf(request.getOperation())) {
            case CREATE:
                XDSServerDataHolder.getRoleManagementService().addRole(
                        roleXDSWrapper.getNewRoleName(),
                        roleXDSWrapper.getNewUserIDList(),
                        roleXDSWrapper.getNewGroupIDList(),
                        roleXDSWrapper.getNewPermissions(),
                        request.getTenantDomain(),
                        roleXDSWrapper.getRoleID());
                break;
            case DELETE:
                XDSServerDataHolder.getRoleManagementService().deleteRole(
                        roleXDSWrapper.getRoleID(),
                        request.getTenantDomain());
                break;
            case UPDATE_ROLE_NAME:
                XDSServerDataHolder.getRoleManagementService().updateRoleName(
                        roleXDSWrapper.getRoleID(),
                        roleXDSWrapper.getNewRoleName(),
                        request.getTenantDomain());
                break;
            case UPDATE_ROLE_USER_LIST:
                XDSServerDataHolder.getRoleManagementService().updateUserListOfRole(
                        roleXDSWrapper.getRoleID(),
                        roleXDSWrapper.getNewUserIDList(),
                        roleXDSWrapper.getDeletedUserIDList(),
                        request.getTenantDomain());
                break;
            case UPDATE_ROLE_GROUP_LIST:
                XDSServerDataHolder.getRoleManagementService().updateGroupListOfRole(
                        roleXDSWrapper.getRoleID(),
                        roleXDSWrapper.getNewGroupIDList(),
                        roleXDSWrapper.getDeletedGroupIDList(),
                        request.getTenantDomain());
                break;
            case UPDATE_ROLE_PERMISSION_LIST:
                XDSServerDataHolder.getRoleManagementService().setPermissionsForRole(
                        roleXDSWrapper.getRoleID(),
                        roleXDSWrapper.getNewPermissions(), request.getTenantDomain());
                break;
            default:
                throw new IdentityRoleManagementException("Invalid operation type: " + request.getOperation());
        }
    }
}
