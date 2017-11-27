package com.roka.NcpPipe.NcpManager;

import com.ncloud.api.autoscaling.connection.AutoScalingLogConnection;
import com.ncloud.api.autoscaling.model.ActivityLogList;
import com.ncloud.api.autoscaling.model.AdjustmentTypeList;
import com.ncloud.api.autoscaling.model.ConfigurationLogList;
import com.ncloud.api.connection.NcloudApiRequest;

import java.util.List;
import java.util.function.Function;

public class AutoScalingLogManager extends AutoScalingLogConnection {

    public AutoScalingLogManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingLogManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public Function<Object, ActivityLogList> getAutoScalingActivityLogListForJob(List<String> activityNoList, String autoScalingGroupName, Integer pageNo, Integer pageSize) {
        return (result) -> super.getAutoScalingActivityLogList(activityNoList, autoScalingGroupName, pageNo, pageSize);
    }


    public Function<Object, ConfigurationLogList> getAutoScalingConfigurationLogListFoJob(List<String> configurationNoList, String autoScalingGroupName, Integer pageNo, Integer pageSize) {
        return (result) -> super.getAutoScalingConfigurationLogList(configurationNoList, autoScalingGroupName, pageNo, pageSize);
    }
}
