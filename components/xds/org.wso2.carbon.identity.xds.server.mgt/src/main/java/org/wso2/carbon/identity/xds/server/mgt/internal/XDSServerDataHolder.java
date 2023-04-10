package org.wso2.carbon.identity.xds.server.mgt.internal;

import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService;
import org.wso2.carbon.identity.cors.mgt.core.CORSManagementService;
import org.wso2.carbon.identity.oauth.OAuthAdminServiceImpl;
import org.wso2.carbon.identity.role.mgt.core.RoleManagementService;
import org.wso2.carbon.identity.sso.saml.SAMLSSOConfigServiceImpl;
import org.wso2.carbon.idp.mgt.IdpManager;

/**
 * Data holder for GRPC component.
 */
public class XDSServerDataHolder {

    private static ClaimMetadataManagementService claimMetadataManagementService;
    private static RoleManagementService roleManagementService;
    private static ApplicationManagementService applicationManagementService;
    private static OAuthAdminServiceImpl oAuthAdminService;
    private static SAMLSSOConfigServiceImpl samlSSOConfigService;
    private static CORSManagementService corsManagementService;
    private static IdpManager identityProviderManager;

    public static ClaimMetadataManagementService getClaimMetadataManagementService() {
        return claimMetadataManagementService;
    }

    public static void setClaimMetadataManagementService(
            ClaimMetadataManagementService claimMetadataManagementService) {
        XDSServerDataHolder.claimMetadataManagementService = claimMetadataManagementService;
    }

    public static RoleManagementService getRoleManagementService() {
        return roleManagementService;
    }

    public static void setRoleManagementService(
            RoleManagementService roleManagementService) {
        XDSServerDataHolder.roleManagementService = roleManagementService;
    }

    public static ApplicationManagementService getApplicationManagementService() {
        return applicationManagementService;
    }

    public static void setApplicationManagementService(ApplicationManagementService applicationManagementService) {
        XDSServerDataHolder.applicationManagementService = applicationManagementService;
    }

    public static OAuthAdminServiceImpl getOAuthAdminService() {
        return oAuthAdminService;
    }

    public static void setOAuthAdminService(OAuthAdminServiceImpl oAuthAdminService) {
        XDSServerDataHolder.oAuthAdminService = oAuthAdminService;
    }

    public static SAMLSSOConfigServiceImpl getSamlSSOConfigService() {
        return samlSSOConfigService;
    }

    public static void setSamlSSOConfigService(SAMLSSOConfigServiceImpl samlSSOConfigService) {
        XDSServerDataHolder.samlSSOConfigService = samlSSOConfigService;
    }

    public static CORSManagementService getCORSManagementService() {
        return corsManagementService;
    }

    public static void setCORSManagementService(CORSManagementService corsManagementService) {
        XDSServerDataHolder.corsManagementService = corsManagementService;
    }

    public static IdpManager getIdentityProviderManager() {
        return identityProviderManager;
    }

    public static void setIdentityProviderManager(IdpManager identityProviderManager) {
        XDSServerDataHolder.identityProviderManager = identityProviderManager;
    }
}
