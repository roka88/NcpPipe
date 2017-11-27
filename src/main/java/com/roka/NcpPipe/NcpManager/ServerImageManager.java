package com.roka.NcpPipe.NcpManager;

import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.connection.MemberServerImageConnection;
import com.ncloud.api.image.model.MemberServerImageList;

import java.util.List;
import java.util.function.Function;

public class ServerImageManager extends MemberServerImageConnection {

    public enum HealthCheckType {
        INIT("INIT"), CREAT("CREAT");

        public String type;

        HealthCheckType(String type) {
            this.type = type;
        }

    }


    public ServerImageManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ServerImageManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public Function<Object, MemberServerImageList> getMemberServerImageListForJob(List<String> memberServerImageNoList, Integer pageNo, Integer pageSize, String regionNo, List<String> platformTypeCodeList, String sortedBy, String sortingOrder) {
        return (result) -> super.getMemberServerImageList(memberServerImageNoList, pageNo, pageSize, regionNo, platformTypeCodeList, sortedBy, sortingOrder);
    }


    public Function<Object, MemberServerImageList> deleteMemberServerImagesForJob(List<String> memberServerImageNoList) {
        return (result) -> super.deleteMemberServerImages(memberServerImageNoList);
    }


    public Function<Object, MemberServerImageList> createMemberServerImageForJob(String memberServerImageName, String memberServerImageDescription, String serverInstanceNo) {
        return (result) -> super.createMemberServerImage(memberServerImageName, memberServerImageDescription, serverInstanceNo);
    }
}
