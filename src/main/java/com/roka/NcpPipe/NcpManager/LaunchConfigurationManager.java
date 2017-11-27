package com.roka.NcpPipe.NcpManager;

import com.ncloud.api.autoscaling.connection.LaunchConfigurationConnection;
import com.ncloud.api.autoscaling.model.LaunchConfigurationList;
import com.ncloud.api.common.model.CommonResponse;
import com.ncloud.api.connection.NcloudApiRequest;

import java.util.List;
import java.util.function.Function;

public class LaunchConfigurationManager extends LaunchConfigurationConnection {

    public LaunchConfigurationManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public LaunchConfigurationManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public Function<Object, LaunchConfigurationList> getLaunchConfigurationListForJob(List<String> launchConfigurationNameList, Integer pageNo, Integer pageSize, String sortedBy, String sortingOrder) {
        return (result) -> super.getLaunchConfigurationList(launchConfigurationNameList, pageNo, pageSize, sortedBy, sortingOrder);
    }


    public Function<Object, LaunchConfigurationList> createLaunchConfigurationForJob(String launchConfigurationName, String serverImageProductCode, String serverProductCode, String memberServerImageNo, String loginKeyName, String userData, List<String> accessControlGroupConfigurationNoList) {
        return (result) -> super.createLaunchConfiguration(launchConfigurationName, serverImageProductCode, serverProductCode, memberServerImageNo, loginKeyName, userData, accessControlGroupConfigurationNoList);
    }


    public Function<Object, CommonResponse> deleteAutoScalingLaunchConfigurationForJob(String launchConfigurationName) {
        return (result) -> super.deleteAutoScalingLaunchConfiguration(launchConfigurationName);
    }
}
