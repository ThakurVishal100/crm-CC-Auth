package com.jss.jiffy_edge.convertors.auth;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails;
import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;
import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.entities.auth.SysAccessLevel;
import com.jss.jiffy_edge.dao.entities.auth.VwAppliedAccessPolicies;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.MasterPolicyRequest;
import com.jss.jiffy_edge.models.auth.PolicyResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PolicyConverter {

    public AccessPolicyMaster toMasterEntity(MasterPolicyRequest request) {
        AccessPolicyMaster entity = new AccessPolicyMaster();
        entity.setPolicyName(request.getPolicyName());
        entity.setDescp(request.getDescp());
        entity.setStatus(request.getStatus());
        entity.setCategory(request.getCategory());
        entity.setAvlForSuonly(request.getAvlForSuonly());
        return entity;
    }

    public AccessPolicyDetails toDetailsEntity(SystemAccessPolicyRequest request) {
        AccessPolicyDetails entity = new AccessPolicyDetails();
        entity.setAccessName(request.getAccessName());
        entity.setAccessDesc(request.getAccessDesc());
        entity.setStatus(request.getStatus());
        // Note: The menu itself is set in the service layer where we have access to the repository
        return entity;
    }

    public PolicyResponse toPolicyResponse(VwAppliedAccessPolicies policy) {
        ServiceDetails serviceDetails = new ServiceDetails();
        serviceDetails.setServiceId(policy.getServiceId());
        serviceDetails.setServiceName(policy.getServiceName());
        serviceDetails.setServiceDescp(policy.getServiceDescp());
        serviceDetails.setServiceCatg(policy.getServiceCatg());
        serviceDetails.setParentServiceId(policy.getParentServiceId());
        serviceDetails.setStatus(policy.getServiceStatus());

        PolicyResponse response = new PolicyResponse();
        response.setPolicyId(policy.getPolicyId());
        response.setPolicyName(policy.getAccessName());
        response.setPolicyDescription(policy.getAccessDesc());
        response.setServiceDetails(serviceDetails);
        response.setPermissions(getPermissionsFromSysAccessLevel(policy.getSysAccessLvlname()));
        response.setMenuId(policy.getMenuId());
        response.setMenuName(policy.getMenuName());
        response.setMenuIcon(policy.getMenuIcon());
        response.setMenuNameToDisplay(policy.getMenuNameToDisplay());
        response.setHintText(policy.getHintText());
        response.setMenuDescp(policy.getMenuDescp());
        response.setMenuLevel(policy.getMenuLevel());
        response.setHasChild(policy.isHasChild());
        response.setChildMenu(policy.isChildMenu());
        response.setShowOrder(policy.getShowOrder());
        response.setTargetLink(policy.getTargetLink());
        response.setTargetType(policy.getTargetType());
        response.setMenuStatus(policy.getMenuStatus());
        response.setSuAccessOnly(policy.getSuAccessOnly());

        return response;
    }

    private List<String> getPermissionsFromSysAccessLevel(SysAccessLevel.SysAccessLvlName lvlName) {
        if (lvlName == null) {
            return Collections.emptyList();
        }
        List<String> permissions = new ArrayList<>();
        switch (lvlName) {
            case ALL_ACCESS:
            case SYSTEM_ADMIN:
            case INSTANCE_ADMIN:
            case SERVICE_OWNER:
            case READ_WRITE_DELETE_CREATE:
                permissions.add("CREATE");
            case READ_WRITE_DELETE:
                permissions.add("DELETE");
            case READ_WRITE:
                permissions.add("WRITE");
            case READ:
                permissions.add("READ");
                break;
            default:
                break;
        }
        return permissions;
    }
}