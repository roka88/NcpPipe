package com.roka.NcpPipe.NcpManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.server.connection.ServerConnection;
import com.ncloud.api.server.model.RootPassword;
import com.ncloud.api.server.model.ServerInstanceList;


import java.util.List;
import java.util.function.Function;

public class ServerManager extends ServerConnection {

    public enum HealthCheckType {
        RUN("RUN"), NSTOP("NSTOP");

        public String type;

        HealthCheckType(String type) {
            this.type = type;
        }

    }

    public ServerManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);

    }

    public ServerManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);

    }

    public Function<Object, ServerInstanceList> startServerInstancesForJob(List<String> serverInstanceNoList) {
        return (result) -> super.startServerInstances(serverInstanceNoList);
    }

    public Function<Object, ServerInstanceList> stopServerInstancesForJob(List<String> serverInstanceNoList) {
        return (result) -> super.stopServerInstances(serverInstanceNoList);
    }

    public Function<Object, ServerInstanceList> terminateServerInstancesForJob(List<String> serverInstanceNoList)  {
        return (result)-> super.terminateServerInstances(serverInstanceNoList);
    }

    public Function<Object, ServerInstanceList> getServerInstanceListForJob(List<String> serverInstanceNoList, String searchFilterName, String searchFilterValue, Integer pageNo, Integer pageSize, String serverInstanceStatusCode, String internetLineTypeCode, String regionNo, String baseBlockStorageDiskTypeCode, String baseBlockStorageDiskDetailTypeCode, String sortedBy, String sortingOrder)  {
        return (result)-> super.getServerInstanceList(serverInstanceNoList, searchFilterName, searchFilterValue, pageNo, pageSize, serverInstanceStatusCode, internetLineTypeCode, regionNo, baseBlockStorageDiskTypeCode, baseBlockStorageDiskDetailTypeCode, sortedBy, sortingOrder);
    }

    public Function<Object, ServerInstanceList> createServerInstancesForJob(String serverImageProductCode, String serverProductCode, String memberServerImageNo, String serverName, String serverDescription, String loginKeyName, Boolean isProtectServerTermination, Integer serverCreateCount, Integer serverCreateStartNo, String internetLineTypeCode, String feeSystemTypeCode, String userData, String zoneNo, List<String> accessControlGroupConfigurationNoList) {
        return (result)-> super.createServerInstances(serverImageProductCode, serverProductCode, memberServerImageNo, serverName, serverDescription, loginKeyName, isProtectServerTermination, serverCreateCount, serverCreateStartNo, internetLineTypeCode, feeSystemTypeCode, userData, zoneNo, accessControlGroupConfigurationNoList);
    }

    public Function<Object, RootPassword> getRootPasswordForJob(String serverInstanceNo, String privateKey) {
        return (result)-> super.getRootPassword(serverInstanceNo, privateKey);
    }

    public Function<Object, ServerInstanceList> rebootServerInstancesForJob(List<String> serverInstanceNoList) {
        return (result)-> super.rebootServerInstances(serverInstanceNoList);
    }

    public Function<Object, ServerInstanceList> changeServerInstanceSpecForJob(String serverInstanceNo, String serverProductCode) {
        return (result)-> super.changeServerInstanceSpec(serverInstanceNo, serverProductCode);
    }

}
