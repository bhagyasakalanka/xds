package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

/**
 * GRPC event handler interface.
 */
public interface XDSEventHandler {

    /**
     * Handle the event.
     * @param request This is the request received from the Xds server.
     * @throws Exception If an error occurred while handling the event.
     */
    void handleEvent(discovery.service.api.Event.ReceivedEvent request) throws Exception;
}
