package org.wso2.carbon.identity.xds.server.mgt;

import discovery.service.api.Event;
import discovery.service.api.ReceiveEventServiceGrpc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.carbon.identity.xds.common.constant.XDSConstants;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.ApplicationEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.CORSEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.ClaimEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.EmailTemplateEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.EventPublisherEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.IDPEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.KeyStoreEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.NotificationSenderXDSEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.OauthEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.RemoteFetchEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.SAMLEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.TemplateXDSEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.UserStoreXDSEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.WorkflowEventHandler;


/**
 * XDS service server implementation.
 */
public class XDSServiceServer extends ReceiveEventServiceGrpc.ReceiveEventServiceImplBase {
    private static final Log LOG = LogFactory.getLog(XDSServiceServer.class);

    @Override
    public void receiveEvent(discovery.service.api.Event.ReceivedEvent request,
                             io.grpc.stub.StreamObserver<discovery.service.api.Event.EventReceivedResponse>
                                     responseObserver) {

        boolean isError = false;
        LOG.info(String.format("Received event of type: %s, Operation: %s",
                request.getType(), request.getOperation()));
        try {
            startTenantFlow(request.getTenantDomain(), request.getUsername());
            switch (XDSConstants.EventType.valueOf(request.getType())) {
                case CLAIM:
                    new ClaimEventHandler().handleEvent(request);
                    break;
                case APPLICATION:
                    new ApplicationEventHandler().handleEvent(request);
                    break;
                case OAUTH:
                    new OauthEventHandler().handleEvent(request);
                    break;
                case SAML:
                    new SAMLEventHandler().handleEvent(request);
                    break;
                case CORS:
                    new CORSEventHandler().handleEvent(request);
                    break;
                case IDP:
                    new IDPEventHandler().handleEvent(request);
                    break;
                case EVENT_PUBLISHER:
                    new EventPublisherEventHandler().handleEvent(request);
                    break;
                case WORKFLOW:
                    new WorkflowEventHandler().handleEvent(request);
                    break;
                case KEYSTORE:
                    new KeyStoreEventHandler().handleEvent(request);
                    break;
                case REMOTE_FETCH:
                    new RemoteFetchEventHandler().handleEvent(request);
                    break;
                case EMAIL_TEMPLATE:
                    new EmailTemplateEventHandler().handleEvent(request);
                    break;
                case NOTIFICATION_SENDER:
                    new NotificationSenderXDSEventHandler().handleEvent(request);
                    break;
                case TEMPLATE:
                    new TemplateXDSEventHandler().handleEvent(request);
                    break;
                case USER_STORE:
                    new UserStoreXDSEventHandler().handleEvent(request);
                    break;
                default:
                    LOG.info("Unknown event type received: " + request.getType());
                    isError = true;
            }
        } catch (Exception e) {
            LOG.error("Error while handling event: " + request.getType(), e);
            isError = true;
        } finally {
            endTenantFlow();
            Event.EventReceivedResponse response =
                    Event.EventReceivedResponse.newBuilder().build().newBuilder().setUpdated(!isError).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public void startTenantFlow(String tenantDomain, String username) {

        int tenantId = IdentityTenantUtil.getTenantId(tenantDomain);
        PrivilegedCarbonContext.startTenantFlow();
        PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantDomain(tenantDomain);
        PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantId(tenantId);
        PrivilegedCarbonContext.getThreadLocalCarbonContext().setUsername(username);
    }

    public void endTenantFlow() {

        PrivilegedCarbonContext.endTenantFlow();
    }

}
