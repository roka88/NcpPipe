package com.roka.NcpPipe.NcpManager;


import com.ncloud.api.autoscaling.connection.SuspendProcessConnection;
import com.ncloud.api.autoscaling.model.ProcessList;
import com.ncloud.api.common.model.CommonResponse;
import com.ncloud.api.connection.NcloudApiRequest;


import java.util.List;
import java.util.function.Function;

public class SuspendProcessManager extends SuspendProcessConnection {

    public SuspendProcessManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public SuspendProcessManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public Function<Object, CommonResponse> suspendProcessesForJob(String autoScalingGroupName, List<String> scalingProcessCodeList) {
        return (result)-> super.suspendProcesses(autoScalingGroupName, scalingProcessCodeList);
    }


    public Function<Object, CommonResponse> resumeProcessesForJob(String autoScalingGroupName, List<String> scalingProcessCodeList) {
        return (result)-> super.resumeProcesses(autoScalingGroupName, scalingProcessCodeList);
    }


    public Function<Object, ProcessList> getScalingProcessTypeListForJob() {
        return (result)-> super.getScalingProcessTypeList();
    }
}
