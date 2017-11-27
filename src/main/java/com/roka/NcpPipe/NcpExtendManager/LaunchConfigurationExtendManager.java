package com.roka.NcpPipe.NcpExtendManager;



import com.ncloud.api.autoscaling.model.LaunchConfigurationList;
import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.model.MemberServerImageList;
import com.roka.NcpPipe.NcpManager.LaunchConfigurationManager;


import java.util.List;
import java.util.function.Function;

public class LaunchConfigurationExtendManager extends LaunchConfigurationManager {

    public LaunchConfigurationExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public LaunchConfigurationExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public Function<MemberServerImageList, LaunchConfigurationList> createLaunchConfigurationStaticWithImgNoForJob(String launchConfigurationName, String serverImageProductCode, String serverProductCode, List<String> accessControlGroupConfigurationNoList, String loginKeyName, String userData) {

        return (result) -> createLaunchConfiguration(launchConfigurationName, serverImageProductCode, serverProductCode, result.getMemberServerImageList().get(0).getMemberServerImageNo(), loginKeyName, userData, accessControlGroupConfigurationNoList);

    }
}
