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
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.IDPEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.OauthEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.RoleEventHandler;
import org.wso2.carbon.identity.xds.server.mgt.event.handlers.SAMLEventHandler;


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
        try {
            startTenantFlow(request.getTenantDomain(), request.getUsername());
            switch (XDSConstants.EventType.valueOf(request.getType())) {
                case ROLE:
                    new RoleEventHandler().handleEvent(request);
                    break;
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
