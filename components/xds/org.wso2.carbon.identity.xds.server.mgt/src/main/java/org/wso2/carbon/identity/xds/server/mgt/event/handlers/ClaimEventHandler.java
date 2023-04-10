package org.wso2.carbon.identity.xds.server.mgt.event.handlers;

import com.google.gson.Gson;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimMetadataManagementService;
import org.wso2.carbon.identity.claim.metadata.mgt.ClaimXDSOperationType;
import org.wso2.carbon.identity.claim.metadata.mgt.exception.ClaimMetadataException;
import org.wso2.carbon.identity.claim.metadata.mgt.model.ClaimXDSWrapper;
import org.wso2.carbon.identity.xds.server.mgt.internal.XDSServerDataHolder;

/**
 * Event handler for claim events.
 */
public class ClaimEventHandler implements XDSEventHandler {

    @Override
    public void handleEvent(discovery.service.api.Event.ReceivedEvent request) throws ClaimMetadataException {

        Gson gson = new Gson();
        ClaimXDSWrapper claimXDSWrapper = gson.fromJson(request.getValue(), ClaimXDSWrapper.class);
        ClaimMetadataManagementService claimMetadataManagementService = XDSServerDataHolder
                .getClaimMetadataManagementService();

        switch (ClaimXDSOperationType.valueOf(request.getOperation())) {
            case ADD_CLAIM_DIALECT:
                claimMetadataManagementService.addClaimDialect(
                        claimXDSWrapper.getClaimDialect(),
                        request.getTenantDomain());
                break;
            case RENAME_CLAIM_DIALECT:
                claimMetadataManagementService.renameClaimDialect(
                        claimXDSWrapper.getOldClaimDialect(),
                        claimXDSWrapper.getNewClaimDialect(),
                        request.getTenantDomain());
                break;
            case REMOVE_CLAIM_DIALECT:
                claimMetadataManagementService.removeClaimDialect(
                        claimXDSWrapper.getClaimDialect(),
                        request.getTenantDomain());
                break;
            case ADD_LOCAL_CLAIM:
                    claimMetadataManagementService.addLocalClaim(
                            claimXDSWrapper.getLocalClaim(),
                            request.getTenantDomain());
                break;
            case UPDATE_LOCAL_CLAIM:
                claimMetadataManagementService.updateLocalClaim(
                        claimXDSWrapper.getLocalClaim(),
                        request.getTenantDomain());

                break;
            case REMOVE_LOCAL_CLAIM:
                claimMetadataManagementService.removeLocalClaim(
                        claimXDSWrapper.getLocalClaimURI(),
                        request.getTenantDomain());
                break;
            case UPDATE_LOCAL_CLAIM_MAPPINGS:
                claimMetadataManagementService.updateLocalClaimMappings(
                        claimXDSWrapper.getLocalClaimList(),
                        request.getTenantDomain(),
                        claimXDSWrapper.getUserStoreDomainName());
                break;
            case ADD_EXTERNAL_CLAIM:
                claimMetadataManagementService.addExternalClaim(
                        claimXDSWrapper.getExternalClaim(),
                        request.getTenantDomain());
                break;
            case UPDATE_EXTERNAL_CLAIM:
                claimMetadataManagementService.updateExternalClaim(
                        claimXDSWrapper.getExternalClaim(),
                        request.getTenantDomain());
                break;
            case REMOVE_EXTERNAL_CLAIM:
                claimMetadataManagementService.removeExternalClaim(
                        claimXDSWrapper.getExternalClaimDialectURI(),
                        claimXDSWrapper.getExternalClaimURI(),
                        request.getTenantDomain());
                break;
            case REMOVE_CLAIM_MAPPING_ATTRIBUTES:
                claimMetadataManagementService.removeClaimMappingAttributes(
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId(),
                        claimXDSWrapper.getUserStoreDomainName());
                break;
            case REMOVE_ALL_CLAIMS:
                claimMetadataManagementService.removeAllClaims(
                        PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId()
                );
                break;
            default:
                throw new ClaimMetadataException("Invalid operation type: " + request.getOperation());
        }
    }
}
