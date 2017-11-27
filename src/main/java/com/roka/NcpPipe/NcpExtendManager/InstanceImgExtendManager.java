package com.roka.NcpPipe.NcpExtendManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.model.MemberServerImageList;
import com.roka.NcpPipe.NcpManager.ServerImageManager;


import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class InstanceImgExtendManager extends ServerImageManager {

    public InstanceImgExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public InstanceImgExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public Function<MemberServerImageList, MemberServerImageList> waitChangedServerImgStatusOfStaticImgNoForJob(HealthCheckType currentStatus, HealthCheckType completeStatus, long statusCheckTimeMills) {
        return (result)-> {
            String status = currentStatus.type;
            MemberServerImageList waitResult = null;
            while (!status.equals(completeStatus.type)) {
                try {
                    Thread.sleep(statusCheckTimeMills);
                    waitResult = getMemberServerImageList(Collections.singletonList(result.getMemberServerImageList().get(0).getMemberServerImageNo()), null, null, null, null, null, null);
                    status = waitResult.getMemberServerImageList().get(0).getMemberServerImageStatus().getCode();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return waitResult;
        };

    }

    public Function<Object, MemberServerImageList> waitChangedServerImgStatus(List<String> serverImgNo, HealthCheckType currentStatus, HealthCheckType completeStatus, long statusCheckTimeMills) {
        return (result)-> {
            String status = currentStatus.type;
            MemberServerImageList waitResult = null;
            while (!status.equals(completeStatus.type)) {
                try {
                    Thread.sleep(statusCheckTimeMills);
                    waitResult = getMemberServerImageList(serverImgNo, null, null, null, null, null, null);
                    status = waitResult.getMemberServerImageList().get(0).getMemberServerImageStatus().getCode();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return waitResult;
        };
    }

}
