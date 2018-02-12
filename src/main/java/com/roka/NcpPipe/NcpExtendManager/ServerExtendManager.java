package com.roka.NcpPipe.NcpExtendManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.server.model.ServerInstanceList;


import com.roka.NcpPipe.NcpManager.ServerManager;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public class ServerExtendManager extends ServerManager {

    public ServerExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ServerExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public Function<ServerInstanceList, ServerInstanceList> waitChangedServerStatusForJob(List<String> serverInstanceNoList, ServerManager.HealthCheckType currentStatus, ServerManager.HealthCheckType completeStatus, long statusCheckTimeMills) {
        return (result) -> {
            String status = currentStatus.type;
            ServerInstanceList waitResult = null;
            while (!status.equals(completeStatus.type)) {
                try {
                    Thread.sleep(statusCheckTimeMills);
                    waitResult = getServerInstanceList(serverInstanceNoList, null, null, null, null, null, null, null, null, null, null, null);
                    status = waitResult.getServerInstanceList().get(0).getServerInstanceStatus().getCode();
                    LoggerFactory.getLogger(this.getClass()).info("Instance Current Status : " + status);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return waitResult;
        };
    }
}
