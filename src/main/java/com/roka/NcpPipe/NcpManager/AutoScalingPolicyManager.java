package com.roka.NcpPipe.NcpManager;

import com.ncloud.api.autoscaling.connection.AutoScalingPolicyConnection;
import com.ncloud.api.autoscaling.model.AdjustmentTypeList;
import com.ncloud.api.autoscaling.model.ScalingPolicyList;
import com.ncloud.api.common.model.CommonResponse;
import com.ncloud.api.connection.NcloudApiRequest;


import java.util.List;
import java.util.function.Function;

public class AutoScalingPolicyManager extends AutoScalingPolicyConnection {

    public AutoScalingPolicyManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingPolicyManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public Function<Object, ScalingPolicyList> getAutoScalingPolicyListForJob(List<String> policyNameList, String autoScalingGroupName, Integer pageNo, Integer pageSize) {
        return (result) -> super.getAutoScalingPolicyList(policyNameList, autoScalingGroupName, pageNo, pageSize);
    }

    public Function<Object, CommonResponse> putScalingPolicyForJob(String policyName, String autoScalingGroupName, String adjustmentTypeCode, Integer scalingAdjustment, Integer cooldown, Integer minAdjustmentStep) {
        return (result) -> super.putScalingPolicy(policyName, autoScalingGroupName, adjustmentTypeCode, scalingAdjustment, cooldown, minAdjustmentStep);
    }

    public Function<Object, CommonResponse> deletePolicyForJob(String policyName, String autoScalingGroupName) {
        return (result) -> super.deletePolicy(policyName, autoScalingGroupName);
    }

    public Function<Object, CommonResponse> executePolicyForJob(String policyName, String autoScalingGroupName, Boolean honorCooldown) {
        return (result) -> super.executePolicy(policyName, autoScalingGroupName, honorCooldown);
    }

    public Function<Object, AdjustmentTypeList> getAdjustmentTypeListForJob() {
        return (result) -> super.getAdjustmentTypeList();
    }
}
