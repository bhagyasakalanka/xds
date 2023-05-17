package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import discovery.service.api.Event;
import org.wso2.carbon.identity.notification.sender.tenant.config.NotificationSenderManagementService;
import org.wso2.carbon.identity.notification.sender.tenant.config.NotificationSenderXDSOperationType;
import org.wso2.carbon.identity.notification.sender.tenant.config.model.NotificationSenderXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;
import org.wso2.carbon.idp.mgt.IdentityProviderManagementException;

/**
 * Event handler for Email Template related events.
 */
public class NotificationSenderXDSEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(Event.ReceivedEvent request) throws Exception {

        Gson gson = new Gson();
        NotificationSenderXDSWrapper notificationSenderXDSWrapper  = gson.fromJson(
                request.getValue(),
                NotificationSenderXDSWrapper.class);
        NotificationSenderManagementService notificationSenderManagementService =
                XDSServerDataHolder.getNotificationSenderManagementService();

        switch (NotificationSenderXDSOperationType.valueOf(request.getOperation())) {
            case ADD_SMS_SENDER:
                notificationSenderManagementService.addSMSSender(
                        notificationSenderXDSWrapper.getSMSSender());
                break;
            case ADD_EMAIL_SENDER:
                notificationSenderManagementService.addEmailSender(
                        notificationSenderXDSWrapper.getEmailSender());
                break;
            case UPDATE_SMS_SENDER:
                notificationSenderManagementService.updateSMSSender(
                        notificationSenderXDSWrapper.getSMSSender());
                break;
            case UPDATE_EMAIL_SENDER:
                notificationSenderManagementService.updateEmailSender(
                        notificationSenderXDSWrapper.getEmailSender());
                break;
            case DELETE_NOTIFICATION_SENDER:
                notificationSenderManagementService.deleteNotificationSender(
                        notificationSenderXDSWrapper.getSenderName());
                break;
            default:
                throw new IdentityProviderManagementException("Invalid operation type: " + request.getOperation());
        }
    }
}
