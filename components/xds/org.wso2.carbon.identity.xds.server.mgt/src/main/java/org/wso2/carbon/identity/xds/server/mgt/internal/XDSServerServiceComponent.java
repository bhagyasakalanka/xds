package org.wso2.carbon.identity.xds.server.mgt.internal;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.identity.cors.mgt.core.CORSManagementService;
import org.wso2.carbon.identity.oauth.OAuthAdminServiceImpl;
import org.wso2.carbon.identity.role.mgt.core.RoleManagementService;
import org.wso2.carbon.identity.sso.saml.SAMLSSOConfigServiceImpl;
import org.wso2.carbon.identity.xds.server.mgt.XDSServiceServer;
import org.wso2.carbon.idp.mgt.IdpManager;

import java.io.IOException;

/**
 * @scr.component name="identity.xds.server.component" immediate=true
 */
@Component(
        name = "identity.xds.server.component",
        immediate = true)
public class XDSServerServiceComponent {

    private static final Log LOG = LogFactory.getLog(XDSServerServiceComponent.class);
    private Server server;

    @Activate
    public void activate(ComponentContext context)  {
        // Create a new instance of your gRPC service implementation
        XDSServiceServer grpcServiceServer = new XDSServiceServer();
        int port = IdentityUtil.getProperty("XDS.Server.Port") != null ?
                Integer.parseInt(IdentityUtil.getProperty("XDS.Server.Port")) : 50051;
        LOG.info(String.format("starting grpc server on port: %d\n\n\n\n\n\n", port));
        // Start the gRPC server on port 50051
        try {
            server = NettyServerBuilder.forPort(port)
                    .addService(grpcServiceServer)
                    .build()
                    .start();
        } catch (IOException e) {
            LOG.info("starting failure grpc server\n\n\n\n\n\n", e);
        }
        LOG.info("started grpc server\n\n\n\n\n\n");
        // Register your service with the OSGi service registry
        context.getBundleContext().registerService(XDSServiceServer.class.getName(), grpcServiceServer, null);
    }

    @Deactivate
    public void deactivate(ComponentContext context) throws Exception {
        // Stop the gRPC server
        server.shutdown();
    }

    @Reference(
            name = "claim.metadata.management.service",
            service = org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetClaimMetadataManagementService"
    )
    protected void setClaimMetadataManagementService(
            ClaimMetadataManagementService claimMetadataManagementService) {

        LOG.debug("Receiving claim metadata management Service");
        XDSServerDataHolder.setClaimMetadataManagementService(claimMetadataManagementService);

    }

    protected void unsetClaimMetadataManagementService(
            ClaimMetadataManagementService claimMetadataManagementService) {

        LOG.debug("Unsetting Claim Metadata management Service");
        XDSServerDataHolder.setClaimMetadataManagementService(null);
    }

    @Reference(
            name = "role.management.service",
            service = org.wso2.carbon.identity.role.mgt.core.RoleManagementService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRoleManagementService"
    )
    protected void setRoleManagementService(
            RoleManagementService roleManagementService) {

        LOG.debug("Receiving Role management Service");
        XDSServerDataHolder.setRoleManagementService(roleManagementService);

    }

    protected void unsetRoleManagementService(
            RoleManagementService roleManagementService) {

        LOG.debug("Unsetting Role management Service");
        XDSServerDataHolder.setRoleManagementService(null);
    }

    @Reference(
            name = "oauth.admin.service",
            service = org.wso2.carbon.identity.oauth.OAuthAdminServiceImpl.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetOAuthAdminService"
    )
    protected void setOAuthAdminService(OAuthAdminServiceImpl oAuthAdminService) {

        LOG.debug("Receiving Oauth management Service");
        XDSServerDataHolder.setOAuthAdminService(oAuthAdminService);

    }

    protected void unsetOAuthAdminService(
            OAuthAdminServiceImpl oAuthAdminService) {

        LOG.debug("Unsetting Application management Service");
        XDSServerDataHolder.setOAuthAdminService(null);
    }

    @Reference(
            name = "application.management.service",
            service = org.wso2.carbon.identity.application.mgt.ApplicationManagementService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetApplicationManagementService"
    )
    protected void setApplicationManagementService(
            ApplicationManagementService applicationManagementService) {

        LOG.debug("Receiving Application management Service");
        XDSServerDataHolder.setApplicationManagementService(applicationManagementService);

    }

    protected void unsetApplicationManagementService(
            ApplicationManagementService applicationManagementService) {

        LOG.debug("Unsetting Application management Service");
        XDSServerDataHolder.setApplicationManagementService(null);
    }

    @Reference(
            name = "saml.sso.config.service",
            service = org.wso2.carbon.identity.sso.saml.SAMLSSOConfigServiceImpl.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetSamlSSOConfigService"
    )
    protected void setSamlSSOConfigService(
            SAMLSSOConfigServiceImpl samlSSOConfigService) {

        LOG.debug("Receiving Saml SSO Config Service");
        XDSServerDataHolder.setSamlSSOConfigService(samlSSOConfigService);

    }

    protected void unsetSamlSSOConfigService(
            SAMLSSOConfigServiceImpl samlssoConfigService) {

        LOG.debug("Unsetting Saml SSO config Service");
        XDSServerDataHolder.setSamlSSOConfigService(null);
    }

    @Reference(
            name = "cors.management.service",
            service = org.wso2.carbon.identity.cors.mgt.core.CORSManagementService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetCorsManagementService"
    )
    protected void setCorsManagementService(
            CORSManagementService corsManagementService) {

        LOG.debug("Receiving CORS Management Service");
        XDSServerDataHolder.setCORSManagementService(corsManagementService);

    }

    protected void unsetCorsManagementService(
            CORSManagementService corsManagementService) {

        LOG.debug("Unsetting CORS Management Service");
        XDSServerDataHolder.setCORSManagementService(null);
    }

    @Reference(
            name = "idp.management.service",
            service = org.wso2.carbon.idp.mgt.IdpManager.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetIdpManagementService"
    )
    protected void setIdpManagementService(
            IdpManager identityProviderManager) {

        LOG.debug("Receiving Identity Provider Manager.");
        XDSServerDataHolder.setIdentityProviderManager(identityProviderManager);

    }

    protected void unsetIdpManagementService(
            IdpManager identityProviderManager) {

        LOG.debug("Unsetting Identity Provider Manager.");
        XDSServerDataHolder.setIdentityProviderManager(null);
    }
}
