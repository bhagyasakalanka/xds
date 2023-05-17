package org.wso2.carbon.identity.xds.server.mgt.internal;

import org.wso2.carbon.email.mgt.EmailTemplateManager;
import org.wso2.carbon.event.publisher.core.EventPublisherService;
import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.identity.cors.mgt.core.CORSManagementService;
import org.wso2.carbon.identity.notification.sender.tenant.config.NotificationSenderManagementService;
import org.wso2.carbon.identity.oauth.OAuthAdminServiceImpl;
import org.wso2.carbon.identity.remotefetch.common.RemoteFetchConfigurationService;
import org.wso2.carbon.identity.sso.saml.SAMLSSOConfigServiceImpl;
import org.wso2.carbon.identity.template.mgt.TemplateManager;
import org.wso2.carbon.identity.user.store.configuration.UserStoreConfigService;
import org.wso2.carbon.idp.mgt.IdpManager;
import org.wso2.carbon.security.keystore.KeyStoreManagementService;

/**
 * Data holder for GRPC component.
 */
public class XDSServerDataHolder {

    private static ClaimMetadataManagementService claimMetadataManagementService;
    private static ApplicationManagementService applicationManagementService;
    private static OAuthAdminServiceImpl oAuthAdminService;
    private static SAMLSSOConfigServiceImpl samlSSOConfigService;
    private static CORSManagementService corsManagementService;
    private static IdpManager identityProviderManager;
    private static ConfigurationManager configurationManager;
    private static EventPublisherService eventPublisherService;
    private static KeyStoreManagementService keyStoreManagementService;
    private static RemoteFetchConfigurationService remoteFetchConfigurationService;
    private static EmailTemplateManager emailTemplateManager;
    private static NotificationSenderManagementService notificationSenderManagementService;
    private static TemplateManager templateManager;
    private static UserStoreConfigService userStoreConfigService;

    public static ClaimMetadataManagementService getClaimMetadataManagementService() {
        return claimMetadataManagementService;
    }

    public static void setClaimMetadataManagementService(
            ClaimMetadataManagementService claimMetadataManagementService) {
        XDSServerDataHolder.claimMetadataManagementService = claimMetadataManagementService;
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

    public static ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public static void setConfigurationManager(ConfigurationManager configurationManager) {
        XDSServerDataHolder.configurationManager = configurationManager;
    }

    public static EventPublisherService getEventPublisherService() {
        return eventPublisherService;
    }

    public static void setEventPublisherService(EventPublisherService eventPublisherService) {
        XDSServerDataHolder.eventPublisherService = eventPublisherService;
    }

    public static KeyStoreManagementService getKeyStoreManagementService() {
        return keyStoreManagementService;
    }

    public static void setKeyStoreManagementService(KeyStoreManagementService keyStoreManagementService) {
        XDSServerDataHolder.keyStoreManagementService = keyStoreManagementService;
    }

    public static RemoteFetchConfigurationService getRemoteFetchConfigurationService() {
        return remoteFetchConfigurationService;
    }

    public static void setRemoteFetchConfigurationService(
            RemoteFetchConfigurationService remoteFetchConfigurationService) {
        XDSServerDataHolder.remoteFetchConfigurationService = remoteFetchConfigurationService;
    }

    public static EmailTemplateManager getEmailTemplateManager() {
        return emailTemplateManager;
    }

    public static void setEmailTemplateManager(EmailTemplateManager emailTemplateManager) {
        XDSServerDataHolder.emailTemplateManager = emailTemplateManager;
    }

    public static NotificationSenderManagementService getNotificationSenderManagementService() {
        return notificationSenderManagementService;
    }

    public static void setNotificationSenderManagementService(
            NotificationSenderManagementService notificationSenderManagementService) {
        XDSServerDataHolder.notificationSenderManagementService = notificationSenderManagementService;
    }

    public static TemplateManager getTemplateManager() {
        return templateManager;
    }

    public static void setTemplateManager(TemplateManager templateManager) {
        XDSServerDataHolder.templateManager = templateManager;
    }

    public static UserStoreConfigService getUserStoreConfigService() {
        return userStoreConfigService;
    }

    public static void setUserStoreConfigService(UserStoreConfigService userStoreConfigService) {
        XDSServerDataHolder.userStoreConfigService = userStoreConfigService;
    }
}
