package com.roka.NcpPipe.NcpExtendManager;


import com.ncloud.api.autoscaling.model.AutoScalingGroupList;
import com.ncloud.api.autoscaling.model.InAutoScalingGroupServerInstance;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpPipe.NcpManager.AutoScalingGroupManager;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class AutoScalingGroupExtendManager extends AutoScalingGroupManager {

    public AutoScalingGroupExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingGroupExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public Function<Object, AutoScalingGroupList> checkServerStatusOfAutoScalingInServiceForJob(String autoScalingGroupNameList, long statusCheckTimeMills, long defaultCooldown) {

        return (result) -> {

            AutoScalingGroupList autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
            boolean isAllInService = isServerInstancesInAutoScalingInService(autoScalingGroupList);
            while (!isAllInService) {
                try {
                    Thread.sleep(statusCheckTimeMills);
                    autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
                    isAllInService = isServerInstancesInAutoScalingInService(autoScalingGroupList);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return autoScalingGroupList;
        };
    }

    public Function<Object, AutoScalingGroupList> checkServerStatusOfAutoScalingInTerminatedForJob(String autoScalingGroupNameList, long statusCheckTimeMills, long defaultCooldown) {

        return (result) -> {
            AutoScalingGroupList autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
            boolean isAllInTerminated = isServerInstancesInAutoScalingInTerminate(autoScalingGroupList);
            while (!isAllInTerminated) {
                try {
                    Thread.sleep(statusCheckTimeMills);
                    autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
                    isAllInTerminated = isServerInstancesInAutoScalingInTerminate(autoScalingGroupList);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return autoScalingGroupList;
        };
    }


    private boolean isServerInstancesInAutoScalingInTerminate(AutoScalingGroupList result) {
        List<InAutoScalingGroupServerInstance> list = result.getAutoScalingGroupList().get(0).getInAutoScalingGroupServerInstanceList();

        for (int i=0;i <list.size();i++) {
            if (list.get(i).getLifecycleState().getCode().equals(HealthCheckType.INSVC.type) || list.get(i).getLifecycleState().getCode().equals(HealthCheckType.PNDNG.type)) {
                return false;
            }
        }
        return true;
    }

    private boolean isServerInstancesInAutoScalingInService(AutoScalingGroupList result) {
        List<InAutoScalingGroupServerInstance> list = result.getAutoScalingGroupList().get(0).getInAutoScalingGroupServerInstanceList();

        if (0 == list.size()) {
            return false;
        }
        for (int i=0;i <list.size();i++) {
            if (list.get(i).getLifecycleState().getCode().equals(HealthCheckType.TMNNG.type) || list.get(i).getLifecycleState().getCode().equals(HealthCheckType.PNDNG.type)) {
                return false;
            }
        }
        return true;

    }
}
